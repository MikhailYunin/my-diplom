package com.restik.mydiplom.controller;

import com.restik.mydiplom.repositories.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShowReserveController {
    @Autowired
    ReserveRepository reserveRepository;


    @RequestMapping(value = "/restaurant/showReserve", method = RequestMethod.GET)
    public String showReserve(Model model) {
//, @RequestParam(name = "restaurant") int restaurantId
        int restaurantId = 2;
        System.out.println("\n Перед запросом ресторана \n");
        model.addAttribute("reserve", reserveRepository.findByRestaurant(restaurantId));
        System.out.println("\n После запроса ресторана \n");
        return "admin/show_reserve";

    }
}
