package cz.uhk.ppro.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String name;

    public Role() {
        id=0;
    }
    public Role(@NotEmpty String name) {
        this.name = name;
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
}
