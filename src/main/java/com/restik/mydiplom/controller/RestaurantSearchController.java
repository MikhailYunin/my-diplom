package com.restik.mydiplom.controller;


import com.restik.mydiplom.dao.RestaurantDAO;
import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.exception.ProjException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/restaurantSearch.html")
public class RestaurantSearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("searchResult") Person person) {
        return "userHome";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doSubmitAction(@ModelAttribute("searchResults") Restaurant restaurant) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}
        ModelAndView mv = new ModelAndView();
        try {
            System.out.print("test");
            RestaurantDAO restDAO = new RestaurantDAO();
            System.out.print("test1");
            List<Restaurant> restList=restDAO.getAll();

            // DAO.close();
            if(restList.size()==0){
                mv.setViewName("userHome");
                return mv;
            }else{
                mv.addObject("restList",restList);
                mv.setViewName("searchResult");
                return mv;
            }

        } catch (ProjException e) {
            System.out.println("Exception: " + e.getMessage());

            mv.addObject("searchSuccess",null);
            mv.setViewName("userHome");
            return mv;
        }
    }
}
