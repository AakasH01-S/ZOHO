package com.test.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.test.model.User;
import com.test.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactservice;

    @GetMapping("/")
    public String contact(Model model) throws SQLException {
        HashMap<Integer, User> details = contactservice.displayContact();
        
        model.addAttribute("contacts", details);
        model.addAttribute("empty", details.size());

        return "index";
    }

    @PostMapping("/addcontact")
    public String add(Model model,
            @RequestParam("name") String name,
            @RequestParam("number") String number,
            @RequestParam("mail") String mail
        ) throws SQLException {

        boolean value = contactservice.addContact(name,number,mail);
        HashMap<Integer, User> details = contactservice.displayContact();
        model.addAttribute("contacts", details);
        model.addAttribute("exist", value );
        return "index";
    }

    @PostMapping("/remove")
    public String remove(
        Model model, 
        @RequestParam("index") int index
    ) throws SQLException {
        
        contactservice.deleteContact(index);
        HashMap<Integer, User> details = contactservice.displayContact();
        model.addAttribute("contacts",details);
        model.addAttribute("empty",details.size());
        return "index";
    }

    @PostMapping("/edit")
    public String edit(Model model,
            @RequestParam("name") String name,
            @RequestParam("number") String number,
            @RequestParam("mail") String mail,
            @RequestParam("index") int index
    ) throws SQLException {
        
        contactservice.editContact(name, number, mail, index);
        HashMap<Integer, User> details = contactservice.displayContact();
        model.addAttribute("contacts", details);
        return "index";
    }
    
// stmt.executeUpdate("update contact set name = '" + name + "',email = '" +mail + "',number = '" + number+ "' where id = " + index + ";");
    // @PostMapping("/search")
    // public String remove(Model model ,@RequestParam("search") String search){
    // ArrayList<User> searchedList= new ArrayList<>();
    // for (User user : ContactApp.contact) {
    // if(user.getName().contains(search)){
    // searchedList.add(user);
    // }
    // }
    // model.addAttribute("contacts",searchedList);
    // return "index";
    // }

}
