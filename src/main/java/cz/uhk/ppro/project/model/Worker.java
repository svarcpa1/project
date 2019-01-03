package cz.uhk.ppro.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Worker {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String sureName;
    @ManyToOne
    private Workplace workplace;
    private String position;
    @OneToMany
    private List<Document> documentsCreated = new ArrayList<>();

    public Worker() {
    }

    public Worker(String firstName, String sureName, String position) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
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
}
