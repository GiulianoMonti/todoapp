package todo.g.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import todo.g.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserId(Sort createdAt, Long userId);
//    Transaction createTransaction(Long userId, Transaction transaction);

}
