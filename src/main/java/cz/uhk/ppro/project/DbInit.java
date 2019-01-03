package cz.uhk.ppro.project;

import cz.uhk.ppro.project.model.Document;
import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbInit {

	public static void main(String[] args) {
		// Create the EntityManagerFactory & EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMine");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Hall h = new Hall("plechy");
		Workplace wp1 = new Workplace("pracoviste1");
		Workplace wp2 = new Workplace("pracoviste2");
		Document doc1 = new Document("dokument1", "xxx");
		Document doc2 = new Document("dokument2", "xxx");
		Worker wrk1 = new Worker("Pavel", "ŠVARC" , "BOSS");
		Worker wrk2 = new Worker("Ota", "Černý" , "Otrok");

		h.getWorkplaces().add(wp1);
		h.getWorkplaces().add(wp2);
		wp1.setHall(h);
		wp2.setHall(h);
		wp1.getDocuments().add(doc1);
		wp2.getDocuments().add(doc2);
		doc1.setWorkplace(wp1);
		doc2.setWorkplace(wp2);
		wp1.getWorkers().add(wrk1);
		wp2.getWorkers().add(wrk2);
		wrk1.setWorkplace(wp1);
		wrk2.setWorkplace(wp2);
		wrk1.getDocumentsCreated().add(doc1);
		wrk2.getDocumentsCreated().add(doc2);
		doc1.setWorketCreated(wrk1);
		doc2.setWorketCreated(wrk2);

		em.persist(h);
		em.persist(wp1);
		em.persist(wp2);
		em.persist(doc1);
		em.persist(doc2);
		em.persist(wrk1);
		em.persist(wrk2);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
