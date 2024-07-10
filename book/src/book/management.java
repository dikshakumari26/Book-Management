

package book;

import java.io.*;
import java.util.Scanner;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookName;
    private String writerName;
    private float cost;
    private int stock = 10;
    
    public Book(String bookname, String writername, float cost){
    this.bookName = bookname;
    this.writerName = writername;
    this.cost= cost;
    }
     public  void setStock(int number){
     this.stock = stock - number;
     }
     public  void addStock(int number){
     this.stock = stock + number;
     }
    
 public  String getBookname(){
    return bookName;
    }
  public  String getwritername(){
    return writerName;
    }
   public  float getBookcost(){
    return cost;
    }
    public  int getBookstock(){
    return stock;
    }
    @Override
    public String toString(){
    return "book name :\n " + bookName + "\nwritername :\n " + writerName + "\ncost : \n" + cost;
    }

    // Constructor, getters, and setters go here
    
}

public class management {

private static final String FILENAME = "C:\\Users\\Hp\\OneDrive\\WORKSPACE-2023\\bookmanagement\\inventory.txt";
    private static final String BACKUP_FILENAME = "C:\\Users\\Hp\\OneDrive\\WORKSPACE-2023\\bookmanagement\\backup.txt";
    private static final int THRESHOLD = 3;
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load data from the file into an array
        Book[] books = new Book[100];
        //books =  loadInventory();
        int arrayCount = 0;

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    arrayCount = addBook(books,arrayCount);
                    break;
                case 2:
                    searchBook(books,arrayCount);
                    break;
                case 3:
                    getBookList(books,arrayCount);
                    break;
                case 4:
                    checkInventory(books,arrayCount);
                    break;
                case 5:
                    checkBookRequirements(books,THRESHOLD,arrayCount);
                    break;
                case 6:
                    sellBook(books,arrayCount,THRESHOLD);
                    break;
                case 7:
                    backupData(books);
                    break;
                case 8:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 8);

        // Save data back to the file before exiting
        saveInventory(books);
    }

    private static void displayMenu() {
        System.out.println("\n========== Bookstore Management System ==========");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Get Book List");
        System.out.println("4. Check Inventory");
        System.out.println("5. Check Book Requirements");
        System.out.println("6. Sell Book");
        System.out.println("7. Backup Data");
        System.out.println("8. Exit");
    }

    private static Book[] loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (Book[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory. Starting with an empty inventory.");
            return new Book[0];
        }
    }

    private static void saveInventory(Book[] books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(books);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }

    private static int addBook(Book[] books, int count) {
        // Implementation for adding a book goes here
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name of the book: ");
        String bknm= sc.nextLine();
        System.out.println("enter the author name of the book: ");
        String authnm= sc.nextLine();
        System.out.println("enter the cost of the book: ");
        float cst= sc.nextFloat();
//        System.out.println(count);
//        System.out.println(books.length);
    // Check if there is enough space in the array
    if (count < books.length) {
        // Add the new book at the index indicated by 'count'
        books[count] = new Book(bknm, authnm, cst);
        System.out.println("Book added successfully.");
        System.out.println(books[count].toString());
        count++;
        saveInventory(books);
        return count;
    } else {
        System.out.println("Error: The inventory is full. Cannot add more books.");
    }
    return 0;
        
    }
    private static void searchBook(Book[] books,int count) {
        // Implementation for searching a book goes here
         Scanner sc = new Scanner(System.in);
         System.out.println("Name of the book and the author:  ");
         String booknm = sc.nextLine();
         String authorname = sc.nextLine();
         for(int i = 0; i<count;i++){
         if(books[i].getBookname().equalsIgnoreCase(booknm)){
                 //System.out.println("first if");
               if(books[i].getwritername().equalsIgnoreCase(authorname)){
         Book currentBook = books[i];
        if (currentBook != null) {
            System.out.println("Book " + (i + 1) + ":");
            System.out.println("Name: " + currentBook.getBookname());
            System.out.println("Author: " + currentBook.getwritername());
        }     
        }
                      else {
            System.out.println("Book is [Not Available]");
        }
      }   
       else {
            System.out.println("Book is [Not Available]");
        }
    }
         
    }

    private static void getBookList(Book[] books,int count) {
        // Implementation for getting the book list goes here
      
    for (int i = 0; i < count; i++) {
        Book currentBook = books[i];
        if (currentBook != null) {
            System.out.println("Book " + (i + 1) + ":");
            System.out.println("Name: " + currentBook.getBookname());
            System.out.println("Author: " + currentBook.getwritername());
            System.out.println("---------------------------");
        } else {
            System.out.println("Book " + (i + 1) + ": [Not Available]");
        }
    }
    }

    private static void checkInventory(Book[] books, int count) {
        // Implementation for checking inventory goes here
        for(int i = 0; i<count;i++){
            System.out.println("Name: " + books[i].getBookname());
            System.out.println("Author: " + books[i].getwritername());
            System.out.println("Stock : " + books[i].getBookstock());
            
            System.out.println("---------------------------");
        }
    }

    private static void checkBookRequirements(Book[] books,int thresold,int count) {
        // Implementation for checking book requirements goes here
        for(int i = 0; i<count;i++){
           if(books[i].getBookstock()<=thresold){
           System.out.printf("Restock the book %s\n",books[i].getBookname());
           }
           else{
            System.out.printf("No Restocking the book %s\n",books[i].getBookname());
           }
        }
        
    }

    private static void sellBook(Book[] books ,int count, int thresold) {
        // Implementation for selling a book goes here
         Scanner sc = new Scanner(System.in);
         System.out.println("Name of the book and the author:  ");
         String booknm = sc.nextLine();
         String authorname = sc.nextLine();
         float price; 
        System.out.println("number of sold out : ");
        int totalbook = sc.nextInt();
        sc.nextLine();
            for (int i = 0; i < count; i++) {
             if(books[i].getBookname().equalsIgnoreCase(booknm)){
                 //System.out.println("first if");
               if(books[i].getwritername().equalsIgnoreCase(authorname)){
               price = totalbook*(books[i].getBookcost() + (books[i].getBookcost()*0.20f));
               System.out.println("Selling Price of book: " + price);
               books[i].setStock(totalbook);
               if(books[i].getBookstock()<=thresold){
               System.out.printf("Restock the book %s\n",books[i].getBookname());
               System.out.println("do you want to restock the book? Yes / No :  ");
               //System.out.println();
               String val = sc.nextLine();
               if(val.equalsIgnoreCase("yes")){
               books[i].addStock(totalbook);
               }
               else{
               return;
               }
               }
               else{
                System.out.println("No restock needed");
               }
               }
             }
    }
       
        
    }

    private static void backupData(Book[] books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BACKUP_FILENAME))) {
            oos.writeObject(books);
            
            System.out.println("Data backed up successfully.");
        } catch (IOException e) {
            System.out.println("Error backing up data.");
        }
    }
    
}

