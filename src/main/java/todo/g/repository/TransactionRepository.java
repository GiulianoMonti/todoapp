package todo.g.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.g.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
