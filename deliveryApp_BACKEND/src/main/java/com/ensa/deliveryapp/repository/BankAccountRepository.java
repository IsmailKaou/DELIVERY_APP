package com.ensa.deliveryapp.repository;

import com.ensa.deliveryapp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    BankAccount findByCardNumber(String cardNumber);
}
