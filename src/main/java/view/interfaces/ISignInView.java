package view.interfaces;

import controller.SignInController;

import java.io.IOException;

/**
 * Interface of the sign in view.
 * It is used in order to make a more abstracted connection,
 * between the controller and possible views (mobile view, javafx or terminal).
 */
public interface ISignInView {

    void initView(SignInController controller);

    /**
     * Display the status line.
     * @param status - the body of the status message to be displayed.
     */
    void displayStatus(String status);

    void goToHome() throws IOException;

}