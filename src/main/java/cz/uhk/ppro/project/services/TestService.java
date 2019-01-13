package cz.uhk.ppro.project.services;

import cz.uhk.ppro.project.model.Document;
import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
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

    public Hall findHallById(long id) {
        return em.find(Hall.class,id);
    }

    public Workplace findWorkplaceById(long id) {
        return em.find(Workplace.class,id);
    }

    public void saveEntity(Object hall) {
        em.persist(hall);
    }

    public List<Hall> findAllHalls() {
        return em.createQuery("SELECT h FROM Hall h", Hall.class).getResultList();
    }

    public List<Workplace> findAllWorkplaces() {
        return em.createQuery("SELECT w FROM Workplace w", Workplace.class).getResultList();
    }

    public List<Worker> findAllWorkers() {
        return em.createQuery("SELECT w FROM Worker w", Worker.class).getResultList();
    }

    public void updateHall(Hall hall) {
        em.merge(hall);
    }

    public void updateWorkplace(Workplace wp) {
        em.merge(wp);
    }

    public void deleteHallById(long id) {
        Hall hall = em.find(Hall.class,id);
        em.remove(hall);
    }
}
