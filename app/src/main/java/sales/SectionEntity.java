package sales;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;


    public SectionEntity() { }
    public SectionEntity(String name) { this.name = name; }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

    public CategoryEntity getCategory(){ return category; }
    public void setCategory(CategoryEntity category) {this.category = category;}
}
