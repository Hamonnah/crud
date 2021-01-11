package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {

        //Given
        TaskDto taskDto = new TaskDto(1L, "taskDto", "test content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(1L, task.getId().doubleValue(), 1);
        Assert.assertEquals("taskDto", task.getTitle());
        Assert.assertEquals("test content", task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {

        //Given
        Task task = new Task(2L, "task", "test content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(2L, taskDto.getId().doubleValue(), 1);
        Assert.assertEquals("task", taskDto.getTitle());
        Assert.assertEquals("test content", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {

        //Given
        Task task = new Task(2L, "task", "test content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        //When
        List<TaskDto> dtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        Assert.assertEquals(1, dtoList.size());
        Assert.assertEquals(2L, dtoList.get(0).getId().longValue());
        Assert.assertEquals("task", dtoList.get(0).getTitle());
        Assert.assertEquals("test content", dtoList.get(0).getContent());
    }

}