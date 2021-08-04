package com.library.controllers;

import com.library.models.Address;
import com.library.models.Author;
import com.library.models.User;
import com.library.services.AddressService;
import com.library.services.UserService;
import com.library.services.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/createAddress", method = RequestMethod.GET)
    public String createAddress() {
        return "Address/AddressForm";
    }

    @PostMapping("/createAddress")
    public String createAddress(Address address) {
        User userLogged = userService.findAll().get(0);

        addressService.save(address);

        userLogged.setAddress(address);

        userService.save(userLogged);

        return "redirect:/Home";
    }
}
