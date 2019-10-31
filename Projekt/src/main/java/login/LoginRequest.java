package login;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.*;
import java.io.IOException;


@Named
@RequestScoped
public class LoginRequest extends HttpServlet {
    private String password;
    private String username;


    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString()
    {
        return username+" "+password;
    }

/*
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String someUsername = req.getParameter("Username");
        String somePassword = req.getParameter("Password");

        FacesContext context = FacesContext.getCurrentInstance();

        if(someUsername.equals(getUsername()) && somePassword.equals(getPassword()))
        {
            context.getExternalContext().getSessionMap().put("user", username);

//            HttpSession session = SessionUtils.getSession();
//            session.setAttribute("username", username);

            Cookie loginCookie = new Cookie("user", someUsername);
            loginCookie.setMaxAge(30 * 60);  // ciasteczko umrze po 30 minutach
            resp.addCookie(loginCookie);
            resp.sendRedirect("loggedScreen");
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username or Password",
                            "Enter correct Username and Password"));
        }
    }
    */


    public boolean validateUsernameAndPassword(String someUsername, String somePassword)
    {
        boolean isValid;
        if(someUsername.equals(getUsername()) && somePassword.equals(getPassword()))
        {
            isValid = true;
        }
        else isValid = false;

        return isValid;
    }

    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();

        context.getExternalContext().redirect("index.xhtml");
    }

}
