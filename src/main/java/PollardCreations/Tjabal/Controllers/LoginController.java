package PollardCreations.Tjabal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author james
 */
@Controller
public class LoginController
{
//    @GetMapping("/")
    @GetMapping("signInPage")
    public String home() 
    {
        return "SignInPage";
    }	
    
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) 
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        
        // Implement authentication logic here (e.g., using Spring Security)
        if (isValidUser(username, password)) 
        {
            return "redirect:/home"; 
        } 
        else 
        {
           
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login?error";
        }
    }
    
    private boolean isValidUser(String username, String password) 
    {
        // Replace with your actual authentication logic 
        return "user".equals(username) && "password".equals(password);
    }
}