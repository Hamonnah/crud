package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloBoard;
import com.crud.tasks.trello.facade.TrelloList;
import com.crud.tasks.trello.facade.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {

        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "Test list", false));
        trelloListsDto.add(new TrelloListDto("2", "Test list two", false));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("3", "Board", trelloListsDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Than
        Assert.assertEquals(1, trelloBoards.size());
        Assert.assertEquals("3", trelloBoards.get(0).getId());
        Assert.assertEquals("Board", trelloBoards.get(0).getName());
        Assert.assertEquals(2, trelloBoards.stream().mapToLong(b -> b.getLists().size()).sum());
    }

    @Test
    public void mapToBoardsDtoTest() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test list", false));
        trelloLists.add(new TrelloList("2", "Test list two", false));

        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("3", "Board", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoards = trelloMapper.mapToBoardsDto(trelloBoard);

        //Than
        Assert.assertEquals(1, trelloBoards.size());
        Assert.assertEquals("3", trelloBoards.get(0).getId());
        Assert.assertEquals("Board", trelloBoards.get(0).getName());
        Assert.assertEquals(2, trelloBoards.stream().mapToLong(b -> b.getLists().size()).sum());
    }

    @Test
    public void mapToListTest() {

        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "Test list", false));
        trelloListsDto.add(new TrelloListDto("2", "Test list two", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("1", trelloLists.get(0).getId());
        Assert.assertEquals("2", trelloLists.get(1).getId());
        Assert.assertEquals("Test list", trelloLists.get(0).getName());
        Assert.assertEquals("Test list two", trelloLists.get(1).getName());
        Assert.assertFalse(trelloLists.get(0).isClosed());
        Assert.assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void mapToListDtoTest() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test list", false));
        trelloLists.add(new TrelloList("2", "Test list two", false));

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(2, trelloListsDto.size());
        Assert.assertEquals("1", trelloListsDto.get(0).getId());
        Assert.assertEquals("2", trelloListsDto.get(1).getId());
        Assert.assertEquals("Test list", trelloListsDto.get(0).getName());
        Assert.assertEquals("Test list two", trelloListsDto.get(1).getName());
        Assert.assertFalse(trelloListsDto.get(0).isClosed());
        Assert.assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    public void mapToTrelloCardTest() {

        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Name", "Description", "123", "456");
        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(cardDto);
        //Then
        Assert.assertEquals("Name", trelloCard.getName());
        Assert.assertEquals("Description", trelloCard.getDescription());
        Assert.assertEquals("123", trelloCard.getPos());
        Assert.assertEquals("456", trelloCard.getListId());
    }

    @Test
    public void mapToTrelloCardDtoTest() {

        //Given
        TrelloCard card = new TrelloCard("Name", "Description", "123", "456");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(card);
        //Then
        Assert.assertEquals("Name", trelloCardDto.getName());
        Assert.assertEquals("Description", trelloCardDto.getDescription());
        Assert.assertEquals("123", trelloCardDto.getPos());
        Assert.assertEquals("456", trelloCardDto.getListId());
    }

}