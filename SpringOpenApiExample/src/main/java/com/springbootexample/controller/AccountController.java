package com.springbootexample.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.springbootexample.api.AccountsApi;
import com.springbootexample.model.AccountDto;
import com.springbootexample.model.GetAccountsResponseDto;

@RestController
public class AccountController implements AccountsApi {
	@Override
	public ResponseEntity<GetAccountsResponseDto> getAccounts() {
		return ResponseEntity.ok(
				new GetAccountsResponseDto().items(Arrays.asList(createAccount("DEXXXX"), createAccount("ENYYYY"))));
	}

	private AccountDto createAccount(String iban) {
		AccountDto account = new AccountDto();
		account.setIban(iban);
		return account;
	}
}
