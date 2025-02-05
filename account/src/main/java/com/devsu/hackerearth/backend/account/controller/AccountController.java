package com.devsu.hackerearth.backend.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired()
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<AccountDto>> getAll() {
		// api/accounts
		// Get all accounts
		return ResponseEntity.ok(accountService.getAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccountDto> get(@PathVariable Long id) {
		// api/accounts/{id}
		// Get accounts by id
		return ResponseEntity.ok(accountService.getById(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
		// api/accounts
		// Create accounts
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(accountDto));
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<AccountDto> update(@RequestBody AccountDto accountDto) {
		// api/accounts/{id}
		// Update accounts
		return ResponseEntity.ok(accountService.update(accountDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AccountDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialAccountDto partialAccountDto) {
		// api/accounts/{id}
		// Partial update accounts
		return ResponseEntity.ok(accountService.partialUpdate(id, partialAccountDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/accounts/{id}
		// Delete accounts
		try {
			accountService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
