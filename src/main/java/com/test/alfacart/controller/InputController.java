package com.test.alfacart.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class InputController {

    public static String games() {
        Integer you = 0;
        Integer AI = 0;
        int begin, end;
        Scanner input = new Scanner(System.in);
        System.out.println("Input min values");
        begin = input.nextInt();
        System.out.println("Input max values");
        end = input.nextInt();
        ArrayList ListData = new ArrayList<>();
        for (int i = begin; i < end; i++) {
            if (i % 2 == 0) {
                ListData.add(i);
            }
        }
        ArrayList ListYou = new ArrayList<>();
        int much = ListData.size();

        for (int i = 0; i < much; i++) {
            for (int q = 0; q < ListData.size(); q++) {

                if (ListYou.contains(ListData.get(q))) {
                    ListData.remove(q);
                }
            }

            Collections.shuffle(ListData);
            System.out.println("Pick a number from most left or right "
                    + ListData);
            int num = input.nextInt();
            ListYou.add(num);

            if (!ListData.contains(num)) {
                System.out.println("Don't do it, please pick only our data");
                break;
            }

            int left = Integer.parseInt((String) ListData.get(0).toString());
            int rigth = Integer.parseInt((String) ListData.get(ListData.size() - 1).toString());

            if (num != left) {
                if (num != rigth) {
                    System.out.println("Don't do it just pick most left or right ");
                    break;
                }
            }

            you = you + num;
            System.out.println("You Pick : " + num);

            Random randomNumbers = new Random();
            int aa = randomNumbers.nextInt(ListData.size());
            System.out.println("AI: " + ListData.get(aa));
            AI = AI + Integer.parseInt((String) ListData.get(0).toString());

        }

        System.out.println("Total Nilai Kamu " + you);
        System.out.println("Total Nilai AI " + AI);

        if (you > AI) {
            System.out.println("YOU WIN .. ");
            return "You Win ..";
        } else {
            System.out.println("YOU LOSE .. ");
            return "You Lose ..";
        }
    }

}
