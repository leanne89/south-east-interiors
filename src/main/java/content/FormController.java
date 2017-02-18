package content;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class FormController {

    protected String validateToken(HttpSession session, String tokenName, String tokenValue)
    {
        String sessionToken = (String)session.getAttribute(tokenName);

        if(!tokenValue.isEmpty() && sessionToken != null && tokenValue.equals(sessionToken))
        {
            session.removeAttribute(tokenName);
            return null;
        }
        return "form-error-invalid-token";
    }
}

