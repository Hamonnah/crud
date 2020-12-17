package com.crud.tasks.trello.facade;


import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test list", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "Test list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "Test list", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> fetchedTrelloBoardsDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(fetchedTrelloBoardsDtos);
        assertEquals(0, fetchedTrelloBoardsDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test list", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "Test list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "Test list", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardsDto);
        assertEquals(1, trelloBoardsDto.size());

        trelloBoardsDto.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("Test list", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("Test list", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldCreateCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("Test", "Test description,", "1", "1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test description,", "1", "1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Test", "testurl.com");

        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdCard);
        when(trelloMapper.mapToTrelloCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToTrelloCardDto(trelloCard)).thenReturn(trelloCardDto);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //Then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("Test", createdTrelloCardDto.getName());
        assertEquals("testurl.com", createdTrelloCardDto.getShortUrl());
    }

}
