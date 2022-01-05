package todo.g.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.g.model.Task;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private boolean finished;

    public static TaskDto taskToDto(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .finished(task.getFinished())
                .build();
    }
}
