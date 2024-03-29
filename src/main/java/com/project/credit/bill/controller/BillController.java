package com.project.credit.bill.controller;

import com.project.credit.bill.dto.BillResponseDto;
import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.bill.service.BillService;
import com.project.credit.card.exception.CardException;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getBill/{cardNumber}")
    public BillResponseDto autoGeneratedBillForMonth(@PathVariable("cardNumber") @NotBlank String cardNumber) throws TransactionException, BillException, DateException, CardException {
        Bill bill = billService.autoGenerateBillForMonth(cardNumber);
        BillResponseDto res = new BillResponseDto(bill);
        res.setTransactions(bill.getTransactions().stream().map(transactionService ::covertTransactionToTransactionResponseDto).toList());
        return res;
    }

    @GetMapping("/billPayment/{cardNumber}")
    public BillResponseDto billPayment(@PathVariable("cardNumber") @NotBlank String cardNumber) throws BillException, CardException, TransactionException {
        Bill bill = billService.billPayment(cardNumber);
        BillResponseDto res = new BillResponseDto(bill);
        res.setTransactions(bill.getTransactions().stream().map(transactionService ::covertTransactionToTransactionResponseDto).toList());
        return res;
    }

}
