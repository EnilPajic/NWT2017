package com.enil.haberservices.Entity;


import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "surveys")
public class Survey
{
    @Id
    @GeneratedValue
    public long id;
    public String name, description;
    public boolean active;
    @Column (name = "date_created")
    public Timestamp datecreated;
    public Timestamp expires;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "survey")
    public List<VoteItems> voteitems;

    public Survey() {    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", datecreated=" + datecreated +
                ", expires=" + expires +
                ", voteItems=" + voteitems +
                '}';
    }
}
