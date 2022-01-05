package todo.g.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.g.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
