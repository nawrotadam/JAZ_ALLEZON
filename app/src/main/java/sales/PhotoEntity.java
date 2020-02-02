package sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="photo")
public class PhotoEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    @OrderColumn(name = "order")
    private AuctionEntity auction;

    public PhotoEntity() {}
    public PhotoEntity(String link) {this.link = link;}

    public String getLink(){ return link; }
    public void setLink(String link) {this.link = link;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public AuctionEntity getAuction() {return auction;}
    public void setAuction(AuctionEntity auction) {this.auction = auction;}

}
