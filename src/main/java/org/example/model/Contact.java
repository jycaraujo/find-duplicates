package org.example.model;

public class Contact {
    private final String contactId;
    private final String name;
    private final String name1;
    private final String email;
    private final String postalZip;
    private final String address;

    public Contact(String contactId, String name, String name1, String email, String postalZip, String address) {
        this.contactId = contactId;
        this.name = name;
        this.name1 = name1;
        this.email = email;
        this.postalZip = postalZip;
        this.address = address;
    }

    public String getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getName1() {
        return name1;
    }

    public String getEmail() {
        return email;
    }

    public String getPostalZip() {
        return postalZip;
    }

    public String getAddress() {
        return address;
    }

}
