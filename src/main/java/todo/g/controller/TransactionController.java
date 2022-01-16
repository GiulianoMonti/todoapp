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

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    ITransactionService iTransactionService;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody(required = true) Transaction transaction) {

        return new ResponseEntity<>(iTransactionService.createTransaction(transaction), HttpStatus.CREATED);
    }


}
