package com.springAddress_book.Spring_AddressBook_App_Development.service;


import com.springAddress_book.Spring_AddressBook_App_Development.DTO.AddressBookDTO;
import com.springAddress_book.Spring_AddressBook_App_Development.Repository.AddressBookRepository;
import com.springAddress_book.Spring_AddressBook_App_Development.model.AddressBookEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final AddressBookRepository repository;

    public AddressBookService(AddressBookRepository repository) {
        this.repository = repository;
    }

    public List<AddressBookDTO> getAllContacts() {
        return repository.findAll().stream()
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getEmail(), contact.getPhone()))
                .collect(Collectors.toList());
    }

    public Optional<AddressBookDTO> getContactById(Long id) {
        return repository.findById(id)
                .map(contact -> new AddressBookDTO(contact.getName(), contact.getEmail(), contact.getPhone()));
    }

    public AddressBookDTO addContact(AddressBookDTO contactDTO) {
        AddressBookEntry contact = new AddressBookEntry(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        AddressBookEntry savedContact = repository.save(contact);
        return new AddressBookDTO(savedContact.getName(), savedContact.getEmail(), savedContact.getPhone());
    }

    public Optional<AddressBookDTO> updateContact(Long id, AddressBookDTO contactDTO) {
        return repository.findById(id).map(existingContact -> {
            existingContact.setName(contactDTO.getName());
            existingContact.setEmail(contactDTO.getEmail());
            existingContact.setPhone(contactDTO.getPhone());
            repository.save(existingContact);
            return new AddressBookDTO(existingContact.getName(), existingContact.getEmail(), existingContact.getPhone());
        });
    }

    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}