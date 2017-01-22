package main.java.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String submitMessage(@ModelAttribute("SpringWeb")Message message, ModelMap model) {
        model.addAttribute("phone", message.getPhoneNumber());
        model.addAttribute("email", message.getEmailAddress());
        model.addAttribute("message", message.getMessage());

        return "contact-result";
    }
}

