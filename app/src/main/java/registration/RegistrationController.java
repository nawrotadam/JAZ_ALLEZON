package registration;
import auth.ProfileService;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegistrationController {
    @Inject
    private RegistrationRequest registrationRequest;

    @Inject
    private ProfileService profileService;

    public String register() {
        if (profileService.doesUserExist(registrationRequest.getUsername())) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("already-exists", "Username already exists.");
            return "register.xhtml?faces-redirect=true";
        }

        profileService.addUser(
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                registrationRequest.getFirstname(),
                registrationRequest.getLastname(),
                registrationRequest.getEmail(),
                registrationRequest.getDateOfBirth()
        );

        return "index.xhtml?faces-redirect=true";
    }

}

