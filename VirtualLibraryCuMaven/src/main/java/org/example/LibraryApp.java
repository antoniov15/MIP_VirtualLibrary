package org.example;

import org.example.modules.*;
import java.util.Scanner;
import java.io.IOException;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            System.out.print("\n");

            System.out.print("/--------------------------------------------------\\ \n");
            System.out.print("|                       MENU                       | \n");
            System.out.print("| 1. Add Book                                      | \n");
            System.out.print("| 2. Add Magazine                                  | \n");
            System.out.print("| 3. Add Comic                                     | \n");
            System.out.print("| 4. List Items                                    | \n");
            System.out.print("| 5. Edit Item                                     | \n");
            System.out.print("| 6. Save to File                                  | \n");
            System.out.print("| 7. Load from File                                | \n");
            System.out.print("| 8. Create New File                               | \n");
            System.out.print("| 9. Delete File                                   | \n");
            System.out.print("| 10. Exit                                         | \n");
            System.out.print("\\--------------------------------------------------/ \n\n");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter title, author, price, pages:");
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    double price = scanner.nextDouble();
                    int pages = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    library.addItem(new Book(title, author, price, pages));
                }
                case 2 -> {
                    System.out.println("Enter title, author, price, issue:");
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    String issue = scanner.nextLine();
                    library.addItem(new Magazine(title, author, price, issue));
                }
                case 3 -> {
                    System.out.println("Enter title, author, price, artist:");
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    String artist = scanner.nextLine();
                    library.addItem(new Comic(title, author, price, artist));
                }
                case 4 -> library.listItems();
                case 5 -> {
                    library.listItems();
                    System.out.println("Enter the index of the item to edit:");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    try {
                        Item item = library.getItemByIndex(index);
                        if (item instanceof Book book) {
                            System.out.println("Enter new title, author, price, pages:");
                            String newTitle = scanner.nextLine();
                            String newAuthor = scanner.nextLine();
                            double newPrice = scanner.nextDouble();
                            int newPages = scanner.nextInt();
                            scanner.nextLine();
                            book.edit(newTitle, newAuthor, newPrice, newPages);
                        } else if (item instanceof Magazine magazine) {
                            System.out.println("Enter new title, author, price, issue:");
                            String newTitle = scanner.nextLine();
                            String newAuthor = scanner.nextLine();
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();
                            String newIssue = scanner.nextLine();
                            magazine.edit(newTitle, newAuthor, newPrice, newIssue);
                        } else if (item instanceof Comic comic) {
                            System.out.println("Enter new title, author, price, artist:");
                            String newTitle = scanner.nextLine();
                            String newAuthor = scanner.nextLine();
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();
                            String newArtist = scanner.nextLine();
                            comic.edit(newTitle, newAuthor, newPrice, newArtist);
                        }
                        System.out.println("Item updated successfully.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid item index.");
                    }
                }
                case 6 -> {
                    System.out.println("Enter filename to save:");
                    String filename = scanner.nextLine();
                    try {
                        library.saveToFile(filename);
                        System.out.println("Data saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.println("Enter filename to load:");
                    String filename = scanner.nextLine();
                    try {
                        library.loadFromFile(filename);
                        System.out.println("Data loaded successfully.");
                    } catch (IOException e) {
                        System.out.println("Error loading data: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.println("Enter filename to create:");
                    String filename = scanner.nextLine();
                    try {
                        library.createFile(filename);
                        System.out.println("File created successfully.");
                    } catch (IOException e) {
                        System.out.println("Error creating file: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.println("Enter filename to delete:");
                    String filename = scanner.nextLine();
                    try {
                        library.deleteFile(filename);
                    } catch (IOException e) {
                        System.out.println("Error deleting file: " + e.getMessage());
                    }
                }
                case 10 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}