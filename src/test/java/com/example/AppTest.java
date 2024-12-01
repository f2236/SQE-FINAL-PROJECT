package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private ArrayList<App.Contact> contacts;

    @BeforeEach    
    public void setUp() {
        // This will run before each test, resetting the contacts list.
        contacts = new ArrayList<>();
    }

    // Test Case 1: Add Contact
    @Test
    public void testAddContact() {
        App.Contact contact = new App.Contact("John Doe", "1234567890");
        contacts.add(contact);

        assertEquals(1, contacts.size(), "Contact should be added to the list");
        assertEquals("John Doe", contacts.get(0).getName(), "Contact name should be 'John Doe'");
        assertEquals("1234567890", contacts.get(0).getPhoneNumber(), "Contact phone number should be '1234567890'");
    }

    // Test Case 2: View Contacts (No Contacts in List)
    @Test
    public void testViewContacts_NoContacts() {
        assertTrue(contacts.isEmpty(), "The contact list should be empty initially.");
    }

    // Test Case 3: View Contacts (Contacts in List)
    @Test
    public void testViewContacts_WithContacts() {
        App.Contact contact1 = new App.Contact("John Doe", "1234567890");
        App.Contact contact2 = new App.Contact("Jane Smith", "0987654321");

        contacts.add(contact1);
        contacts.add(contact2);

        assertFalse(contacts.isEmpty(), "The contact list should not be empty.");
        assertEquals(2, contacts.size(), "There should be two contacts in the list.");
    }

    // Test Case 4: Search Contact (Contact Found)
    @Test
    public void testSearchContact_Found() {
        App.Contact contact = new App.Contact("John Doe", "1234567890");
        contacts.add(contact);

        boolean contactFound = false;
        for (App.Contact c : contacts) {
            if (c.getName().equalsIgnoreCase("John Doe")) {
                contactFound = true;
                break;
            }
        }

        assertTrue(contactFound, "Contact with name 'John Doe' should be found.");
    }

    // Test Case 5: Search Contact (Contact Not Found)
    @Test
    public void testSearchContact_NotFound() {
        App.Contact contact = new App.Contact("John Doe", "1234567890");
        contacts.add(contact);

        boolean contactFound = false;
        for (App.Contact c : contacts) {
            if (c.getName().equalsIgnoreCase("Jane Smith")) {
                contactFound = true;
                break;
            }
        }

        assertFalse(contactFound, "Contact with name 'Jane Smith' should not be found.");
    }

    // Test Case 6: Add Duplicate Contact
    @Test
    public void testAddDuplicateContact() {
        App.Contact contact1 = new App.Contact("John Doe", "1234567890");
        App.Contact contact2 = new App.Contact("John Doe", "1234567890");

        contacts.add(contact1);
        contacts.add(contact2);

        assertEquals(2, contacts.size(), "Two contacts with the same name should be allowed in the list.");
    }

    // Test Case 7: Delete Contact
    @Test
    public void testDeleteContact() {
        App.Contact contact1 = new App.Contact("John Doe", "1234567890");
        App.Contact contact2 = new App.Contact("Jane Smith", "0987654321");

        contacts.add(contact1);
        contacts.add(contact2);

        // Now, let's remove the "John Doe" contact
        contacts.remove(contact1);

        // Assert that the contact list no longer contains "John Doe"
        assertFalse(contacts.contains(contact1), "Contact 'John Doe' should be removed from the list.");
        // Assert that the contact list still contains "Jane Smith"
        assertTrue(contacts.contains(contact2), "Contact 'Jane Smith' should still be in the list.");
    }
}
