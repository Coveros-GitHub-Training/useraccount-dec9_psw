package com.coveros.training.flavorhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for serving the main web pages
 */
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    /**
     * Display the company management page
     * @return the company view template
     */
    @GetMapping("/company")
    public String company() {
        return "company";
    }
    
    /**
     * Display the employee management page
     * @return the employees view template
     */
    @GetMapping("/employees")
    public String employees() {
        return "employees";
    }
    
    /**
     * Display the recipes browsing page
     * TODO: Add model attributes for pre-populated data
     * @return the recipes view template
     */
    @GetMapping("/recipes")
    public String recipes() {
        return "recipes";
    }
}
