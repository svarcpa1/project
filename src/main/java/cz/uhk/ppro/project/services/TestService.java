package cz.uhk.ppro.project.services;

import cz.uhk.ppro.project.model.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TestService {

    @PersistenceContext
    private EntityManager em;

    public Hall findById(long id) {
        return em.find(Hall.class,id);
    }

    public void saveEntity(Object entity) {
        em.persist(entity);
    }

    public List<Hall> findAllHalls() {
        return em.createQuery("SELECT h FROM Hall h", Hall.class).getResultList();
    }

}
