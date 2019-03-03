package view.implementation.terminal;

import controller.DemoController;
import view.interfaces.IDemoView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The view class for the terminal demonstration purposes.
 * Displays the ascii menu and asks to input the option, then handles it.
 */
public class TerminalDemoView implements IDemoView {

    private DemoController controller;

    // Console Color Formatting Tags
    private final String ansiReset = "\u001B[0m";
    private final String ansiRed = "\u001B[31m";
    private final String ansiGreen = "\u001B[32m";


    /**
     * Constructor for the view class.
     * @param controller class which acts upon the actions taken in the view.
     */
    public TerminalDemoView(DemoController controller) {
        this.controller = controller;
    }

    /**
     * Displays the terminal menu for user. Then requests the input of the option
     * and sends it to handling controller.
     */
    public void show() {

        String content =    "Application Menu: \n"
                            + "      1 - Sign Up\n"
                            + "      2 - Sign In\n"

                            + "      3 - Retrieve Data from DB\n"
                            + "      4 - Add New Entry to DB\n"

                            + "      5 - Shutdown\n";


        while (true) {

            System.out.println(content);

            Scanner in = new Scanner(System.in);
            System.out.print("Input an option: ");
            int option = in.nextInt();

            handleMenuOption(option);

        }

    }

    /**
     * Callbacks the controller to act according to a chosen option.
     * @param option chosen from terminal menu
     */
    private void handleMenuOption(int option) {
        switch (option) {

            case 1:
                signUpViewHandler();
                break;

            case 2:
                signInViewHandler();
                break;

            case 3:
                retrieveDataViewHandler();
                break;

            case 4:
                putDataViewHandler();
                break;

            case 5:
                controller.applicationShutdown();
                break;

            default: System.out.println("Unknown option. Please try again!");

        }
    }

    /**
     * Initiate the load of the data from the DB from an inputted by user path.
     */
    private void retrieveDataViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Path (leave empty to retrieve the whole DB): ");
        final String path = in.nextLine();

        controller.retrieveDataCallback(path);

    }

    /**
     * Obtain data from the user and then pass it further for uploading into the database.
     */
    private void putDataViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Path: ");
        final String path = in.nextLine();

        System.out.print("Key: ");
        final String key = in.nextLine();

        System.out.print("Value: ");
        final String value = in.nextLine();

        Map<String, Object> dataMap = new LinkedHashMap();
        dataMap.put(key, value);

        controller.putDataActionCallback(path, dataMap);
    }

    private void signUpViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Email: ");
        final String email = in.nextLine();

        System.out.print("Password: ");
        final String pass = in.nextLine();


        controller.signUpCallback(email, pass);

    }

    private void signInViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Email: ");
        final String email = in.nextLine();

        System.out.print("Password: ");
        final String pass = in.nextLine();


        controller.signInCallback(email, pass);

    }

    /**
     * Displays the error message to the console with the red color format.
     * @param status - the body of the error message.
     */
    public void displayStatus(String status) {
        System.out.println("\n\n" + ansiRed + status + ansiReset + "\n\n");
    }


    /**
     * Displays the arbitrary data that is passed from the controller
     * formatted with the green color.
     * @param data - the body of the message.
     */
    public void displayData(String data) {
        System.out.println(ansiGreen + data + ansiReset);
    }

}
