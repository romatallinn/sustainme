package view.interfaces;

import controller.SignUpController;

/**
 * Interface of the sign up view.
 * It is used in order to make a more abstracted connection,
 * between the controller and possible views (mobile view, javafx or terminal).
 */
public interface ISignUpView {

    void initView(SignUpController controller);

    /**
     * Display the status line.
     * @param status - the body of the status message to be displayed.
     */
    void displayStatus(String status);


    void clearSignUpFields();
}
