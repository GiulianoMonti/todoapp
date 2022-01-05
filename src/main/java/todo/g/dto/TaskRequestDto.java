package todo.g.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaskRequestDto {
    @NotEmpty(message = "name should not be null or empty")
    private String name;

    @NotEmpty(message = "folderId should not be null or empty")
    private Long folderId;
}
