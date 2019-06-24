package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.Reserve;
import com.restik.mydiplom.entity.Tables;
import com.restik.mydiplom.entity.User;
import com.restik.mydiplom.repositories.ReserveRepository;
import com.restik.mydiplom.repositories.RestaurantRepository;
import com.restik.mydiplom.repositories.TableRepository;
import com.restik.mydiplom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Controller
public class ReserveController {
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    UserRepository userRepository;

    private LocalDateTime dateReserveDeltaMinus;
    private LocalDateTime dateReserveDeltaPlus;
    private int visitorsVolume;
    private LocalDateTime dateReserve;


    @RequestMapping(value = "/reserve/add", method = RequestMethod.GET)
    public String showForm(Model model) {

        model.addAttribute("restaurant", restaurantRepository.findAll());
        //  model.addAttribute("tables", tableRepository.findAll());
        model.addAttribute("reserve", new Reserve());

        return "user/add_reserve";
    }

    @RequestMapping(value = "/reserve/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Reserve reserve, Model model, Principal principal,
                             @RequestParam(name = "restaurant") int restaurantId,
                             @RequestParam(name = "visitorsVolume") int visitorsVolume,
                             @RequestParam(name = "reserveStart") String reserveStart
    ) {

        System.out.println("restId " + restaurantId);
        System.out.println("visitorsVolume " + visitorsVolume);
        System.out.println("reserveStart " + reserveStart);

        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTime = LocalDateTime.parse(reserveStart, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("ISO Date Time is "+localDateTime);

        dateReserveDeltaMinus = localDateTime.minusHours(2);
        dateReserveDeltaPlus = localDateTime.plusHours(2);

        String email = principal.getName();
        System.out.println("\n Имя пользователя email \n" +email);


        User user = userRepository.findByEmail(email);
        System.out.println("\n Имя пользователя резервации \n" +user.getName());
        System.out.println("\n пользователь резервации \n" + user.toString());


        try {
            Tables tables = tableRepository.findFreeTable(restaurantId, visitorsVolume, dateReserveDeltaMinus, dateReserveDeltaPlus).get();
            if (tables != null & timeNow.isBefore(localDateTime)) {

                System.out.println("\n РЕзерв не нул \n");

                System.out.println("Table id: " + tableRepository.findFreeTable(restaurantId, visitorsVolume, dateReserveDeltaMinus, dateReserveDeltaPlus).toString());
                reserve.setTables(tables);
                reserve.setUser(user);
                reserveRepository.save(reserve);
//                user.setReserve(reserve);
                userRepository.save(user);

                return "user/add_reserve_success";
            } else {
                System.out.println("\n РЕзерв нул \n");
                return "user/add_reserve_failed";
            }

        }catch (Exception NoSuchElementException) {
                System.out.println("\n Не Взлетело Exception: " + NoSuchElementException.getMessage());

                return "user/add_reserve_failed";
        }

    }




}



