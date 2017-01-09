package main.java.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String submitMessage(@ModelAttribute("SpringWeb")Message message, ModelMap model) {
        model.addAttribute("phone-number", message.getPhoneNumber());
        model.addAttribute("email", message.getEmailAddress());
        model.addAttribute("message", message.getMessage());

        return "result";
    }
}

