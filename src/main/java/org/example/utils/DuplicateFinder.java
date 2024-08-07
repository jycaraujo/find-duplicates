package org.example.utils;

import org.example.model.Contact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateFinder {

    private static double calculateMatchScore(Contact c1, Contact c2) {
        double score = 0.0;

        if (c1.getEmail().equalsIgnoreCase(c2.getEmail())) {
            score += 0.5;
        }

        if (c1.getName().equalsIgnoreCase(c2.getName()) || c1.getName1().equalsIgnoreCase(c2.getName1())) {
            score += 0.3;
        }

        if (c1.getPostalZip().equalsIgnoreCase(c2.getPostalZip()) || c1.getAddress().equalsIgnoreCase(c2.getAddress())) {
            score += 0.2;
        }

        return score;
    }

    private static String generatePairKey(String id1, String id2) {
        if (id1.compareTo(id2) < 0) {
            return id1 + "-" + id2;
        } else {
            return id2 + "-" + id1;
        }
    }

    public static List<DuplicateResult> findDuplicates(List<Contact> contacts) {
        List<DuplicateResult> results = new ArrayList<>();
        Set<String> processedPairs = new HashSet<>();
        for (Contact contact : contacts) {
            for (Contact other : contacts) {
                if (contact != other) {
                    double score = calculateMatchScore(contact, other);
                    if (score > 0) {
                        String pairKey = generatePairKey(contact.getContactId(), other.getContactId());
                        if (!processedPairs.contains(pairKey)) {
                            processedPairs.add(pairKey);
                            if (score == 0.5) {
                                results.add(new DuplicateResult(contact.getContactId(), other.getContactId(), "Medium"));
                            } else if (score > 0.5) {
                                results.add(new DuplicateResult(contact.getContactId(), other.getContactId(), "High"));
                            } else if (score < 0.5) {
                                results.add(new DuplicateResult(contact.getContactId(), other.getContactId(), "Low"));
                            }
                        }
                    }
                }
            }
        }

        return results;
    }

    public static class DuplicateResult {
        private final String contactId;
        private final String duplicated;
        private final String score;

        public DuplicateResult(String contactId, String duplicated, String score) {
            this.contactId = contactId;
            this.duplicated = duplicated;
            this.score = score;
        }

        public String getDuplicated() {
            return duplicated;
        }

        public String getContactId() {
            return contactId;
        }

        public String getScore() {
            return score;
        }
    }

}
