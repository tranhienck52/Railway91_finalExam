package com.data.controller;

import com.data.form.AccountCreateForm;
import com.data.entity.Account;
import com.data.repository.AccountRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AccountController {
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public ResponseEntity<?> create(@Valid @RequestBody AccountCreateForm accountCreateForm){
        Account account = modelMapper.map(accountCreateForm,Account.class);
//        Account account = new Account();
//        account.setUsername(accountCreateReq.getUsername());
        account.setPassword(passwordEncoder.encode(accountCreateForm.getPassword()));
//        account.setAddress(accountCreateReq.getAddress());
//        account.setEmail(accountCreateReq.getEmail());
        accountRepo.save(account);
        return ResponseEntity.ok("Dang ky thanh cong!");
    }
}
