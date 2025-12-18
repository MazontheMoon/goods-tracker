import java.util.Scanner;

public class Main {

    //Declare variables
    static boolean isActive = true;
    static int userChoice;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Display greeting
        displayGreeting();

        while(isActive){
            //Display menu
            displayMenu();

            //Get user selection
            System.out.print("Enter your menu choice: ");
            userChoice = scanner.nextInt();

            switch(userChoice){
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
        System.out.println("=======================================\n");
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

    public static void displayComingSoonMessage(String name) {
        System.out.println("-----------------------------------------------");
        System.out.println(name + " feature is coming soon.");
        System.out.println("-----------------------------------------------");
    }

    public static void displayExitMessage() {
        System.out.println("===============================================");
        System.out.println("Thank you for using the Stock Management System");
        System.out.println("===============================================");
    }

    public static void handleGoodsIn(){
        System.out.println("goods in");
    }

}