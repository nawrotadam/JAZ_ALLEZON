package sales;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
public class CategoryEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy="category")
    private Set<SectionEntity> sections;


    public CategoryEntity() { }
    public CategoryEntity(String name) { this.name = name; }

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

    public Set<SectionEntity> getSections() {return sections;}
    public void setSections(Set<SectionEntity> sections){this.sections = sections;}

    public void addSection(SectionEntity section) { this.sections.add(section); }

}
