package View.Interfaces;

import Controller.DemoController;

/**
 * Interface of the demo view.
 * It is used in order to make a more abstracted connection
 * between the controller and possible views (mobile view, JavaFX or Terminal)
 */
public interface IDemoView {

    void initView(DemoController controller);

    /**
     * Display the view
     */
    void Show();

    /**
     * Display the status line
     * @param status
     */
    void DisplayStatus(String status);

    /**
     * Display the data (json object)
     * @param data
     */
    void DisplayData(String data);


    void RetrieveDataViewHandler();
    void PutDataViewHandler();
    void ApplicationShutdownHandler();

}
