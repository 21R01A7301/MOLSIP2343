// Java Programming intern
// MicrOrbital Labs Internship Works Batch SIP2343
// Task 1 - Contact Management System
//Project code - MOLAP624
//The application, that write / read / update the contact information details to the csv file.
// LinkedIn profile - https://www.linkedin.com/in/aditi-bengani/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        CSVFile csvFile;
        try {
            csvFile = new CSVFile("contacts.csv");
        } catch (IOException e) {
            System.err.println("Error creating or opening the CSV file.");
            return;
        }
        List<Contact> contacts;
        try {
            contacts = csvFile.readContacts();
        } catch (IOException e) {
            System.err.println("Error reading contacts from the CSV file.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Management System");
            System.out.println("1. Display Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    displayContacts(contacts);
                    break;
                case 2:
                    addContact(contacts, csvFile, scanner);
                    break;
                case 3:
                    searchContact(contacts, scanner);
                    break;
                case 4:
                    updateContact(contacts, csvFile, scanner);
                    break;
                case 5:
                    deleteContact(contacts, csvFile, scanner);
                    break;
                case 6:
                    System.out.println("Exiting the Contact Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("\n(No contacts found)");
        } else {
            System.out.println("\nContacts:");
            for (Contact contact : contacts) {
                printContactInfo(contact);
            }
        }
    }

    private static void printContactInfo(Contact contact) {
        System.out.println("Name: " + contact.getName());
        System.out.println("Phone Number: " + contact.getPhoneNumber());
        System.out.println("Email: " + contact.getEmail());
        System.out.println();
    }

    private static void addContact(List<Contact> contacts, CSVFile csvFile, Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber, email);
        contacts.add(newContact);

        try {
            csvFile.writeContacts(contacts);
            System.out.println("Contact added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing contacts to the CSV file.");
        }
    }

    private static void searchContact(List<Contact> contacts, Scanner scanner) {
        System.out.println("Enter the name to search: ");
        String searchName = scanner.nextLine().trim();
        boolean found = false;

        System.out.println("\nSearch Results:");
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                printContactInfo(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
    }

    private static void updateContact(List<Contact> contacts, CSVFile csvFile, Scanner scanner) {
        System.out.print("Enter the name to update: ");
        String updateName = scanner.nextLine().trim();

        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(updateName)) {
                System.out.println("Enter new information for " + contact.getName() + ":");
                System.out.print("New Name: ");
                String newName = scanner.nextLine();
                System.out.print("New Phone Number: ");
                String newPhoneNumber = scanner.nextLine();
                System.out.print("New Email: ");
                String newEmail = scanner.nextLine();

                contact.setName(newName);
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmail(newEmail);

                contactFound = true;
                try {
                    csvFile.writeContacts(contacts);
                    System.out.println("Contact updated successfully.");
                } catch (IOException e) {
                    System.err.println("Error writing contacts to the CSV file.");
                }
                break;
            }
        }

        if (!contactFound) {
            System.out.println("No matching contact found for updating.");
        }
    }

    private static void deleteContact(List<Contact> contacts, CSVFile csvFile, Scanner scanner) {
        System.out.print("Enter the name to delete: ");
        String deleteName = scanner.nextLine().trim();

        List<Contact> contactsToDelete = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(deleteName)) {
                contactsToDelete.add(contact);
            }
        }

        if (contactsToDelete.isEmpty()) {
            System.out.println("No matching contacts found.");
        } else {
            contacts.removeAll(contactsToDelete);

            try {
                csvFile.writeContacts(contacts);
                System.out.println("Contacts deleted successfully.");
            } catch (IOException e) {
                System.err.println("Error writing contacts to the CSV file.");
            }
        }
    }
}

class CSVFile {
    private File file;

    public CSVFile(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public List<Contact> readContacts() throws IOException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    Contact contact = new Contact(data[0], data[1], data[2]);
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }

    public void writeContacts(List<Contact> contacts) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail() + "\n");
            }
        }
    }
}
