package todo.g.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.g.model.Transaction;
import todo.g.repository.TransactionRepository;
import todo.g.service.ITransactionService;


@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public Transaction createTransaction(Transaction transaction) {

      return  transactionRepository.save(transaction);

    }
}
