package com.test.alfacart.repository;

import com.test.alfacart.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {

    public List<Guest> findAllByOrderByName();

    public List<Guest> findByNameContainsOrAddressContainsOrderByName(String name, String address);

    public Guest findByName(String name);
}
