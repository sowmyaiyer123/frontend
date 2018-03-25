package com.priceseries.frontend.controller;

import com.priceseries.frontend.model.UserInput;
import com.priceseries.frontend.service.RestService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RestService restService;

    @GetMapping("/userinput")
    public String userInputForm(Model model) {
        model.addAttribute("userinput", new UserInput());
        return "userinput";
    }

    @PostMapping("/userinput")
    public String userInputSubmit(@ModelAttribute UserInput userInput, Model model) {
        int numberOfQuotes = userInput.getNumberOfQuotes();
        log.info(String.valueOf(numberOfQuotes));
        String average = restService.getData(numberOfQuotes);
        model.addAttribute("average", "The average of the last " + numberOfQuotes + " quotes is " + average);
        model.addAttribute("userinput", userInput);
        log.info(average);
        return "userinput";
    }
}
