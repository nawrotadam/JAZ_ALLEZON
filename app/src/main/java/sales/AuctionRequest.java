package sales;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

@Named
@SessionScoped
public class AuctionRequest implements Serializable {
    // normal values
    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String section;
    private String parameterName1;
    private String parameterName2;
    private String parameterName3;
    private String parameterValue1;
    private String parameterValue2;
    private String parameterValue3;
    private String photo1;
    private String photo2;
    private String photo3;

    // edited values
    private Long auctionId;
    private String newTitle;
    private String newDescription;
    private BigDecimal newPrice;
    private String newCategory;
    private String newSection;
    private String newParameter1;
    private String newParameter2;
    private String newParameter3;
    private String newPhoto1;
    private String newPhoto2;
    private String newPhoto3;

    // normal values
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getParameterName1() { return parameterName1; }
    public void setParameterName1(String parameterName1) { this.parameterName1 = parameterName1; }
    public String getParameterName2() { return parameterName2; }
    public void setParameterName2(String parameterName2) { this.parameterName2 = parameterName2; }
    public String getParameterName3() { return parameterName3; }
    public void setParameterName3(String parameterName3) { this.parameterName3 = parameterName3; }

    public String getParameterValue1() { return parameterValue1; }
    public void setParameterValue1(String parameterValue1) { this.parameterValue1 = parameterValue1; }
    public String getParameterValue2() { return parameterValue2; }
    public void setParameterValue2(String parameterValue2) { this.parameterValue2 = parameterValue2; }
    public String getParameterValue3() { return parameterValue3; }
    public void setParameterValue3(String parameterValue3) { this.parameterValue3 = parameterValue3; }

    public String getPhoto1() { return photo1; }
    public void setPhoto1(String photo1) { this.photo1 = photo1; }
    public String getPhoto2() { return photo2; }
    public void setPhoto2(String photo2) { this.photo2 = photo2; }
    public String getPhoto3() { return photo3; }
    public void setPhoto3(String photo3) { this.photo3 = photo3; }


    // edited values
    public Long getAuctionId() { return auctionId; }
    public void setAuctionId(Long auctionId) { this.auctionId = auctionId; }
    public String getNewTitle() { return newTitle; }
    public void setNewTitle(String newTitle) { this.newTitle = newTitle; }
    public String getNewDescription() { return newDescription; }
    public void setNewDescription(String newDescription) { this.newDescription = newDescription; }
    public BigDecimal getNewPrice() { return newPrice; }
    public void setNewPrice(BigDecimal newPrice) { this.newPrice = newPrice; }
    public String getNewCategory() { return newCategory; }
    public void setNewCategory(String newCategory) { this.newCategory = newCategory; }
    public String getNewSection() { return newSection; }
    public void setNewSection(String newSection) { this.newSection = newSection; }

    public String getNewParameter1() { return newParameter1; }
    public void setNewParameter1(String newParameter1) { this.newParameter1 = newParameter1; }
    public String getNewParameter2() { return newParameter2; }
    public void setNewParameter2(String newParameter2) { this.newParameter2 = newParameter2; }
    public String getNewParameter3() { return newParameter3; }
    public void setNewParameter3(String newParameter3) { this.newParameter3 = newParameter3; }

    public String getNewPhoto1() { return newPhoto1; }
    public void setNewPhoto1(String newPhoto1) { this.newPhoto1 = newPhoto1; }
    public String getNewPhoto2() { return newPhoto2; }
    public void setNewPhoto2(String newPhoto2) { this.newPhoto2 = newPhoto2; }
    public String getNewPhoto3() { return newPhoto3; }
    public void setNewPhoto3(String newPhoto3) { this.newPhoto3 = newPhoto3; }
}
