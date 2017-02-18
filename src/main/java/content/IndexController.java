package content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(HttpSession session)
    {
        String contactTokenName = "contactToken";
        generateToken(session, contactTokenName);

        String reviewTokenName = "reviewToken";
        generateToken(session, reviewTokenName);

        return "index";
    }

    private String generateToken(HttpSession session, String tokenName)
    {
        String token = Double.toString(Math.random());

        session.setAttribute(tokenName, token);

        return token;
    }
}
