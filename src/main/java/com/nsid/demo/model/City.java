package com.nsid.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID;
    private String Name;
    private String District;
    private String Population;

    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getDistrict() {
        return District;
    }
    public void setDistrict(String District) {
        this.District = District;
    }
    public String getPopulation() {
        return Population;
    }
    public void setPopulation(String Population) {
        this.Population = Population;
    }


}