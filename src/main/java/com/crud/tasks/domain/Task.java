package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@Entity
@Table(name = "tasks" )
public class Task {


    private Long id;


    private String title;


    private String content;

    public Task(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Task() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getTitle() {
        return title;
    }

    @Column(name = "description")
    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
