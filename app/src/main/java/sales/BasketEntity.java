package sales;

import auth.ProfileEntity;
import sales.AuctionEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name = "basket")
public class BasketEntity {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="creationDate")
    private Time creationDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private ProfileEntity owner;

    @OneToOne(optional = false)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Time getCreationDate() { return creationDate; }
    public void setCreationDate(Time creationDate) { this.creationDate = creationDate; }

    public ProfileEntity getOwner() { return owner; }
    public void setOwner(ProfileEntity owner) { this.owner = owner; }

    public AuctionEntity getAuction() { return auction; }
    public void setAuction(AuctionEntity auction) { this.auction = auction; }


}
