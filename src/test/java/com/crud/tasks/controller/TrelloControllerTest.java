package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Test;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    void shouldFetchEmptyTrelloBoards() throws Exception {

        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldFetchTrelloBoards() throws Exception {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test task", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test task", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("[0]", is("1")));
                .andExpect(jsonPath("$.name", is("Test task")));
                /*.andExpect(jsonPath("[0].lists", hasSize(1)))
                .andExpect(jsonPath("[0].lists[0].id", is(1)))
                .andExpect(jsonPath("[0].lists[0].name", is("Test List")))
                .andExpect(jsonPath("[0].lists[0].closed", is(false)));*/
    }

}