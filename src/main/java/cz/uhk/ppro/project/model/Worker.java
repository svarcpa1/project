package cz.uhk.ppro.project.model;

import cz.uhk.ppro.project.validation.RoleConstraint;
import cz.uhk.ppro.project.validation.WorkplaceConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "login"))
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
    @OneToMany(mappedBy = "workerCreated")
    private List<Document> documentsCreated = new ArrayList<>();

    private String password;
    private String login;

    @RoleConstraint
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Role role;

    public Worker() {

    }
    public Worker(String firstName, String surName, Role role) {
        this.firstName = firstName;
        this.surName = surName;
        this.role = role;
        login = firstName + "." + surName;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
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
