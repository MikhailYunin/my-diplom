package com.restik.mydiplom.controller;


import com.restik.mydiplom.dao.RestaurantDAO;
import com.restik.mydiplom.dao.TableDAO;
import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.entity.TableOfRestaurant;
import com.restik.mydiplom.exception.ProjException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TableController {

    @RequestMapping(value="addTable.html", method = RequestMethod.GET)
    public String initializeForm(@ModelAttribute("tableAdd") Person user) {

        return "successRestaurantAdded";
    }


    @RequestMapping(value="addTable.html",method = RequestMethod.POST)
    protected ModelAndView doSubmitAction(@ModelAttribute("addingTable") TableOfRestaurant restTable, HttpServletRequest request) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}
    ModelAndView mv = new ModelAndView();
		try {
        String restName=request.getParameter("restName");
        int tableNumber = Integer.parseInt(request.getParameter("tableNo"));

        RestaurantDAO restDAO=new RestaurantDAO();
        Restaurant rest=restDAO.fetchMyRestaurant(restName);

        //HttpSession session = request.getSession();
        //System.out.print("test");
        TableDAO tableDAO = new TableDAO();
        TableOfRestaurant restTab = tableDAO.fetchMyRestaurantTable(tableNumber);
        //System.out.print("test1");
        //Restaurant rest= (Restaurant)session.getAttribute("restSessionObj");
        //Restaurant rest = (Restaurant) session.getAttribute("restaurant");

        if(restTab == null){
            TableOfRestaurant avail=tableDAO.create(restTable.getTableNo(),rest);
            System.out.println("Table avialability is"+avail);
            mv.addObject("tableAdded",avail);
            mv.setViewName("successRestaurantAdded");
            return mv;
            // DAO.close();
        }
        else{
            mv.setViewName("successRestaurantAdded");
            System.out.println("You already have this table added to the system");
            return mv;
        }
    }
		catch (ProjException e) {
        System.out.println("Exception: " + e.getMessage());
        mv.setViewName("successRestaurantAdded");
        return mv;
    }


}

    @RequestMapping(value="/deleteTable.html",method = RequestMethod.POST)
    protected String occupiedTable(@ModelAttribute("deletingTable") TableOfRestaurant restTable,HttpServletRequest request) throws Exception {

        String restName=request.getParameter("restName");
        RestaurantDAO restDAO=new RestaurantDAO();
        Restaurant rest=restDAO.fetchMyRestaurant(restName);
        TableDAO tableDAO = new TableDAO();
        int rowsUpdated=tableDAO.update(restTable.getTableNo(), restTable.getTableStatus(), rest);
        System.out.print(rowsUpdated);

//			RestaurantTable avail=tableDAO.delete(restTable);
//			System.out.println("Table avialability is"+avail);
        // DAO.close();

        return "test";
//        return "successRestaurantAdded";
    }

    @RequestMapping(value="/updateVacancy.html",method = RequestMethod.POST)
    protected String vacantTable(@ModelAttribute("updatingTableVacancy") TableOfRestaurant restTable,HttpServletRequest request) throws Exception {

        String restName=request.getParameter("restName");
        RestaurantDAO restDAO=new RestaurantDAO();
        Restaurant rest=restDAO.fetchMyRestaurant(restName);
        TableDAO tableDAO = new TableDAO();
        int rowsUpdated=tableDAO.updateVacancy(restTable.getTableNo(), restTable.getTableStatus(), rest);
        System.out.print(rowsUpdated);

//			RestaurantTable avail=tableDAO.delete(restTable);
//			System.out.println("Table avialability is"+avail);
        // DAO.close();

        return "test";
//        return "successRestaurantAdded";
    }
}
    /*
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
