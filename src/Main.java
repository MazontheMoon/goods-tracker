import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    //Declare variables
    static boolean isActive = true;
    static int userChoice;
    static String productCode;
    static String productName;
    static int productQuantity;
    static double productPrice;
    static double deliveryCost;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Display greeting
        displayMessage("Welcome to the Stock Management System");

        while (isActive) {

            //Display menu
            displayMenu();

            //Get user selection
            System.out.print("Enter your menu choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    displayMessage("Payroll option not yet available.");
                    break;
                case 2:
                    handleGoodsIn();
                    break;
                case 3:
                    displayMessage("Goods Out option not yet available.");
                    break;
                case 4:
                    displayMessage("Stock Control option not yet available.");
                    break;
                case 5:
                    displayMessage("Reports option not yet available.");
                    break;
                case 6:
                    isActive = false;
                    break;
                default:
                    displayMessage("INVALID CHOICE. Enter a number between 1 and 6");
            }
        }

        //Exit program
        scanner.close();
        displayMessage("Thank you for using the Stock Management System");
    }

    /*=============
    DISPLAY MESSAGE
    ===============*/
    public static void displayMessage(String message) {
        System.out.println("=======================================");
        System.out.println(message);
        System.out.println("=======================================");
    }

    /*============
    DISPLAY MENU
    ==============*/
    public static void displayMenu() {
        System.out.println("------------");
        System.out.println("Menu Options");
        System.out.println("------------");
        System.out.println("1. Payroll");
        System.out.println("2. Goods In");
        System.out.println("3. Goods Out");
        System.out.println("4. Stock Control");
        System.out.println("5. Reports");
        System.out.println("6. Exit");
        System.out.println("------------");
    }

    /*===========================
    HANDLE GOODS IN FUNCTIONALITY
    =============================*/
    public static void handleGoodsIn() {

        //Show greeting
        displayMessage("Goods In Management System");

        //clear scanner cache
        scanner.nextLine();

        //Get product input
        productCode = getInput("string", "Product Code");
        productName = getInput("string", "Product Name");
        productQuantity = Integer.parseInt(getInput("int", "Product Quantity"));
        productPrice = Double.parseDouble(getInput("double", "Product Price"));

        //Calculations
        calculateDeliveryCost();

        //Display results on screen
        displayMessage("Delivery Cost for " + productQuantity + " " + productName + " is: $" + deliveryCost);

        //Add product to text file
        writeToFile();

    }

    /*========
    GET INPUT
    =========*/

    public static String getInput(String inputType, String itemTitle) {
        while (true) {
            try {
                System.out.print("Enter " + itemTitle + " :");
                return validateInput(inputType, itemTitle, scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*============
    VALIDATE INPUT
    ==============*/
    public static String validateInput(String inputType, String item, String input) {

        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException(item + " cannot be blank.");
        }

        //Check if 0 entered
        if (inputType.equals("int")) {
            int numberInt = Integer.parseInt(input);
            if (numberInt <= 0) {
                throw new IllegalArgumentException(item + " must be greater than 0.");
            }
        }

        //Check if 0.0 entered
        if (inputType.equals("double")) {
            double numberDouble = Double.parseDouble(input);
            if (numberDouble <= 0) {
                throw new IllegalArgumentException(item + " must be greater than 0.0");
            }
        }
        return input;
    }

    /*====================
    GOODS IN CALCULATIONS
    ====================*/
    //PRODUCT INPUT CALCULATIONS
    public static void calculateDeliveryCost() {
        deliveryCost = productPrice * productQuantity;
    }

    /*===================================
    PREPARE TEXT CONTENT TO WRITE TO FILE
    ====================================*/
    public static String prepareContentToWrite(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return String.format("""
                        ========================
                        Goods Inward %s
                        ========================
                        Product Code: %s
                        Product Name: %s
                        Product Quantity: %d
                        Product Price: %s
                        Delivery Cost: %s
                        
                        """,
                date.format(dateFormatter),
                productCode,
                productName,
                productQuantity,
                currency.format(productPrice),
                currency.format(deliveryCost)
        );
    }

    /*=================
    WRITE TO TEXT FILE
    ==================*/
    public static void writeToFile() {
        String filePath = "C:\\temp\\goodsIn.txt";

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(prepareContentToWrite());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file " + filePath);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}



