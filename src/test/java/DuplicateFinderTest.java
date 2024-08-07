import org.example.model.Contact;
import org.example.utils.DuplicateFinder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DuplicateFinderTest {

    @Test
    void testFindDuplicates() {
        Contact contact1 = new Contact("1", "Pam", "Beasly", "pambeesly@example.com", "12345", "123 Main St");
        Contact contact2 = new Contact("2", "Pam", "Beesly", "pambeesly@example.com", "12345", "123 Main St");
        Contact contact3 = new Contact("3", "P.", "Beesly", "pambeesly@example.com", "67890", "456 Main St");
        Contact contact4 = new Contact("4", "Pam", "Beesly", "beasly@example.com", "67890", "456 Main St");

        List<Contact> contacts = List.of(contact1, contact2, contact3, contact4);

        List<DuplicateFinder.DuplicateResult> results = DuplicateFinder.findDuplicates(contacts);

        assertEquals(6, results.size());

        assertTrue(results.stream().anyMatch(r -> r.getContactId().equals("1") && r.getDuplicated().equals("2") && r.getScore().equals("High")));
        assertTrue(results.stream().anyMatch(r -> r.getContactId().equals("1") && r.getDuplicated().equals("4") && r.getScore().equals("Low")));
        assertTrue(results.stream().anyMatch(r -> r.getContactId().equals("3") && r.getDuplicated().equals("4") && r.getScore().equals("Medium")));
    }
}
