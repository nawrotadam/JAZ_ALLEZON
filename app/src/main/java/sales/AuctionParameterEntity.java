package sales;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "auctionParameter")
public class AuctionParameterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AuctionParameterIdEntity auctionParameterId = new AuctionParameterIdEntity();

    @Column(name="value")
    private String value;

    @MapsId("auctionId")
    @ManyToOne
    @JoinColumn(name="auction_id")
    private AuctionEntity auction;

    @MapsId("parameterId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;


    public AuctionParameterEntity() { }
    public AuctionParameterEntity(ParameterEntity parameter)
    {
        this.parameter = parameter;
    }

    public AuctionParameterIdEntity getAuctionParameterId() { return auctionParameterId; }
    public void setAuctionParameterId(AuctionParameterIdEntity auctionParameterId) { this.auctionParameterId = auctionParameterId; }
    public AuctionEntity getAuction() { return auction; }
    public void setAuction(AuctionEntity auction) { this.auction = auction; }
    public ParameterEntity getParameter() { return parameter; }
    public void setParameter(ParameterEntity parameter) { this.parameter = parameter; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
