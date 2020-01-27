package auth;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;


@ApplicationScoped
@Named
public class ProfileRepository {
    @PersistenceContext
    private EntityManager em;


    ProfileEntity requireUser(String localUsername) {
        try{
            return em.
                    createQuery("Select u from ProfileEntity u where u.username = :localUsername", ProfileEntity.class).
                    setParameter("localUsername", localUsername).getSingleResult();
        } catch(NoResultException e) {
            throw new IllegalStateException("Required user does not exist.");
        }
    }

    @Transactional
    Optional<ProfileEntity> findUserByUsername(String localUsername) {
        try {
            return Optional.of(em.
                    createQuery("Select u from ProfileEntity u where u.username = :localUsername", ProfileEntity.class).
                    setParameter("localUsername", localUsername).getSingleResult());
        } catch (NoResultException e) { return Optional.empty(); }
    }

    @Transactional
    public void addUser(ProfileEntity user) {
        String localUsername = user.getUsername();

        try{
            ProfileEntity profileEntity =  em.
                    createQuery("Select u from ProfileEntity u where u.username = :localUsername", ProfileEntity.class).
                    setParameter("localUsername", localUsername).getSingleResult();

            throw new IllegalStateException(String.format("User %s already exists.", user.getUsername())); //todo potencjalnie podejrzane, sprawdzic
        } catch (NoResultException e) { em.persist(user); }
    }

}