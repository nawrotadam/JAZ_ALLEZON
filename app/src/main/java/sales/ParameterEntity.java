package sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "parameter")
public class ParameterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    @OrderColumn(name = "order")
    private AuctionEntity auction;


    public ParameterEntity() { }
    public ParameterEntity(String name) { this.name = name; }

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}

    public String getName(){ return name; }
    public void setName(String name) {this.name = name;}

    public AuctionEntity getAuction() { return auction; }
    public void setAuction(AuctionEntity auction) { this.auction = auction;}
}