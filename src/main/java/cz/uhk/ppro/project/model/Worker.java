package cz.uhk.ppro.project.model;

import cz.uhk.ppro.project.validation.WorkplaceConstraint;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surName;
    @WorkplaceConstraint
    @ManyToOne
    private Workplace workplace;
    private String position;
    @OneToMany(mappedBy = "workerCreated")
    private List<Document> documentsCreated = new ArrayList<>();

    public Worker() {
        id=0;
    }

    public Worker(String firstName, String surName, String position) {
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getFullName() {
        return firstName + " " + surName;
    }

    public void setSurName(String sureName) {
        this.surName = sureName;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Document> getDocumentsCreated() {
        return documentsCreated;
    }

    public void setDocumentsCreated(List<Document> documentsCreated) {
        this.documentsCreated = documentsCreated;
    }

    public void removeDocument(Document document) {
        documentsCreated.remove(document);
        if (document != null) {
            document.setWorkerCreated(null);
        }
    }
}
