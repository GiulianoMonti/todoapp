package todo.g.service;

import todo.g.model.Transaction;

import java.util.List;

public interface ITransactionService {

//    Transaction createTransactions(Transaction transaction);
    List<Transaction> getTransactionByUserId(Long userId);
    Transaction createTransaction(Long userId, Transaction transaction);
    ;

}
