package sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "parameter")
public class ParameterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public ParameterEntity() { }
    public ParameterEntity(String name) { this.name = name; }

    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

}