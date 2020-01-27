package login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.*;


@Named
@RequestScoped
public class LoginRequest extends HttpServlet {
    private String username;
    private String password;


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
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
