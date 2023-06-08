package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.model.BankAccount;
import com.ensa.deliveryapp.repository.BankAccountRepository;
import com.github.javafaker.Faker;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class BankAccountService {
private final BankAccountRepository bankAccountRepository;

    public BankAccount processPayment(String cardNumber,double amount){
        BankAccount bankAccount = bankAccountRepository.findByCardNumber(cardNumber);
        if(bankAccount!=null && bankAccount.getBalance()>amount){
            double currentBalance = bankAccount.getBalance();
            bankAccount.setBalance(currentBalance-amount);
            return bankAccount;
        }
    return null;

    }

    public void initProducts() {
//        Faker faker = new Faker();
//        List<BankAccount> bankAccounts = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            BankAccount bankAccount = new BankAccount();
//            bankAccount.setAccountNumber(faker.finance().iban());
//            bankAccount.setAccountHolderName(faker.name().fullName());
//            bankAccount.setBalance(faker.number().randomDouble(2, 1000, 10000));
//            bankAccount.setCardNumber(faker.finance().creditCard());
//
//            bankAccounts.add(bankAccount);
//        }

//        bankAccountRepository.saveAll(bankAccounts);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountHolderName("Salesforce Team");
        bankAccount.setAccountNumber("BG65STHL614889Co13H03H");
        bankAccount.setCardCVV("333");
        bankAccount.setCardDate("12/33");
        bankAccount.setBalance(10000);
        bankAccount.setCardNumber("6502-6241-4114-1956-6083");
        bankAccountRepository.save(bankAccount);
    }
}
