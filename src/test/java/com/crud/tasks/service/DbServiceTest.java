package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        List<Task> taskList = Arrays.asList(task);

        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> theList = dbService.getAllTasks();

        //Then
        assertNotNull(theList);
        assertEquals(1, theList.size());
    }

    @Test
    public void getTaskWhichDoesNotExistTest() {
        //Given
        when(repository.findById(2L)).thenReturn(Optional.empty());

        //When
        Optional<Task> result = dbService.getTask(2L);

        //Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given

        //When
        dbService.deleteTask(2L);

        //Then
        verify(repository, times(1)).deleteById(2L);
    }
}
