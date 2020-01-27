package login;
import auth.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class LogoutController {
    @Inject
    private ProfileService profileService;

    public String logout()
    {
        profileService.logout();

        return "login.xhtml?faces-redirect=true";
    }
}
