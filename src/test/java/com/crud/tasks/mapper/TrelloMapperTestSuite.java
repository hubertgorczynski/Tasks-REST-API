package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals("1", trelloListDto.get(0).getId());
        assertEquals("test list 1", trelloListDto.get(0).getName());
        assertFalse(trelloListDto.get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", true);

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("test list 1", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        TrelloBoard trelloBoard = new TrelloBoard("3", "test board", trelloLists);

        List<TrelloBoard> trelloBoardLists = new ArrayList<>();
        trelloBoardLists.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoLists = trelloMapper.mapToBoardsDto(trelloBoardLists);

        //Then
        assertEquals(1, trelloBoardDtoLists.size());
        assertEquals("3", trelloBoardDtoLists.get(0).getId());
        assertEquals("test board", trelloBoardDtoLists.get(0).getName());
        assertEquals(2, trelloBoardDtoLists.get(0).getLists().size());
        assertEquals("1", trelloBoardDtoLists.get(0).getLists().get(0).getId());
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", true);

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("3", "test board", trelloListsDto);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("3", trelloBoardList.get(0).getId());
        assertEquals("test board", trelloBoardList.get(0).getName());
        assertEquals(2, trelloBoardList.get(0).getLists().size());
        assertEquals("1", trelloBoardList.get(0).getLists().get(0).getId());
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "test description",
                "top", "3");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test card", trelloCardDto.getName());
        assertEquals("test description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("3", trelloCardDto.getListId());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "test description",
                "top", "2");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("test card", trelloCard.getName());
        assertEquals("test description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("2", trelloCard.getListId());
    }
}
