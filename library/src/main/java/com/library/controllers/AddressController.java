package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressController {

    @RequestMapping(value = "/createAddress", method = RequestMethod.GET)
    public String createAddress() {
        return "Address/AddressForm";
    }
}
