package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String content;

    public TaskDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
