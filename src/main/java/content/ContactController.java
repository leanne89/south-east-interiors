package content;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

@Controller
public class ContactController extends FormController{

    @Autowired
    private ApplicationMailer mailer;

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String submitMessage(HttpSession session, @ModelAttribute("SpringWeb") Enquiry enquiry, ModelMap model) {
        model.addAttribute("phone", enquiry.getPhone());
        model.addAttribute("email", enquiry.getEmail());
        model.addAttribute("subject", enquiry.getSubject());
        model.addAttribute("message", enquiry.getMessage());

        String errorTemplate = validateEnquiry(enquiry, session);

        if( errorTemplate != null)
        {
            return errorTemplate;
        }

        String phone = enquiry.getPhone();
        String email = getEmail(enquiry);
        String fromAddress = getFromAddress(enquiry);
        String subject = enquiry.getSubject();
        String message = enquiry.getMessage();

        String emailBody = "### New enquiry ###\n\n\n" +
                            "Phone: " + phone + "\n\n" +
                            "Email address: " + email + "\n\n" +
                            "Enquiry: " + message;

        mailer.sendNewEnquiryMail(subject, emailBody, fromAddress);

        return "contact-result";
    }

    private String validateEnquiry(Enquiry enquiry, HttpSession session)
    {
        String invalidTokenTemplate = validateToken(session, "contactToken", enquiry.getToken());

        if(invalidTokenTemplate != null)
        {
            return invalidTokenTemplate;
        }

        if( enquiry.getPhone().isEmpty() && enquiry.getEmail().isEmpty() )
        {
            return "contact-error-no-contact-method";
        }
        if( !enquiry.getPhone().isEmpty())
        {
            try
            {
                StringUtils.isNumeric( enquiry.getPhone() );
            }
            catch( NumberFormatException e )
            {
                return "contact-error-invalid-phone";
            }

        }
        if( !enquiry.getEmail().isEmpty() )
        {
            try
            {
                InternetAddress emailAddr = new InternetAddress(enquiry.getEmail());
                emailAddr.validate();
            }
            catch (AddressException ex)
            {
                return "contact-error-invalid-email";
            }
        }
        if( enquiry.getSubject().isEmpty())
        {
            return "contact-error-no-subject";
        }
        if( enquiry.getMessage().isEmpty())
        {
            return "contact-error-no-message";
        }
        return null;
    }

    private String getEmail( Enquiry enquiry)
    {
        String email = "Not given";

        if( !enquiry.getEmail().isEmpty())
        {
            email = enquiry.getEmail();
        }
        return email;
    }

    private String getFromAddress( Enquiry enquiry)
    {
        String fromAddress = null;

        if( !enquiry.getEmail().isEmpty())
        {
            fromAddress = enquiry.getEmail();
        }

        return fromAddress;
    }
}

