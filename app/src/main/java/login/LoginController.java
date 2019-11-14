package login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {

@Inject
LoginRequest loginRequest;



}
