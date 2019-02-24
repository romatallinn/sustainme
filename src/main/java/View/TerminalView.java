package View;

import Controller.DemoTerminalController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The view class for the terminal demonstration purposes.
 * Displays the ascii menu and asks to input the option, then handles it.
 */
public class TerminalView {

    private DemoTerminalController controller;

    // Console Color Formatting Tags
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    /**
     * Constructor for the view class.
     * @param controller class which acts upon the actions taken in the view.
     */
    public TerminalView(DemoTerminalController controller)
    {
        this.controller = controller;
    }

    /**
     * Displays the terminal menu for user. Then requests the input of the option and sends it to handling controller.
     */
    public void InitializeMenuInteraction()
    {
        String content =    "Application Menu: \n" +
                            "      1 - Retrieve Data from DB\n" +
                            "      2 - Add New Entry to DB\n" +
                            "      3 - Shutdown\n";


        while(true) {

            System.out.println(content);

            Scanner in = new Scanner(System.in);
            System.out.print("Input an option: ");
            int option = in.nextInt();

            HandleMenuOption(option);

        }

    }

    /**
     * Callbacks the controller to act according to a chosen option.
     * @param option chosen from terminal menu
     */
    private void HandleMenuOption(int option)
    {
        switch (option)
        {
//            case 0:
//                DisplayErrorMessage("Test Error Message");

            case 1:
                RetrieveDataViewHandler();

                break;

            case 2:
                PutDataViewHandler();
                break;

            case 3:
                controller.ApplicationShutdown();
                break;

            default: System.out.println("Unknown option. Please try again!");
        }
    }

    /**
     * Initiate the load of the data from the DB from an inputted by user path.
     */
    private void RetrieveDataViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Path (leave empty to retrieve the whole DB): ");
        String path = in.nextLine();

        controller.RetrieveDataCallback(path);

    }

    /**
     * Obtain data from the user and then pass it further for uploading into the database.
     */
    private void PutDataViewHandler()
    {

        Map<String, Object> dataMap = new LinkedHashMap();

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Path: ");
        String path = in.nextLine();

        System.out.print("Key: ");
        String key = in.nextLine();

        System.out.print("Value: ");
        String value = in.nextLine();

        dataMap.put(key, value);

        controller.PutDataActionCallback(path, dataMap);
    }

    /**
     * Displays the error message to the console with the red color format.
     * @param err - the body of the error message.
     */
    public void DisplayErrorMessage(String err)
    {
        System.out.println(ANSI_RED + err + ANSI_RESET);
    }


    /**
     * Displays the arbitrary data that is passed from the controller formatted with the green color.
     * @param data
     */
    public void DisplayData(String data) {
        System.out.println(ANSI_GREEN + data + ANSI_RESET);
    }

}
