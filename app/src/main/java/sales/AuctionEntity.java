package sales;
import auth.ProfileEntity;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auction")
public class AuctionEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="price")
    private BigDecimal price;

    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private ProfileEntity owner;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "auction")
    private List<PhotoEntity> photos;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "auction")
    private List<ParameterEntity> parameters;



    public AuctionEntity() { }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public CategoryEntity getCategory() { return category; }
    public ProfileEntity getOwner(){return owner;}
    public List<PhotoEntity> getPhotos(){return photos;}
    public List<ParameterEntity> getParameters(){return parameters;}

    public void setId(Long id){this.id = id;}
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setOwner(ProfileEntity owner) { this.owner = owner; }
    public void setCategory(CategoryEntity category) { this.category = category; }
    public void setPhotos(List<PhotoEntity> photos) { this.photos = photos; }
    public void setParameters(List<ParameterEntity> parameters) { this.parameters = parameters; }

}
