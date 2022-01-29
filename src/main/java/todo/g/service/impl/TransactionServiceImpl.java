package todo.g.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import todo.g.exception.ResourceNotFoundException;
import todo.g.model.Transaction;
import todo.g.model.User;
import todo.g.repository.TransactionRepository;
import todo.g.repository.UserRepository;
import todo.g.service.ITransactionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;


//    @Override
//    public Transaction createTransactions(Transaction transaction) {
//
//        return transactionRepository.save(transaction);
//
//    }

    @Override
    public Transaction createTransaction(Long userId, Transaction transaction) {

//        Transaction transactions = new Transaction();

        // retrieve post entity by id
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Transaction", "id", userId));

        // set post to comment entity
        transaction.setUser(user);



        // comment entity to DB
        System.out.println(user.getId());
        System.out.println(user.getEmail());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByUserId(Long userId) {
        // retrieve comments by postId
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        // convert list of comment entities to list of comment dto's
        return transactions;
    }

}
