package com.restik.mydiplom.controller;

import com.restik.mydiplom.dao.VisitorDAO;
import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.entity.Visitor;
import com.restik.mydiplom.exception.ProjException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VisitorController {
//	@Autowired
//	@Qualifier("userValidator")
//	UserValidator validator;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("regUser") Person user) {
        System.out.print("regUserGet");
        return "add_user";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("regUser") Visitor user) {

        try {
            System.out.print("test");
            VisitorDAO userDAO = new VisitorDAO();
            System.out.print("test1");

            userDAO.create(user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getRoleType());

            // DAO.close();
        } catch (ProjException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "home";
    }
}
