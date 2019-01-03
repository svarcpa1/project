package cz.uhk.ppro.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workplace {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Hall hall;
    @OneToMany(mappedBy = "workplace")
    private List<Worker> workers = new ArrayList<>();
    @OneToMany(mappedBy = "workplace")
    private List<Document> documents = new ArrayList<>();


    public Workplace() {
    }

    public Workplace(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
