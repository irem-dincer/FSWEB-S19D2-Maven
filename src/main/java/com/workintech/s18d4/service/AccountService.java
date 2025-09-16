package com.workintech.s18d4.service;
import com.workintech.s18d4.entity.Account;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public interface AccountService {

    List<Account> findAll();
    Account find(Long id);
    Account save(Account account);
    Account delete(Long id);

}
