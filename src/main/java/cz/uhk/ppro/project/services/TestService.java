package cz.uhk.ppro.project.services;

import cz.uhk.ppro.project.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public Role findRoleById(long id) {
        return em.find(Role.class,id);
    }

    public Worker findWorkerById(long id) {
        return em.find(Worker.class,id);
    }

    public Document findDocumentById(long id) {
        return em.find(Document.class,id);
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

    public List<Role> findAllRoles() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
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

    public byte[] loadDocumentFileById(long documentId){
        return em.find(Document.class,documentId).getFileData();
    }

    public Worker findUserAccount(String userName) {
        try {
            String sql = "Select e from " + Worker.class.getName() + " e " //
                    + " Where e.login = :userName ";

            Query query = em.createQuery(sql, Worker.class);
            query.setParameter("userName", userName);
            return (Worker) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "Select w.role.name from " + Worker.class.getName() + " w " //
                + " where w.id = :userId ";

        Query query = this.em.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
