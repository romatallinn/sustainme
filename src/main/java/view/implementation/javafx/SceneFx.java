package view.implementation.javafx;

/**
 * Helper class that helps to initialize the data of all scenes in the application for easier use.
 * @param <T> one of the view classes (packages: view -> implementation -> javafx)
 */
public class SceneFx<T> {

    private String fxmlPath;
    private String cssPath;

    private T view;


    /**
     * Constructor for the scene
     * @param fxmlPath - path to the file that stores the view markup.
     * @param cssPath - css file that will be connected to the view.
     */
    public SceneFx(String fxmlPath, String cssPath) {
        this.fxmlPath = fxmlPath;
        this.cssPath = cssPath;
    }

    /**
     * Initializes the view (fxml's controller) file for the scene; all GUI events will be forwarded
     * there.
     * @param view one instance of the view classes (packages: view -> implementation -> javafx)
     */
    public void setView(T view) {
        this.view = view;
    }

    /**
     * @return path to the fxml file of the view.
     */
    public String getFxmlPath() {
        return this.fxmlPath;
    }

    /**
     * @return path to the css file of the view.
     */
    public String getCssPath() {
        return this.cssPath;
    }

    /**
     * @return view (fxml's controller) that will handle all GUI events.
     */
    public T getView() {
        return this.view;
    }


}
