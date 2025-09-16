package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping({"/workintech/accounts", "/account"})
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<AccountResponse> getAll() {
        List<Account> accounts = accountService.findAll();
        return accounts.stream()
                .map(this::convertToAccountResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id) {
        Account account = accountService.find(id);
        if (account == null) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        return convertToAccountResponse(account);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
        account.setCustomer(customer);
        Account saved = accountService.save(account);
        return convertToAccountResponse(saved);
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }

        account.setCustomer(customer);
        Account updated = accountService.save(account);
        return convertToAccountResponse(updated);
    }

    @DeleteMapping("/{id}")
    public AccountResponse remove(@PathVariable Long id) {
        Account account = accountService.find(id);
        if (account != null) {
            accountService.delete(id);
            return convertToAccountResponse(account);
        } else {
            throw new RuntimeException("Account not found with id: " + id);
        }
    }

    private AccountResponse convertToAccountResponse(Account account) {
        CustomerResponse customerResponse = new CustomerResponse(
                account.getCustomer().getId(),
                account.getCustomer().getEmail(),
                account.getCustomer().getSalary()
        );

        return new AccountResponse(
                account.getId().toString(),
                account.getAccountName(),
                account.getMoneyAmount(),
                customerResponse
        );
    }
}