package sales;
import auth.ProfileEntity;
import auth.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class AuctionRepository {
    @Inject
    AuctionRequest auctionRequest;

    @Inject
    private HttpServletRequest request;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String addAuction() {
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        List<ProfileEntity> auctionOwnerList = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getResultList();
        ProfileEntity auctionOwner = auctionOwnerList.get(auctionOwnerList.size() - 1);

        AuctionEntity auction = new AuctionEntity();
        auction.setOwner(auctionOwner);

        String sectionName = auctionRequest.getSection();
        List<SectionEntity> sectionList = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();
        SectionEntity section = sectionList.get(sectionList.size() - 1);

        String categoryName = auctionRequest.getCategory();
        List<CategoryEntity> categoryList = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();
        CategoryEntity category = categoryList.get(categoryList.size() - 1);

        category.setSection(section);
        auction.setCategory(category);

        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());
        em.persist(auction);

        ParameterEntity parameter1 = new ParameterEntity(auctionRequest.getParameter1());
        ParameterEntity parameter2 = new ParameterEntity(auctionRequest.getParameter2());
        ParameterEntity parameter3 = new ParameterEntity(auctionRequest.getParameter3());

        parameter1.setAuction(auction);
        parameter2.setAuction(auction);
        parameter3.setAuction(auction);

        em.persist(parameter1);
        em.persist(parameter2);
        em.persist(parameter3);

        PhotoEntity photo1 = new PhotoEntity(auctionRequest.getPhoto1());
        PhotoEntity photo2 = new PhotoEntity(auctionRequest.getPhoto2());
        PhotoEntity photo3 = new PhotoEntity(auctionRequest.getPhoto3());

        photo1.setAuction(auction);
        photo2.setAuction(auction);
        photo3.setAuction(auction);

        em.persist(photo1);
        em.persist(photo2);
        em.persist(photo3);

        return "index";
    }

    @Transactional
    public String editAuction()
    {
        Long auctionId = auctionRequest.getAuctionId();

        AuctionEntity auction = em.
                createQuery("Select a from AuctionEntity a where a.id = :auctionId", AuctionEntity.class).
                setParameter("auctionId", auctionId).getSingleResult();

        String sectionName = auctionRequest.getSection();
        SectionEntity section = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getSingleResult();
        section.setName(auctionRequest.getNewSection());
        em.merge(section);

        String categoryName = auctionRequest.getCategory();
        CategoryEntity category = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getSingleResult();
        category.setName(auctionRequest.getNewCategory());
        em.merge(category);

        category.setSection(section);

        auction.setTitle(auctionRequest.getNewTitle());
        auction.setDescription(auctionRequest.getNewDescription());
        auction.setPrice(auctionRequest.getNewPrice());

        List<PhotoEntity> photoList = em.createQuery("Select p from PhotoEntity p where p.auction = :auction", PhotoEntity.class).
                setParameter("auction", auction).getResultList();
        photoList.get(0).setLink(auctionRequest.getNewPhoto1());
        photoList.get(1).setLink(auctionRequest.getNewPhoto2());
        photoList.get(2).setLink(auctionRequest.getNewPhoto3());

        List<ParameterEntity> parameterList = em.createQuery("Select p from ParameterEntity p where p.auction = :auction", ParameterEntity.class).
                setParameter("auction", auction).getResultList();
        parameterList.get(0).setName(auctionRequest.getNewParameter1());
        parameterList.get(1).setName(auctionRequest.getNewParameter2());
        parameterList.get(2).setName(auctionRequest.getNewParameter3());

        em.merge(auction);
        return "index";
    }

    @Transactional
    public String addSection() {
        String sectionName = auctionRequest.getSection();
        List<SectionEntity> section = em.
                createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        if(section.isEmpty())
        {
            SectionEntity sectionEntity = new SectionEntity(auctionRequest.getSection());
            em.persist(sectionEntity);
        }
        return "index";
    }

    @Transactional
    public String editSection() {
        String sectionName = auctionRequest.getSection();
        String newSectionName = auctionRequest.getNewSection();
        List<SectionEntity> sectionList = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();
        SectionEntity section = sectionList.get(sectionList.size() - 1);

        section.setName(newSectionName);
        em.merge(section);

        return "index";
    }

    @Transactional
    public List<PhotoEntity> getThumbnails()
    {
        // https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-06.jpg
        // https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-22.jpg
        // https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-02.jpg
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        List<ProfileEntity> auctionOwnerList = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getResultList();
        ProfileEntity auctionOwner = auctionOwnerList.get(auctionOwnerList.size() - 1);

        return em.
                createQuery("Select a.photos from AuctionEntity a where a.owner = :auctionOwner").
                setParameter("auctionOwner", auctionOwner).getResultList();
    }

    @Transactional
    public List<AuctionEntity> getAuctions()
    {
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        List<ProfileEntity> auctionOwnerList = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getResultList();
        ProfileEntity auctionOwner = auctionOwnerList.get(auctionOwnerList.size() - 1);

        return em.
                createQuery("Select a from AuctionEntity a where a.owner = :auctionOwner", AuctionEntity.class).
                setParameter("auctionOwner", auctionOwner).getResultList();
    }



    @Transactional
    public void addTestCategoryAndSection() {
        var section = new SectionEntity("asd");
        em.persist(section);

        var category = new CategoryEntity("asd");
        category.setSection(section);

        em.persist(category);

    }

    @Transactional
    public String persistTestUser()
    {
        System.out.println("Test user added");

        ProfileEntity user = new ProfileEntity("admin", "admin","admin", "admin",
                "admin@admin.pl", LocalDate.parse("2007-12-03"));
        em.persist(user);

        var session = request.getSession(true);
        session.setAttribute("username", "admin");
        return "addAuction.xhtml";
    }


}
