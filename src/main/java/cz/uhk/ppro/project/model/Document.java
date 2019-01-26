package cz.uhk.ppro.project.model;

import cz.uhk.ppro.project.validation.WorkerConstraint;
import cz.uhk.ppro.project.validation.WorkerValidator;
import cz.uhk.ppro.project.validation.WorkplaceConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String name;

    @WorkplaceConstraint
    @ManyToOne
    private Workplace workplace;

    @WorkerConstraint
    @ManyToOne
    private Worker workerCreated;

    private String filePath;
    @Lob
    private byte[] fileData;
    @NotNull
    @DateTimeFormat(pattern="MM/dd/YY")
    private Date dateCreated;
    @NotNull
    @DateTimeFormat(pattern="MM/dd/YY")
    private Date dateExpired;
    private String description;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Document() {
        id=0;
    }

    public Document(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Worker getWorkerCreated() {
        return workerCreated;
    }

    public void setWorkerCreated(Worker workerCreated) {
        this.workerCreated = workerCreated;
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
