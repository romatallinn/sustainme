package view.element;

import java.awt.*;
import java.net.MalformedURLException;

public class WindowsNotifications {

        public void notification(String image, String message) throws AWTException, MalformedURLException {
            try {
                if (SystemTray.isSupported()) {
                    WindowsNotifications td = new WindowsNotifications();
                    td.displayTray(image, message);
                } else {
                    System.err.println("System tray not supported!");
                }
            }catch (AWTException e){
                e.printStackTrace();
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        public void displayTray(String image, String message) throws AWTException, MalformedURLException {

            try{

            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();

            //If the icon is a file
            Image picture = Toolkit.getDefaultToolkit().createImage(image);

            TrayIcon trayIcon = new TrayIcon(picture, "Tray Demo");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);

            trayIcon.displayMessage("SustainMe", message, TrayIcon.MessageType.INFO);
        }catch (AWTException e){
            e.printStackTrace();
            }
        }
}


