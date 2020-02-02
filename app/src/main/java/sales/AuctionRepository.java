package sales;
import auth.ProfileEntity;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-06.jpg
// https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-22.jpg
// https://scene360.com/wp-content/uploads/2014/04/action-fighting-movies-02.jpg

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

        String categoryName = auctionRequest.getCategory();
        SectionEntity section = em.createQuery("Select c.section from CategoryEntity c where c.name = :categoryName", SectionEntity.class).
                setParameter("categoryName", categoryName).getSingleResult();

        CategoryEntity category = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getSingleResult();
        category.setSection(section);
        auction.setSection(section);

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

        String categoryName = auctionRequest.getCategory();
        SectionEntity section = em.createQuery("Select c.section from CategoryEntity c where c.name = :categoryName", SectionEntity.class).
                setParameter("categoryName", categoryName).getSingleResult();

        CategoryEntity category = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getSingleResult();
        category.setName(auctionRequest.getNewCategory());
        category.setSection(section);
        auction.setSection(section);
        em.merge(category);

        auction.setTitle(auctionRequest.getNewTitle());
        auction.setDescription(auctionRequest.getNewDescription());
        auction.setPrice(auctionRequest.getNewPrice());

        List<PhotoEntity> photoList = em.createQuery("Select p from PhotoEntity p where p.auction = :auction", PhotoEntity.class).
                setParameter("auction", auction).getResultList();
        if(photoList.size() == 1)
        {
            photoList.get(0).setLink(auctionRequest.getNewPhoto1());
            em.merge(photoList.get(0));
        }
        else if(photoList.size() == 2)
        {
            photoList.get(0).setLink(auctionRequest.getNewPhoto1());
            photoList.get(1).setLink(auctionRequest.getNewPhoto2());
            em.merge(photoList.get(0));
            em.merge(photoList.get(1));
        }
        else if(photoList.size() == 3)
        {
            photoList.get(0).setLink(auctionRequest.getNewPhoto1());
            photoList.get(1).setLink(auctionRequest.getNewPhoto2());
            photoList.get(2).setLink(auctionRequest.getNewPhoto3());
            em.merge(photoList.get(0));
            em.merge(photoList.get(1));
            em.merge(photoList.get(2));
        }



//
//        ParameterEntity parameter1 = new ParameterEntity(auctionRequest.getParameterName1());
//        AuctionParameterEntity auctionParameter1 = new AuctionParameterEntity();
//        auctionParameter1.setAuction(auction);
//        auctionParameter1.setParameter(parameter1);
//        auctionParameter1.setValue(auctionRequest.getParameterValue1());
//        em.persist(auctionParameter1);


        AuctionParameterEntity auctionParameter = em.createQuery("Select p from AuctionParameterEntity p where p.auction = :auction", AuctionParameterEntity.class).
                setParameter("auction", auction).getSingleResult();

        Set<ParameterEntity> parameterSet = new LinkedHashSet<>();

//        AuctionParameterEntity parameterValue1;
//        ParameterEntity parameterName1;
//
//        AuctionParameterEntity auctionParameter1 = em.createQuery("Select p from AuctionParameterEntity p where p.auction = :auction", AuctionParameterEntity.class).
//                setParameter("auction", auction).getSingleResult();
//
//        ParameterEntity parameter =


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
    public List<AuctionEntity> getOwnerAuctions()
    {
        var session = request.getSession(false);
        String sessionUsername = String.valueOf(session.getAttribute("username"));

        ProfileEntity auctionOwner = em.
                createQuery("Select u from ProfileEntity u where u.username = :sessionUsername", ProfileEntity.class).
                setParameter("sessionUsername", sessionUsername).getSingleResult();

        List<AuctionEntity> auctionList = em.
                createQuery("Select a from AuctionEntity a where a.owner = :auctionOwner", AuctionEntity.class).
                setParameter("auctionOwner", auctionOwner).getResultList();

        if(!auctionList.isEmpty())
        {
            return auctionList;
        }
        else return null;
    }

    @Transactional
    public String addCategory()
    {
        String categoryName = auctionRequest.getCategory();
        String sectionName = auctionRequest.getSection();
        List<CategoryEntity> findCategory = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();

        if(findCategory.isEmpty())
        {
            SectionEntity section = em.
                    createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                    setParameter("sectionName", sectionName).getSingleResult();

            CategoryEntity category = new CategoryEntity();
            category.setName(categoryName);
            category.setSection(section);
            em.persist(category);
        }
        return "index";
    }

    @Transactional
    public String editCategory()
    {
        String categoryName = auctionRequest.getCategory();
        String newCategoryName = auctionRequest.getNewCategory();

        List<CategoryEntity> findCategory = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();

        if(!findCategory.isEmpty())
        {
            findCategory.get(0).setName(newCategoryName);
            em.merge(findCategory.get(0));
        }
        return "index";
    }

    @Transactional
    public String addSection() {
        String sectionName = auctionRequest.getSection();
        List<SectionEntity> sectionList = em.
                createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        if(sectionList.isEmpty())
        {
            SectionEntity section = new SectionEntity(auctionRequest.getSection());
            em.persist(section);

        }
        return "index";
    }

    @Transactional
    public String editSection()
    {
        String oldSectionName = auctionRequest.getSection();
        String newSectionName = auctionRequest.getNewSection();

        List<SectionEntity> findSection = em.createQuery("Select s from SectionEntity s where s.name = :oldSectionName", SectionEntity.class).
                setParameter("oldSectionName", oldSectionName).getResultList();

        if(!findSection.isEmpty())
        {
            findSection.get(0).setName(newSectionName);
        }

        return "index";
    }

    @Transactional
    public List<String> getSectionNames()
    {
        return em.createQuery("Select s.name from SectionEntity s", String.class).getResultList();
    }

    @Transactional
    public List<String> getCategoryNames()
    {
        return em.createQuery("Select c.name from CategoryEntity c", String.class).getResultList();
    }


    @Transactional
    public List<AuctionEntity> getAllAuctions()
    {
        return em.createQuery("Select a from AuctionEntity a", AuctionEntity.class).getResultList();
    }

    @Transactional
    public void addTestCategoryAndSection() {
        String categoryName = "asd";
        List<CategoryEntity> categoryList = em.createQuery("Select c from CategoryEntity c where c.name = :categoryName", CategoryEntity.class).
                setParameter("categoryName", categoryName).getResultList();

        String sectionName = "asd";
        List<SectionEntity> sectionList = em.createQuery("Select s from SectionEntity s where s.name = :sectionName", SectionEntity.class).
                setParameter("sectionName", sectionName).getResultList();

        if(categoryList.isEmpty() && sectionList.isEmpty())
        {
            var section = new SectionEntity("asd");
            var category = new CategoryEntity("asd");

            em.persist(section);
            category.setSection(section);
            em.persist(category);
        }
    }

}
