package cz.uhk.ppro.project.model;

import java.util.Date;

public class Document {
    private int id;
    private int name;
    private Workplace workplace;
    private Worker worketCreated;
    private String filePath;
    private Date dateCreated;
    private Date dateExpired;

    public Document(int name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
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
