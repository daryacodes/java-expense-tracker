import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class ExpenseTracker { //to track an ArrayList<Expense> and store multiple expenses,
    // each using an array of objects w/ details like price, date, REF#, etc.
   
    private ArrayList<Expense> expenses;  // here we can add the field (list storing all expenses)
    // this is a resisable list that will hold our Expense objects 
    // now we make the constructor to initialise the empty list 
    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }
    
    // method for adding new expenses
    public void addExpense() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        sdf.setLenient(false); // ensures strict date parsing 
        
        //  validate date
        String date = "";
        while (true) {
            System.out.print("Enter the date (DD/MM/YYYY): ");
            date = scanner.nextLine();
            try {
                sdf.parse(date); // will throw exception if invalid
                break; // valid date
            } catch (ParseException e) {
                System.out.println("Invalid date! Please use format dd/MM/yyyy.");
            }
        }
    
        // validate category choice
        String category = "";
        while (true) {
            System.out.print("Pick a category: 'Food', 'Transport', 'Entertainment': ");
            category = scanner.nextLine();
            if (category.equalsIgnoreCase("Food") ||
                category.equalsIgnoreCase("Transport") ||
                category.equalsIgnoreCase("Entertainment")) {
                break; // valid input = exit loop
            } else {
                System.out.println("Invalid category! Please choose only 'Food', 'Transport', or 'Entertainment'.");
            }
        }
    
        // validate amount
        double amount = 0.0;
        while (true) {
            System.out.print("Please enter amount: ");
            String amountStr = scanner.nextLine();
            try {
                amount = Double.parseDouble(amountStr);
                if (amount < 0) {
                    System.out.println("Amount cannot be negative!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
            }
        }
    
        // validate description
        String description = "";
        while (true) {
            System.out.print("Enter a description: ");
            description = scanner.nextLine();
            if (!description.isEmpty()) {
                break;
            } else {
                System.out.println("Description must not be empty!");
            }
        }
    
        // validate reference code
        String referenceCode = "";
        while (true) {
            System.out.print("Enter reference code: ");
            referenceCode = scanner.nextLine();
            if (!referenceCode.isEmpty()) {
                break;
            } else {
                System.out.println("Reference code must not be empty!");
            }
        }
    
        // vendor name input
        System.out.print("Enter vendor name: ");
        String vendorName = scanner.nextLine();
    
        // payment method input
        System.out.print("Enter payment method (e.g., Cash, Card): ");
        String paymentMethod = scanner.nextLine();
    
        // validate tax included (Yes/No)
        String taxIncl = "";
        while (true) {
            System.out.print("Was tax included? Yes or No: ");
            taxIncl = scanner.nextLine();
            if (taxIncl.equalsIgnoreCase("Yes") || taxIncl.equalsIgnoreCase("No")) {
                break;
            } else {
                System.out.println("Tax included can ONLY be Yes or No");
            }
        }
    
        // Create new Expense object and add to list
        Expense newExpense = new Expense(amount, category, date, description, referenceCode, 
                                         vendorName, paymentMethod, taxIncl);
        expenses.add(newExpense);
        System.out.println("Expense added successfully. Total: " + expenses.size());
    }
    
    public void deleteExpense() {
        // check if there are any expenses to delete 
        if (expenses.isEmpty()) {
            System.out.println("No expenses to delete !");
            return; // exit method
        }
    // show expense w/ numbers    by making a Scanner to read input from user 
        Scanner scanner = new Scanner(System.in);
        // print ALL expenses w/ numbers  so user can pick
        System.out.println("Expenses list: ");
        for (int i = 0; i < expenses.size(); i++) {
            // i at 0 bc ArraList indice starts at 0
            // print (i + 1) to show user-friendly numbering (starts at 1 so its not confusing indexing)
             System.out.println((i + 1) + ". " + expenses.get(i).toString());
        }
        // ask user to enter number of expense to delete 
        System.out.print("Enter the number of the expense to delete: ");
        // check if user entered an int
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input ! Please enter a number: ");
            scanner.nextLine(); // clear invalid input of user 
            return; // exit method
        }
        // get integer input from user
        int index = scanner.nextInt();
        scanner.nextLine(); // take newLine
        // validate number is within allowed range
        // user again sees number starting @ 1, but ArrayList indices start at 0 as we know :)
        if (index < 1 || index > expenses.size()) {
            System.out.println("Invalid choice. Please try again !");
            return; // exit if out of bounds
        }
        // remove expense from the list 
        expenses.remove(index - 1); // -1 to match ArrayList indexing again 
        System.out.println("Expense deleted successfully."); // let user know
    }

   
    // now we can check how many we have to get count
    public int getCount() {
        return expenses.size(); 
    }

    // add another method to edit expenses
    public void editExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to edit.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the expense number to edit:");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i).toString());
        }

        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (index < 1 || index > expenses.size()) {
            System.out.println("Invalid expense number.");
            return;
        }

        Expense selectedExpense = expenses.get(index - 1);
           // Edit amount
           System.out.print("Enter new amount (current: $" + selectedExpense.getAmount() + "): ");
           double newAmount = scanner.nextDouble();
           scanner.nextLine();
           if (newAmount >= 0) {
               selectedExpense.setAmount(newAmount);
           }
   
           // Edit description
           System.out.print("Enter new description (current: " + selectedExpense.getDescription() + "): ");
           String newDescription = scanner.nextLine();
           if (!newDescription.isEmpty()) {
               selectedExpense.setDescription(newDescription);
           }
   
           System.out.println("Expense updated successfully!");
       }

    // now we can summarise by date + category. this will use 2 methods 
    public void summariseByDate() { // by date summarisation
       // method asks user for input internally ! 
       Scanner sc = new Scanner(System.in);
       System.out.print("Please enter DATE to summarise: DD/MM/YYYY");
       String date = sc.nextLine();
       if (expenses.isEmpty()) {
        System.out.println("No expenses recorded yet !"); // error handling if empty
        return;
    }

        double total = 0;
        boolean found = false;
        for (Expense e: expenses) {
            if (e.getDate().equals(date)) {
                System.out.println(e.toString());
                total += e.getAmount();
                found = true;
            }
        } // we can make an IF ELSE conditional statement for error handling IF date is NOT found
        if (!found) { // date cannot be found
            System.out.println("Expenses NOT found for: " + date); //systme lets user know 
        } else {
            System.out.println("Total expenses for " + date + ": $" + total);
        }
        }

    public void summariseByCategory() {
        // user inputs directly what category they wanna summarise by 
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the CATEGORY to summarise: ");
        String category = sc.nextLine();

        double total = 0; // running total for expenses 
        boolean found = false; // flag to track if expenses are found
// loop thru all expenses in list 
        for (Expense e: expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                // print matching expense 
                System.out.println(e.toString());
                total += e.getAmount();
                found = true;
            }
        }

        // after loop decide which message disaplys
        if (!found) {
            System.out.println("Expenses NOT found for: " + category);
        } else {
            System.out.println("Total expenses for " + category + ": $" + total);
        }
    }
    
        // method to SAVE expenses. this method will write expenses --> a file so data persists after closing the programme
    public void saveToFile(String filename) { // this req file handling in java to read from + write to files
        // we use a try-catch block to handle potential errors when working with files
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(filename))) { 
            for (Expense e : expenses) { // iterates over expenses, writes all 8 variables separated by commas
                writer.println(e.getDate() + "," //save date 
                             + e.getCategory() + ","  //save cateogry 
                             + e.getDescription() + "," // save desc
                             + e.getAmount() + "," // save amount 
                             + e.getreferenceCode() + "," // save ref Code 
                             + e.getvendorName() + "," // save vendor name 
                             + e.getpaymentMethod() + "," // save payment method (cash, card)
                             + e.gettaxIncl()); // save if tax was included (y/n)

            }
            System.out.println("Expenses successfully saved to file: " + filename);
        } catch (java.io.IOException e) {
            System.out.println("Error saving expenses to file: " + e.getMessage());
        }
    }  
    // now we can make a method to LOAD expenses
    public void loadExpensesFromFile(String filename) {
        expenses.clear(); // start fresh; ensure we don't duplicate entries if/when reloading
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(filename))) {
            while (scanner.hasNextLine()) { 
                String line = scanner.nextLine();
                String[] parts = line.split(","); // split by our comma character we used when saving
                if (parts.length == 8) { // checks there are 8 variables ! 
                    String date = parts[0]; 
                    String category = parts[1];
                    String description = parts[2];
                    double amount = Double.parseDouble(parts[3]); // parses double
                    String referenceCode = parts[4];
                    String vendorName = parts[5];
                    String paymentMethod = parts[6];
                    String taxIncl = parts[7];
                        // now we reconstruct the actual expense object !
                    Expense expense = new Expense(amount, category, date, description, referenceCode, vendorName, paymentMethod, taxIncl);
                    expenses.add(expense); // and add it 
                }
            } // now wrap in the try-catch for java file handling 
            System.out.println("Expenses loaded from file: " + filename);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error loading expenses from file: " + e.getMessage());
        } 
    }
    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses are recorded yet !");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // since we already use sdf for validation we can reuse it to sort 
        sdf.setLenient(false); // will ensure strict date parsing

        // to sort by date
        expenses.sort((e1, e2) -> { // we can use a comparator to compare 2 expenses by date
            try {
                Date date1 = sdf.parse(e1.getDate()); // convert expense 1 date from str --> date 
                Date date2 = sdf.parse(e2.getDate()); //convert expense 2 date from str --> date 
                return date1.compareTo(date2); // earlier dates come first 
            } catch (java.text.ParseException e) { // if date is invalid
                // we can throw an error
                // or we can just ignore it and treat it as an error
                // we can also just print the error message
                System.out.println("Error parsing date: " + e.getMessage());
                throw new IllegalArgumentException("Invalid date format: " + e1.getDate());
            }
        });

        double total = 0;
        for (Expense e: expenses) {
            System.out.println(e.toString());
            total += e.getAmount();
        }
        System.out.println("Total expenses: $" + total);
    }

}
