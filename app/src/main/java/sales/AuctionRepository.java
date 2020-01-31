package sales;
import auth.ProfileEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

        ProfileEntity auctionOwner = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getSingleResult();

        AuctionEntity auction = new AuctionEntity();
        auction.setOwner(auctionOwner);

        String sectionName = auctionRequest.getSection();
        CategoryEntity category = em.createQuery("Select s.category from SectionEntity s where s.name = :sectionName", CategoryEntity.class).
                setParameter("sectionName", sectionName).getSingleResult();

        SectionEntity section = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getSingleResult();
        section.setCategory(category);
        auction.setCategory(category);

        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());
        em.persist(auction);

        ParameterEntity parameter1 = new ParameterEntity(auctionRequest.getParameterName1());
        AuctionParameterEntity auctionParameter1 = new AuctionParameterEntity();
        auctionParameter1.setAuction(auction);
        auctionParameter1.setParameter(parameter1);
        auctionParameter1.setValue(auctionRequest.getParameterValue1());
        em.persist(auctionParameter1);

        ParameterEntity parameter2 = new ParameterEntity(auctionRequest.getParameterName2());
        AuctionParameterEntity auctionParameter2 = new AuctionParameterEntity();
        auctionParameter2.setAuction(auction);
        auctionParameter2.setParameter(parameter2);
        auctionParameter2.setValue(auctionRequest.getParameterValue2());
        em.persist(auctionParameter2);

        ParameterEntity parameter3 = new ParameterEntity(auctionRequest.getParameterName3());
        AuctionParameterEntity auctionParameter3 = new AuctionParameterEntity();
        auctionParameter3.setAuction(auction);
        auctionParameter3.setParameter(parameter3);
        auctionParameter3.setValue(auctionRequest.getParameterValue3());
        em.persist(auctionParameter3);

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
        CategoryEntity category = em.createQuery("Select s.category from SectionEntity s where s.name = :sectionName", CategoryEntity.class).
                setParameter("sectionName", sectionName).getSingleResult();

        SectionEntity section = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getSingleResult();
        section.setName(auctionRequest.getNewSection());
        section.setCategory(category);
        auction.setCategory(category);
        em.merge(section);

        auction.setTitle(auctionRequest.getNewTitle());
        auction.setDescription(auctionRequest.getNewDescription());
        auction.setPrice(auctionRequest.getNewPrice());

        List<PhotoEntity> photoList = em.createQuery("Select p from PhotoEntity p where p.auction = :auction", PhotoEntity.class).
                setParameter("auction", auction).getResultList();
        photoList.get(0).setLink(auctionRequest.getNewPhoto1());
        photoList.get(1).setLink(auctionRequest.getNewPhoto2());
        photoList.get(2).setLink(auctionRequest.getNewPhoto3());

        AuctionParameterEntity auctionParameter = em.createQuery("Select p from AuctionParameterEntity p where p.auction = :auction", AuctionParameterEntity.class).
                setParameter("auction", auction).getSingleResult();

        Set<ParameterEntity> parameterSet = new LinkedHashSet<>();
        ParameterEntity parameter1 = new ParameterEntity(auctionRequest.getParameterName1());
        ParameterEntity parameter2 = new ParameterEntity(auctionRequest.getParameterName2());
        ParameterEntity parameter3 = new ParameterEntity(auctionRequest.getParameterName3());

        parameterSet.add(parameter1);
        parameterSet.add(parameter2);
        parameterSet.add(parameter3);

//        auctionParameter.setParameters(parameterSet);
        em.merge(auctionParameter);

        em.merge(auction);
        return "index";
    }

    @Transactional
    public String addSection() {
        String categoryName = auctionRequest.getCategory();
        String sectionName = auctionRequest.getSection();
        List<SectionEntity> findSection = em.
                createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        if(findSection.isEmpty())
        {
            CategoryEntity category = em.
                    createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                    setParameter("categoryName", categoryName).getSingleResult();

            SectionEntity section = new SectionEntity(auctionRequest.getSection());
            section.setCategory(category);
            em.persist(section);
        }
        return "index";
    }

    @Transactional
    public String editSection() {
        String sectionName = auctionRequest.getSection();
        String newSectionName = auctionRequest.getNewSection();

        List<SectionEntity> findSection = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        if(!findSection.isEmpty())
        {
            findSection.get(0).setName(newSectionName);
            em.merge(findSection.get(0));
        }
        return "index";
    }

    @Transactional
    public String addCategory()
    {
        String categoryName = auctionRequest.getCategory();
        List<CategoryEntity> findCategory = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();

        if(findCategory.isEmpty())
        {
            CategoryEntity category = new CategoryEntity();
            category.setName(categoryName);
            em.persist(category);
        }
        return "index";
    }

    @Transactional
    public String editCategory()
    {
        String oldCategoryName = auctionRequest.getCategory();
        String newCategoryName = auctionRequest.getNewCategory();

        List<CategoryEntity> findCategory = em.createQuery("Select c from CategoryEntity c where c.name = :oldCategoryName", CategoryEntity.class).
                setParameter("oldCategoryName", oldCategoryName).getResultList();

        if(!findCategory.isEmpty())
        {
            findCategory.get(0).setName(newCategoryName);
        }

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
    public List<AuctionEntity> getOwnerAuctions()
    {
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        ProfileEntity auctionOwner = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getSingleResult();

        List<AuctionEntity> debug = em.
                createQuery("Select a from AuctionEntity a where a.owner = :auctionOwner", AuctionEntity.class).
                setParameter("auctionOwner", auctionOwner).getResultList();

        return em.
                createQuery("Select a from AuctionEntity a where a.owner = :auctionOwner", AuctionEntity.class).
                setParameter("auctionOwner", auctionOwner).getResultList();
    }

    @Transactional
    public List<AuctionEntity> getAllAuctions()
    {
        return em.
                createQuery("Select a from AuctionEntity a", AuctionEntity.class).getResultList();
    }

    @Transactional
    public List<String> getSectionNames()
    {
        return em.
                createQuery("Select s.name from SectionEntity s", String.class).getResultList();
    }

    @Transactional
    public List<String> getCategoryNames()
    {
        return em.
                createQuery("Select c.name from CategoryEntity c", String.class).getResultList();
    }

    @Transactional
    public void addTestCategoryAndSection() {
        String sectionName = "asd";
        List<SectionEntity> sectionList = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        String categoryName = "asd";
        List<CategoryEntity> categoryList = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();

        if(sectionList.isEmpty() && categoryList.isEmpty())
        {
            var category = new CategoryEntity("asd");
            var section = new SectionEntity("asd");

            section.setCategory(category);
            em.persist(category);
            em.persist(section);
        }
    }

}
