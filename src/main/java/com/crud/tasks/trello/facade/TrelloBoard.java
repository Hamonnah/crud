package com.crud.tasks.trello.facade;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TrelloBoard {

    private String id;
    private String name;
    private List<TrelloList> lists;
}
