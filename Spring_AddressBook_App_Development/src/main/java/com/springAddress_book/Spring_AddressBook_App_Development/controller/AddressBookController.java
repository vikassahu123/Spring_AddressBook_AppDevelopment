package com.springAddress_book.Spring_AddressBook_App_Development.controller;

import com.springAddress_book.Spring_AddressBook_App_Development.model.AddressBookEntry;
import com.springAddress_book.Spring_AddressBook_App_Development.service.AddressBookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressBookEntry>> getAllContacts() {
        return ResponseEntity.ok(service.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntry> getContactById(@PathVariable Long id) {
        return service.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBookEntry> addContact(@RequestBody AddressBookEntry contact) {
        return ResponseEntity.ok(service.addContact(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookEntry> updateContact(@PathVariable Long id, @RequestBody AddressBookEntry contact) {
        return service.updateContact(id, contact)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        return service.deleteContact(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}