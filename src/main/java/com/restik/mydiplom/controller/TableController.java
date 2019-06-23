/*
package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.Reserve;
import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.entity.Tables;
import com.restik.mydiplom.repositories.ReserveRepository;
import com.restik.mydiplom.repositories.RestaurantRepository;
import com.restik.mydiplom.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableController {

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
//    @Autowired
//    private ReserveRepository reserveRepository;


    @RequestMapping(value = "restaurant/table/add", method= RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("restaurants", restaurantRepository.findAll());
//        model.addAttribute("reserve", reserveRepository.findAll());
        model.addAttribute("tables", new Tables());
        return "add_table";
    }


    @RequestMapping(value = "restaurant/table/add", method= RequestMethod.POST)
    public String submitForm(@ModelAttribute Tables tables, Model model,
                             @RequestParam(name = "restaurantId") String restaurantId){
                             //@RequestParam(name = "reserveId") String reserveId) {

        Restaurant restaurant = restaurantRepository.findById(Integer.valueOf(restaurantId)).get();

        updateParticipantRegInfo(tables, restaurant);
        tableRepository.save(tables);

        model.addAttribute("restaurants", restaurantRepository.findAll());

//        Reserve reserve = reserveRepository.findById(Integer.valueOf(reserveId)).get();

        //String code = tables.getTableNum();
//        boolean isPresent = tableRepository.findByCode(code).isPresent();
//
//        if (isPresent) {
//            Tables presentParticipant = tableRepository.findByCode(code).get();
//            updateParticipantRegInfo(tables, reserve, restaurant);
//            tableRepository.save(presentParticipant);
//        } else {
//            updateParticipantRegInfo(tables, reserve, restaurant);
//            tableRepository.save(tables);
//        }


       // model.addAttribute("restaurants", restaurantRepository.findAll());
       // model.addAttribute("tables", tableRepository.findAll());
        //model.addAttribute("reserve", new Tables());
        return "add_table";
    }

    private void updateParticipantRegInfo(Tables participant, Restaurant restaurant){
//        participant.getReserveList().add(reserve);
        participant.setRestaurant(restaurant);
        restaurant.getTablesList().add(participant);

    }

}
*/
