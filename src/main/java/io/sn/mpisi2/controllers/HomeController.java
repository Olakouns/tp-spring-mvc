package io.sn.mpisi2.controllers;

import io.sn.mpisi2.entities.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/v1/message")
    public String getMessage(Model model) {
        model.addAttribute("message", new Message("Welcome here !"));
        return "index";
    }
}
