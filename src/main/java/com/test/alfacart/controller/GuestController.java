package com.test.alfacart.controller;

import com.test.alfacart.entity.Guest;
import com.test.alfacart.model.Response;
import com.test.alfacart.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping({"/", "/guest", "/guest/browse", "/guest/home"})
    public String homePage() {
        return "browse";
    }

    @RequestMapping("/guest/create")
    public String create() {
        return "create";
    }

    @GetMapping("/guest/detail")
    @ResponseBody
    public Guest getDetail(HttpServletRequest request) {
        return guestRepository.findByName(request.getParameter("id"));
    }

    @RequestMapping(value = "/guest/create/action", method = RequestMethod.POST)
    public @ResponseBody
    Response saveGuest(@Valid @RequestBody Guest guest) {
        Response response = new Response();
        try {
            Guest tamu = guestRepository.findByName(guest.getName());
            if (tamu != null) {
                response.setResult(false);
                response.setMessage("Data With Name " + guest.getName() + " Was Exist");
            } else {
                guestRepository.save(guest);
                response.setResult(true);
                response.setMessage("Success Save Data");
            }
        } catch (Exception g) {
            response.setResult(false);
            response.setMessage(g.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/guest/update/action", method = RequestMethod.POST)
    public @ResponseBody
    Response updateGuest(@Valid @RequestBody Guest guest) {
        Response response = new Response();
        try {
            Guest tamu = guestRepository.findByName(guest.getName());
            if (tamu != null) {
                guestRepository.save(guest);
                response.setResult(true);
                response.setMessage("Success Update Data");
            } else {
                response.setResult(false);
                response.setMessage("Data With Name " + guest.getName() + " Not Found");
            }
        } catch (Exception s) {
            response.setResult(false);
            response.setMessage(s.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/guest/delete", method = RequestMethod.POST)
    public @ResponseBody
    Response deleteGuest(HttpServletRequest request) {
        Response response = new Response();
        try {
            String id = request.getParameter("id");
            Guest guest = guestRepository.findByName(id);
            if (guest != null) {
                guestRepository.delete(guest);
                response.setResult(true);
                response.setMessage("Success Delete");
            } else {
                response.setResult(false);
                response.setMessage("Data With Name " + id + " Not Found");
            }
        } catch (Exception g) {
            response.setResult(false);
            response.setMessage(g.getMessage());
        }
        return response;
    }

    @GetMapping("/guest/all")
    @ResponseBody
    public List<Guest> getAll() {
        return guestRepository.findAllByOrderByName();
    }

    @GetMapping("/guest/search")
    @ResponseBody
    public List<Guest> search(HttpServletRequest request) {
        String search = request.getParameter("search");
        String status = request.getParameter("status");
        if (search.equals("") || search == null) {
            search = "";
        }
        if (status.equals("") || status == null) {
            status = "";
        }
        if (status.equals("all")) {
            return guestRepository.findAllByOrderByName();
        } else {
            return guestRepository.findByNameContainsOrAddressContainsOrderByName(search, search);
        }
    }
}
