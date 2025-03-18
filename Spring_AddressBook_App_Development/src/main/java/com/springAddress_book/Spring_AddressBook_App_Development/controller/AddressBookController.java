package com.springAddress_book.Spring_AddressBook_App_Development.controller;





import com.springAddress_book.Spring_AddressBook_App_Development.dto.AddressBookDTO;
import com.springAddress_book.Spring_AddressBook_App_Development.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private final AddressBookService service;

    @Autowired
    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllContacts() {
        return ResponseEntity.ok(service.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getContactById(@PathVariable Long id) {
        return service.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBookDTO> addContact(@RequestBody AddressBookDTO contactDTO) {
        return ResponseEntity.ok(service.addContact(contactDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookDTO> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO contactDTO) {
        return service.updateContact(id, contactDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        return service.deleteContact(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}