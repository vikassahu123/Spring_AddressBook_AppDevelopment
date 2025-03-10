package com.springAddress_book.Spring_AddressBook_App_Development.Repository;


import com.springAddress_book.Spring_AddressBook_App_Development.model.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntry, Long> {
}
