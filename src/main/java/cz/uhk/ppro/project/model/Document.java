package cz.uhk.ppro.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Document {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Workplace workplace;
    @ManyToOne
    private Worker worketCreated;
    private String filePath;
    private Date dateCreated;
    private Date dateExpired;

    public Document(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
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

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Worker getWorketCreated() {
        return worketCreated;
    }

    public void setWorketCreated(Worker worketCreated) {
        this.worketCreated = worketCreated;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }
}
