package hello.Controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;

public interface SecuredMethodsContainer {

    @Secured("ADMIN")
    String listAccounts(Model model);

}
