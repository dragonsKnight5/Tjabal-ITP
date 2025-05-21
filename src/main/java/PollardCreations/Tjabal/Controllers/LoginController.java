package PollardCreations.Tjabal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author james
 */
@Controller
public class LoginController
{
    @GetMapping("/")
    public String home() 
    {
            return "SignInPage";
    }	
}