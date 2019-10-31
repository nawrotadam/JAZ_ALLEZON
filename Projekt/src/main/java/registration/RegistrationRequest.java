package registration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class RegistrationRequest {
    private String password;
    private String repeatedPassword;
    private String username;
    private String forename;
    private String surname;
    private String email;
    private String dateOfBirth;


    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRepeatedPassword(){
        return repeatedPassword;
    }
    public void setRepeatedPassword(String repeatedPassword){ this.repeatedPassword = repeatedPassword; }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getForename(){
        return forename;
    }
    public void setForename(String forename){
        this.forename = forename;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){ this.dateOfBirth = dateOfBirth; }

    public boolean passwordEquals(Object object)
    {
        return object.equals(password);
    }

    // TODO polaczyc sie z baza i wpisac wartosci do bazy

    //TODO porownac repeated password ze zwyklym haslem, ew naprawic to w formularzu

}
