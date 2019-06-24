package com.restik.mydiplom.controller;

import com.restik.mydiplom.entity.Reserve;
import com.restik.mydiplom.entity.User;
import com.restik.mydiplom.repositories.ReserveRepository;
import com.restik.mydiplom.repositories.RestaurantRepository;
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

@Controller
public class DeleteReserveController {

        @Autowired
        ReserveRepository reserveRepository;
        @Autowired
        RestaurantRepository restaurantRepository;
        @Autowired
        UserRepository userRepository;

        @RequestMapping(value = "/restaurant/deleteReserve", method = RequestMethod.GET)
        public String deleteReserve(Model model, Principal principal) {
            String email = principal.getName();
            System.out.println("\n /Метод гет в deleteReserve/ e-mail пользователя \n" +email);
            User adminOfRest = userRepository.findByEmail(email);
            System.out.println("\n Имя администратора ресторана " +adminOfRest.getName()+"\n");
            model.addAttribute("reserve", reserveRepository.findReserveByAdminId(adminOfRest.getId()));
            System.out.println("\n test" );
            return "admin/delete_reserve";
        }
        @Transactional
        @RequestMapping(value = "/restaurant/deleteReserve", method = RequestMethod.POST)
        public String deleteReserve(@ModelAttribute Reserve reserve, Model model) {
            String reserveID= reserve.getUser().getName();
            System.out.println("\n Перед запросом удаления резерва пользователя  "+ reserveID);


            reserveRepository.deleteReserveById(reserve.getId());

            System.out.println("\n После запроса удаления ресторана \n");

            return "admin/delete_reserve_success";
        }
}
