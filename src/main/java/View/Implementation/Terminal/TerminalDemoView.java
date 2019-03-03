package View.Implementation.Terminal;

import Controller.DemoController;
import View.Interfaces.IDemoView;

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
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";


    /**
     * Constructor for the view class.
     * @param controller class which acts upon the actions taken in the view.
     */
//    public TerminalDemoView(DemoController controller)
//    {
//        this.controller = controller;
//    }

    public void initView(DemoController controller) {
        this.controller = controller;
    }

    /**
     * Displays the terminal menu for user. Then requests the input of the option and sends it to handling controller.
     */
    public void Show()
    {
        String content =    "Application Menu: \n" +

                            "      1 - Sign Up\n" +
                            "      2 - Sign In\n" +

                            "      3 - Retrieve Data from DB\n" +
                            "      4 - Add New Entry to DB\n" +

                            "      5 - Shutdown\n";


        while (true) {

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

            case 1:
                SignUpViewHandler();
                break;

            case 2:
                SignInViewHandler();
                break;

            case 3:
                RetrieveDataViewHandler();
                break;

            case 4:
                PutDataViewHandler();
                break;

            case 5:
                controller.ApplicationShutdown();
                break;

            default: System.out.println("Unknown option. Please try again!");

        }
    }

    /**
     * Initiate the load of the data from the DB from an inputted by user path.
     */
    public void RetrieveDataViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Path (leave empty to retrieve the whole DB): ");
        String path = in.nextLine();

        controller.RetrieveDataCallback(path);

    }

    /**
     * Obtain data from the user and then pass it further for uploading into the database.
     */
    public void PutDataViewHandler()
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

    public void ApplicationShutdownHandler() {
        controller.ApplicationShutdown();
    }

    private void SignUpViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Email: ");
        String email = in.nextLine();

        System.out.print("Password: ");
        String pass = in.nextLine();


        controller.SignUpCallback(email, pass);

    }

    private void SignInViewHandler() {

        Scanner in = new Scanner(System.in);

        System.out.print("\n");

        System.out.print("Email: ");
        String email = in.nextLine();

        System.out.print("Password: ");
        String pass = in.nextLine();


        controller.SignInCallback(email, pass);

    }

    /**
     * Displays the error message to the console with the red color format.
     * @param status - the body of the error message.
     */
    public void DisplayStatus(String status)
    {
        System.out.println("\n\n" + ANSI_RED + status + ANSI_RESET + "\n\n");
    }


    /**
     * Displays the arbitrary data that is passed from the controller formatted with the green color.
     * @param data
     */
    public void DisplayData(String data) {
        System.out.println(ANSI_GREEN + data + ANSI_RESET);
    }

}
