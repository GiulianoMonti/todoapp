package todo.g.service;

import todo.g.dto.TaskDto;
import todo.g.dto.TaskRequestDto;

public interface ITaskService {
    TaskDto createTask(TaskRequestDto request);

    TaskDto editTask(Long id, String name);

    void deleteTask(Long id);

    void checkTask(Long id);
}
