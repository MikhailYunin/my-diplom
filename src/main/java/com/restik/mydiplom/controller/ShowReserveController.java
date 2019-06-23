package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.User;
import com.restik.mydiplom.repositories.ReserveRepository;
import com.restik.mydiplom.repositories.RestaurantRepository;
import com.restik.mydiplom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ShowReserveController {
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/restaurant/showReserve", method = RequestMethod.GET)
    public String showReserve(Model model, Principal principal) {
        String email = principal.getName();
        System.out.println("\n /Метод гет в showReserve/ e-mail пользователя \n" +email);
        User adminOfRest = userRepository.findByEmail(email);
        System.out.println("\n Имя администратора ресторана " +adminOfRest.getName()+"\n");
        model.addAttribute("restaurant", restaurantRepository.findRestaurantByAdminId(adminOfRest.getId()));
        return "admin/show_reserve";
    }

    @RequestMapping(value = "/restaurant/showReserve", method = RequestMethod.POST)
    public String showReserve(Model model, @RequestParam(name = "restaurant") int restaurantId) {
        System.out.println("\n Перед запросом ресторана \n");
        model.addAttribute("reserve", reserveRepository.findByRestaurant(restaurantId));
        System.out.println(reserveRepository.findByRestaurant(restaurantId));
        System.out.println("\n После запроса ресторана \n");
        model.addAttribute("restaurant", restaurantRepository.findAll());
        return "admin/show_reserve";
    }
}
