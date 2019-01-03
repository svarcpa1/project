package cz.uhk.ppro.project.model;

import java.util.ArrayList;
import java.util.List;


public class Hall {
    private String name;
    private int id;
    private List<Workplace> workplaces = new ArrayList<>();

    public Hall(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }
}

