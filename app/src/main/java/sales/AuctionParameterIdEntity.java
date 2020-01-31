package sales;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuctionParameterIdEntity implements Serializable {
    private Long auctionId;
    private Long parameterId;

    public Long getAuctionId() { return auctionId; }
    public void setAuctionId(Long auctionId) { this.auctionId = auctionId; }

    public Long getParameterId() { return parameterId; }
    public void setParameterId(Long parameterId) { this.parameterId = parameterId; }

}
