package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.entity.Tables;
import com.restik.mydiplom.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;


    @RequestMapping(value = "/restaurant/add", method = RequestMethod.GET)
    public String showForm (Model model){
        model.addAttribute("restaurant", new Restaurant());
        return "admin/AddRestaurant";
    }

    @RequestMapping(value = "/restaurant/add", method = RequestMethod.POST)
    public String submitForm (@ModelAttribute Restaurant restaurant, Model model,
                              @RequestParam(name = "count") int[]count){
        System.out.println("количество мест на каждый стол : " + Arrays.toString(count));
        for (int i = 0; i < count.length; i++) {
            Tables tables = new Tables();
            tables.setVisitorsVolume(count[i]);
            tables.setRestaurant(restaurant);
            restaurant.getTablesList().add(tables);
        }
        restaurantRepository.save(restaurant);
        model.addAttribute("addInfo", restaurant.getRestaurantName());

        return "admin/AddRestaurant";
    }

}
