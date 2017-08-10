package hello.Controller;

import hello.Dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value="/admin")
public class AdminPageController {

    @Autowired
    private AccountRepository accountRepository;

    @Secured("ROLE_ADMIN")
    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public String listAccounts(Model model, Authentication authentication, HttpServletRequest request) {
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println(userDetails.getAuthorities());
        //System.out.println("Onko ADMIN oikeuksia? " + request.isUserInRole("ADMIN"));
        model.addAttribute("accounts", accountRepository.findAll());
        return "account-list";
    }

}
