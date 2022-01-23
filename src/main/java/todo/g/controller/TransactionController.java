package todo.g.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import todo.g.model.Transaction;
import todo.g.service.ITransactionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    ITransactionService iTransactionService;



//    @PostMapping("/add")
//    public ResponseEntity<?> createTransactions(@Valid @RequestBody(required = true) Transaction transaction) {
//
//        return new ResponseEntity<>(iTransactionService.createTransactions(transaction), HttpStatus.CREATED);
//    }

//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    //    @ApiOperation(value = "Get All Comments By Post ID REST API")
    @GetMapping("/transaction/{userId}")
    //     get all transactions by user id
    public List<Transaction> getTransactionByUserId(@PathVariable(value = "userId") Long userId) {
        return iTransactionService.getTransactionByUserId(userId);
    }

//    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/transaction/{userId}")
    public ResponseEntity<Transaction> createTransaction(@Valid @PathVariable(value = "userId") Long userId,
                                                    @RequestBody Transaction transaction) {
        return new ResponseEntity<>(iTransactionService.createTransaction(userId, transaction), HttpStatus.CREATED);
    }


}
