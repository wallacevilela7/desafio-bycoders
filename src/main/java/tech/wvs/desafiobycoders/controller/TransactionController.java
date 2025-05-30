package tech.wvs.desafiobycoders.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.wvs.desafiobycoders.domain.Transaction;
import tech.wvs.desafiobycoders.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            transactionService.processFile(file);
            return ResponseEntity.ok("File processed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }


    @GetMapping(path = "/list")
    public ResponseEntity<List<Transaction>> listTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }
}
