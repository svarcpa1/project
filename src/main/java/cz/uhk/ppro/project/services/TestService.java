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

    public void saveEntity(Object hall) {
        em.persist(hall);
    }

    public List<Hall> findAllHalls() {
        return em.createQuery("SELECT h FROM Hall h", Hall.class).getResultList();
    }

    public void updateHall(Hall hall) {
        em.merge(hall);
    }

}
