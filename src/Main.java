import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
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
        displayGreeting();

        while (isActive) {
            //Display menu
            displayMenu();

            //Get user selection
            System.out.print("Enter your menu choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    displayComingSoonMessage("Payroll");
                    break;
                case 2:
                    handleGoodsIn();
                    break;
                case 3:
                    displayComingSoonMessage("Goods Out");
                    break;
                case 4:
                    displayComingSoonMessage("Stock Control");
                    break;
                case 5:
                    displayComingSoonMessage("Reports");
                    break;
                case 6:
                    isActive = false;
                    break;
                default:
                    System.out.println("INVALID CHOICE. Enter a number between 1 and 6");

            }
        }

        scanner.close();

        displayExitMessage();

    }

    //Display greeting
    public static void displayGreeting() {
        System.out.println("=======================================");
        System.out.println("Welcome to the Stock Management System");
        System.out.println("=======================================");
    }

    //Display menu
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

    //Display placeholder message for unavailable features
    public static void displayComingSoonMessage(String name) {
        System.out.println("-----------------------------------------------");
        System.out.println(name + " option not available.");
        System.out.println("-----------------------------------------------");
    }

    //Display exit message
    public static void displayExitMessage() {
        System.out.println("===============================================");
        System.out.println("Thank you for using the Stock Management System");
        System.out.println("===============================================");
    }

    /*========================
    HANDLE GOODS FUNCTIONALITY
    =========================*/

    public static void handleGoodsIn() {
        System.out.println("--------------------------");
        System.out.println("Goods In Management System");
        System.out.println("--------------------------");

        //clear scanner cache
        scanner.nextLine();

        getProductCode();
        getProductName();
        getProductQuantity();
        getProductPrice();
        displayDeliveryCost();
        writeToFile();

    }

    //GET PRODUCT INPUT
    public static void getProductCode() {
        while (true) {
            try {
                System.out.print("Enter Product Code: ");
                productCode = validateProductCode(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getProductName() {
        while (true) {
            try {
                System.out.print("Enter Product Name: ");
                productName = validateProductName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getProductQuantity() {
        while (true) {
            try {
                System.out.print("Enter Quantity of Product: ");
                productQuantity = validateProductQuantity(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getProductPrice() {
        while (true) {
            try {
                System.out.print("Enter Price of Product: ");
                productPrice = validateProductPrice(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //VALIDATE PRODUCT INPUT
    public static String validateProductCode(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Product Code cannot be blank.");
        }
        return input;

    }

    public static String validateProductName(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Product Name cannot be blank.");
        }
        return input;

    }

    public static int validateProductQuantity(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Quantity must be filled in.");
        }

        //Check quantity not 0
        int quantity = Integer.parseInt(input);
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be 0.");
        }
        return quantity;
    }

    public static double validateProductPrice(String input) {
        //Check for blank input
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Price must be filled in.");
        }

        //Check price not 0
        double price = Double.parseDouble(input);
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be 0.0");
        }
        return price;

    }

    //PRODUCT INPUT CALCULATIONS
    public static void displayDeliveryCost() {

        deliveryCost = productPrice * productQuantity;
        System.out.println("Delivery Cost for " + productQuantity + " " + productName + " is: $" + deliveryCost);
    }

    /*============
    Write To File
    =============*/

    public static void writeToFile(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String filePath = "C:\\temp\\goodsIn.txt";
        String goodsInLog = String.format("""
                        ==============
                        Goods Inward
                        ===============
                        Product Code: %s
                        Product Name: %s
                        Product Quantity: %d
                        Product Price: %s
                        Delivery Cost: %s
                        =========================
                        """,
                productCode,
                productName,
                productQuantity,
                currency.format(productPrice),
                currency.format(deliveryCost)
        );

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(goodsInLog);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot locate file " + filePath);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}



