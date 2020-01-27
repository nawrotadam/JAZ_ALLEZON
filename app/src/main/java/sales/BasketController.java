package sales;
import org.omnifaces.cdi.Param;

import javax.ejb.Schedule;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class BasketController {
//
//    @Modyfing
//    @Transactional
//    @Query("DELETE FROM basket b WHERE b.creationdate < :date")
//    int removeOlderThan(@Param("date") java.sql.Date date);
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public AuctionEntity getNewContent()
//    {
//
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToBasket()
    {
        // adds auction from list to basket
    }

    public void clearBasket()
    {
        // clears out basket
    }

    @Schedule(month = "*/1")
    public void basketExpired()
    {
        clearBasket();
    }

    public void buyProducts()
    {
        clearBasket();
    }


}
