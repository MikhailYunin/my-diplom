package com.restik.mydiplom.controller;

import com.restik.mydiplom.dao.AdminOfRestaurantDAO;
import com.restik.mydiplom.entity.AdminOfRestaurant;
import com.restik.mydiplom.exception.ProjException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("registerAdmin.htm")
public class AdminController {

    @RequestMapping(method = RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("regAdmin") AdminOfRestaurant restAdmin) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}

        try {
            System.out.print("test");
            AdminOfRestaurantDAO restDAO = new AdminOfRestaurantDAO();
            System.out.print("test1");

            restDAO.create(restAdmin.getFirstName(),restAdmin.getLastName(),restAdmin.getUsername(),restAdmin.getPassword(),restAdmin.getRoleType());
            // DAO.close();
        } catch (ProjException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("regAdmin") AdminOfRestaurant restAdmin) {
        return "home";
    }
}
