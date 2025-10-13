// so this program utilise a console-based input/output interface for simplicity.
// we import the Scanner class; reads input from console 
import java.util.Scanner;

public class Main { // this runs our program + makes expense objects. executes java program ! 
    // we want to include all of our fields here 
    // thus the Main is our DRIVER which calls the methods we make when the user selects the option
    public static void main(String[] args) {
        // make an e.g., expense obj using the ExpenseTracker 
        ExpenseTracker tracker = new ExpenseTracker(); // we make an instance of our empty tracker to store expenses 
        boolean exit = false;  // will stop loop when user picks 'exit' option
        
        Scanner scanner = new Scanner(System.in);   // listener for user input
        while (!exit) { // keep menu rnning until user picks exit = true 
             // here we display our menu options 
            int choice = 0;

                while (choice != 7) { // we utilise a LOOP to continue until user clicks to exit menu option (7)
                    // here is the menu display
                    System.out.println("\n ~~ Expense Tracker Menu ~~"); // header
                    System.out.println("1. Add an expense");
                    System.out.println("2. View all expenses recorded");
                    System.out.println("3. Edit an expense");
                    System.out.println("4. Delete an expense");
                    System.out.println("5. Summarise by date");
                    System.out.println("6. Summarise by category");
                    System.out.println("7. Exit menu");
                    System.out.print("Please choose an option: ");
                    // ensure user enters a number
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // nextLine ensures our programme reads the input correctly :) 
                    } else {
                        System.out.println("Error ! Please enter a number");
                        scanner.nextLine(); // clear the invalid input 
                        continue; //resets loop
                    }
                    
                    switch (choice) {  // we will use a switch command. this evaluates multiple options AT ONE TIME and will execute based on various options 
                        // add expense if they typed 1, etc..
                        case 1 -> tracker.addExpense(); // we call our method from our ExpenseTracker class
                        case 2 -> tracker.viewAllExpenses();  // we can view all expenses
                        case 3 -> tracker.editExpense();
                        case 4 -> tracker.deleteExpense();
                        case 5 -> tracker.summariseByDate();
                        case 6 -> tracker.summariseByCategory();
                        case 7 -> {
                            exit = true; // exit the menu
                            System.out.println("Exiting the menu... Thank you !");
                        }
                        default -> System.out.println("Invalid choice --> please try again !"); 
                    }
                }
        
                scanner.close(); // close at end (INSIDE main, before class ends)
            } // end main
        }
    } // end class
       

// so when we run Main.java:
    // 01 - program starts in public static void main 
    // 02 - new Expense( ... ) - > calls constructor in Expensve.java making a new Expense obj w/ all associated details 
    // 03 - System.out.println(e1) -> auto calls e1.toString()
    // 04 output would print obj details 
