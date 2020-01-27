package sales;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    public CategoryEntity() { }
    public CategoryEntity(String name) { this.name = name; }

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

    public SectionEntity getSection() {return section;}
    public void setSection(SectionEntity section){this.section = section;}

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}
}
