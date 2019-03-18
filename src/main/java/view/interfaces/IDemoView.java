package view.interfaces;

import controller.OldDemoController;

/**
 * Interface of the demo view.
 * It is used in order to make a more abstracted connection,
 * between the controller and possible views (mobile view, javafx or terminal).
 */
public interface IDemoView {

    void initView(OldDemoController controller);


    /**
     * Display the status line.
     * @param status - the body of the status message to be displayed.
     */
    void displayStatus(String status);

    /**
     * Display the data (json object).
     * @param data - the body of the data message to be displayed.
     */
    void displayData(String data);


}
