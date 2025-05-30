package tech.wvs.desafiobycoders.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.wvs.desafiobycoders.domain.Transaction;
import tech.wvs.desafiobycoders.domain.enums.TransactionType;
import tech.wvs.desafiobycoders.repository.TransactionRepository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void processFile(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                Transaction transaction = parseLine(line);
                transactionRepository.save(transaction);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to process the file", e);
        }
    }

    private Transaction parseLine(String line) {
        Transaction transaction = new Transaction();

        int typeCode = Integer.parseInt(line.substring(0, 1));
        TransactionType type = TransactionType.getByCode(typeCode);

        transaction.setType(type.getDescription());
        transaction.setCategory(type.getCategory());
        transaction.setOperationSign(type.getOperationSign());

        transaction.setDate(LocalDate.parse(line.substring(1, 9), DateTimeFormatter.ofPattern("yyyyMMdd")));
        transaction.setAmount(new BigDecimal(line.substring(9, 19)).divide(new BigDecimal(100)));
        transaction.setCpf(line.substring(19, 30));
        transaction.setCard(line.substring(30, 42));
        transaction.setHour(LocalTime.parse(line.substring(42, 48), DateTimeFormatter.ofPattern("HHmmss")));
        transaction.setStoreOwner(line.substring(48, 62).trim());
        transaction.setStoreName(line.substring(62).trim());

        return transaction;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
