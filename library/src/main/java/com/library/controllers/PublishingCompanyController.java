package com.library.controllers;

import com.library.models.Author;
import com.library.models.PublishingCompany;
import com.library.services.PublishingCompanyService;
import com.library.services.Validation;
import com.library.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class PublishingCompanyController {

    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @RequestMapping(value = "/createPublishingCompany", method = RequestMethod.GET)
    public String createPublishingCompany() {
        return "PublishingCompany/PublishingCompanyForm";
    }


    @PostMapping("/createPublishingCompany")
    public String createPublishingCompany(@Validated PublishingCompany publishingCompany, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.out.println("Erro no campo");
            return "redirect:/createPublishingCompany";
        }else {
            publishingCompanyService.save(publishingCompany);
        }


        return "redirect:/listPublishingCompany";
    }

    @PostMapping("/listPublishingCompany/{id}")
    public ModelAndView editPublishingCompany(PublishingCompany publishingCompany) {
        publishingCompanyService.save(publishingCompany);

        return new ModelAndView("Admin/PublishingCompanyConfig");
    }

    @GetMapping("/listPublishingCompany/{id}")
    public ModelAndView editPublishingCompany(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Admin/PublishingCompanyEdit");
        PublishingCompany publishingCompany = publishingCompanyService.findById(id);

        modelAndView.addObject("publishingCompany", publishingCompany);

        return modelAndView;
    }

    @GetMapping("/deletePublishingCompany/{id}")
    public String deletePublishingCompany(@PathVariable("id") long id) {

        publishingCompanyService.deleteById(id);

        return "redirect:/listPublishingCompany";
    }


    @RequestMapping("/listPublishingCompany/{action}")
    public ModelAndView listPublishingCompany(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Admin/PublishingCompanyConfig");
        List<PublishingCompany> publishingCompanies;
        if(action != null) {
            publishingCompanies = publishingCompanyService.listAllPublishingCompanies(action);

        }else{
            publishingCompanies = publishingCompanyService.listAllPublishingCompanies(0);
        }

        modelAndView.addObject("publishingCompanies", publishingCompanies);

        return modelAndView;
    }

    @GetMapping("/listPublishingCompany")
    public ModelAndView listPublishingCompanyPageable() {
        ModelAndView modelAndView = new ModelAndView("Admin/PublishingCompanyConfig");
        List<PublishingCompany> publishingCompanies = publishingCompanyService.listAllPublishingCompanies(0);

        modelAndView.addObject("publishingCompanies", publishingCompanies);

        return modelAndView;
    }

}
