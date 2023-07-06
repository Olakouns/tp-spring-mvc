package io.sn.mpisi2.controllers;

import io.sn.mpisi2.entities.Message;
import io.sn.mpisi2.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class NewHomeController {
    @RequestMapping("/{versionId}/message")
    public String getMessage(@Validated @PathVariable("versionId") String versionId, Model model) {

        if (!versionId.equalsIgnoreCase("v1") && !versionId.equalsIgnoreCase("v2")) {
            throw new ResourceNotFoundException("Version", versionId);
        }

        model.addAttribute("message", new Message((versionId.equals("v1")) ? "Welcome here !!" : "Welcome here to new version! Enjoy."));
        return "index";
    }
}
