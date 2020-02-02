package sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="section")
public class SectionEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy="section")
    private Set<CategoryEntity> categories;


    public SectionEntity() { }
    public SectionEntity(String name) { this.name = name; }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

    public Set<CategoryEntity> getCategories(){ return categories; }
    public void setCategories(Set<CategoryEntity> categories) {this.categories = categories;}
}
