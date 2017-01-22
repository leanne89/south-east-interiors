package main.java.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String submitMessage(@ModelAttribute("SpringWeb")Review review, ModelMap model) {
        model.addAttribute("name", review.getName());
        model.addAttribute("town", review.getTown());
        model.addAttribute("county", review.getCounty());
        model.addAttribute("testimonial", review.getTestimonial());

        return "review-result";
    }
}

