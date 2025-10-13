// so lets build an expense tracker, where user can use a console to input details via text-based prompts
// to track their daily personal expenses. input = their values, output =  categorised + summarised records 

// first we  establish the Expense class; this is a 'cookie cutter' for objects
// inside {..} we add Expense attributes and any methods (things it can do)
public class Expense { // what an expense is 
    private double amount; // the 'amount' variable belongs to 'Expense' class
    // so this line ==> every Expense (class) has an amount of money (obj) hidden from outside world
    private  String category; // food, transport, entertainment 
    private  String date; // dd/mm/yyyy
    private  String description; //describe expense 
    private  String referenceCode; //expense ref code 
    private  String vendorName; //name of vendor 
    private  String paymentMethod; // cash, card ... 
    private  String taxIncl; // yes/no
// all of the attributes are PRIVATE following OOP encapsulation principles
//making these private variables allows ONLY code inside Expense class to access directly
    // now we make the CONSTURCTOR --> this is a method w/ same class name, which
    // allows us to make a New Expense obj
    public Expense(double amount, String category, String date, String description,
     String referenceCode, String vendorName, String paymentMethod, String taxIncl) {
        this.amount = amount; // 'this' keyword refers to CURRENT obj instance. it can access current instance variables and reduce naming conflicts !
        this.category = category;
        this.date = date;
        this.description = description;
        this.referenceCode = referenceCode;
        this.vendorName = vendorName;   
        this.paymentMethod = paymentMethod;
        this.taxIncl = taxIncl;
     }

     // now we add a toString method to print the expense details ! 
     // if we didn't't have this, it would print the memory address of our object (not the details we are interested in !) 
     @Override //this line is req bc Java auto inherits from Object, so our print won't print the memory adress! thus it shows we 
     // are intentionally replacing the inherited method 
     public String toString() {
        return "Expense: " + "$" + amount + " - " + category + " - " + date 
        + " - " + description + " - " + "REF:" + referenceCode + " - " + "Vendor name: " + vendorName + " - " + "Method of Pay: " + paymentMethod + " - " + "Tax Included? " + taxIncl;
     }

    // now as our fields are private, outside code like main.java can't access them direclty
    // so we can use getter methods to read private data
    // we can also use setter methods to write/change private data

    // so we add getter for amount
    public double getAmount() { //anyone can call this bc it's public
      return amount; // returns value of amount (private field !)
    } //this lets code OUTSIDE the class (Expense) read the value safely !
    // now the setter for amount 
    public void setAmount(double amount) { // this takes a NEW value from caller
      // now we want to validate a positive # and ensure it is numeric
      if (amount < 0) {
        System.out.println("Please enter a non-negative number: ");
      } else {
        this.amount = amount;
      }
    }
       //the this. method makes it CLEAR we are talking abt the field of the obj, not just the local parameter 
    // we repeat this for all of the fields ! 

    public String getDate() { //get
        return date;
    }
    public void setDate (String date) { //set
        this.date = date;
    }
    public String getCategory () { //get
        return category;
    } 
    public void setCategory (String category) { //set
    this.category = category;
    }
    public String getDescription() { // get 
        return description;
    }
    public void setDescription(String description) { //set
        this.description = description;
    }
    public String getreferenceCode() { //get
        return referenceCode;
    }
    public void setreferenceCode (String referenceCode) { //set 
        this.referenceCode = referenceCode;
    }
    public String getvendorName() { // get
        return vendorName;
    }
    public void setvendorName(String vendorName) { //set
        this.vendorName = vendorName;
    }
    public String getpaymentMethod () { //get 
        return paymentMethod;
    }
    public void setpaymentMethod (String paymentMethod) { //set
        this.paymentMethod = paymentMethod;
    }
    public String gettaxIncl() { //get
        return taxIncl; 
    }
    public void settaxIncl(String taxIncl) { //set
        this.taxIncl = taxIncl;
    }
    // we can make subclasses for FoodExpense for kinds of meal 
    public static class FoodExpense extends Expense {
        private String mealKind;
        public FoodExpense (double amount, String category, String date, String description, String referenceCode, String vendorName, String paymentMethod, String taxIncl, String mealKind) {
            super(amount, category, date, description, referenceCode, vendorName, paymentMethod, taxIncl); // inherits from super class
            this.mealKind = mealKind;
        }
        public String getMealKind() {
            return mealKind;
        }
        @Override
        public String toString() {
            return super.toString() + " ~ " + "Meal kind: " + mealKind;
        }
    }
}