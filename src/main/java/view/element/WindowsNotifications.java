package view.element;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;

public class WindowsNotifications {

    /**
     * Checks if the display is supported.
     * @param image                     - image for the notification
     * @param message                   - message for the notification
     * @throws AWTException             - exception
     * @throws MalformedURLException    - exception
     */
    public void notification(String image, String message)
        throws AWTException, MalformedURLException {
        try {
            if (SystemTray.isSupported()) {
                WindowsNotifications td = new WindowsNotifications();
                td.displayTray(image, message);
            } else {
                System.err.println("System tray not supported!");
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes the notification.
     * @param image                     - image for the notification
     * @param message                   - message for the notification
     * @throws AWTException             - exception
     * @throws MalformedURLException    - exception
     */
    public void displayTray(String image, String message)
        throws AWTException, MalformedURLException {

        try {

            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();
            
            Image img = new ImageIcon(this.getClass().getResource(image)).getImage();

            TrayIcon trayIcon = new TrayIcon(img);
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);
            trayIcon.displayMessage("SustainMe", message, TrayIcon.MessageType.NONE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}


