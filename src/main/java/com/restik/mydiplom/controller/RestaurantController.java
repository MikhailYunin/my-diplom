package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.entity.Tables;
import com.restik.mydiplom.entity.User;
import com.restik.mydiplom.repositories.RestaurantRepository;
import com.restik.mydiplom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/restaurant/add", method = RequestMethod.GET)
    public String showForm (Model model){
        model.addAttribute("restaurant", new Restaurant());
        return "admin/AddRestaurant";
    }

    @RequestMapping(value = "/restaurant/add", method = RequestMethod.POST)
    public String submitForm (@ModelAttribute Restaurant restaurant, Model model, Principal principal,
                              @RequestParam(name = "count") int[]count){
        String email = principal.getName();
        System.out.println("\n e-mail пользователя \n" +email);


        User adminOfRest = userRepository.findByEmail(email);
        System.out.println("\n Имя администратора ресторана " +adminOfRest.getName()+"\n");
        System.out.println("\n Администратор ресторана " +adminOfRest.toString()+"\n");


        System.out.println("\n количество мест на каждый стол : " + Arrays.toString(count));
        for (int i = 0; i < count.length; i++) {
            Tables tables = new Tables();
            tables.setTableNum(i+1);
            tables.setVisitorsVolume(count[i]);
            tables.setRestaurant(restaurant);
            restaurant.getTablesList().add(tables);
        }

        restaurant.setAdminOfRest(adminOfRest);
        restaurantRepository.save(restaurant);
        model.addAttribute("addInfo", restaurant.getRestaurantName());

        return "admin/AddRestaurant";
    }

}
