package com.example.Tagging.Model;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tags_demo")
public class Tags {

    @Id
    @Column(name = "Tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column (name = "Tag_name",
            nullable = false,
            columnDefinition = "Text")
    public String name;

    public Tags(@JsonProperty("name")String name) {
        this.name = name;
    }



    public Tags() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
