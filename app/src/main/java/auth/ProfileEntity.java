package auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "profile")
public class ProfileEntity {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="firstname")
    private String firstname;

    @NotNull
    @Column(name="lastname")
    private String lastname;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="dateofbirth")
    private LocalDate dateofbirth;


    public ProfileEntity() { }
    public ProfileEntity(String firstname) { this.firstname = firstname; }
    public ProfileEntity(String username, String password, String firstname, String lastname, String email, LocalDate dateofbirth)
    {
        this.username = username; this.password = password; this.firstname = firstname;
        this.lastname = lastname; this.email = email; this.dateofbirth = dateofbirth;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public LocalDate getDateofbirth() { return dateofbirth; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setDateofbirth(LocalDate dateofbirth) { this.dateofbirth = dateofbirth; }

}