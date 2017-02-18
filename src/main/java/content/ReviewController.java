package content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController extends FormController{

    @Autowired
    private ApplicationMailer mailer;

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String submitMessage(HttpSession session, @ModelAttribute("SpringWeb")Review review, ModelMap model) {
        model.addAttribute("name", review.getName());
        model.addAttribute("town", review.getTown());
        model.addAttribute("county", review.getCounty());
        model.addAttribute("testimonial", review.getTestimonial());

        String errorTemplate = validateReview(review, session);

        if( errorTemplate != null)
        {
            return errorTemplate;
        }

        String name = review.getName();
        String town = review.getTown();
        String county = review.getCounty();
        String testimonial = review.getTestimonial();

        String emailBody = "### New review! ###\n\n\n" +
                "Name: " + name + "\n\n" +
                "Town: " + town + "\n\n" +
                "County: " + county + "\n\n" +
                "Review: " + testimonial;

        mailer.sendNewReviewMail(emailBody);

        return "review-result";
    }

    private String validateReview(Review review, HttpSession session)
    {
        String invalidTokenTemplate = validateToken(session, "reviewToken", review.getToken());

        if(invalidTokenTemplate != null)
        {
            return invalidTokenTemplate;
        }

        if( review.getName().isEmpty()
            || review.getTown().isEmpty()
            || review.getCounty().isEmpty()
            || review.getTestimonial().isEmpty())
        {
            return "review-error-empty-fields";
        }
        return null;
    }
}

