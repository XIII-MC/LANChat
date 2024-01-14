package com.xiii.lanchat.ui;

import com.xiii.lanchat.SuperMain;
import com.xiii.lanchat.control.FXController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FXLoader extends Application {

    public static final String softwareVersion = "b0001/D";
    private boolean firstTime;
    private TrayIcon trayIcon;

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SuperMain.class.getResource("/fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Minimize stuff
        Platform.setImplicitExit(false);
        firstTime = false;
        createTrayIcon(stage);

        stage.getIcons().add(new Image(Objects.requireNonNull(SuperMain.class.getResourceAsStream("/icons/MainIcon.png"))));
        stage.setTitle("LAN Chat");
        stage.setScene(scene);
        stage.show();
    }

    public void createTrayIcon(final Stage stage) {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            java.awt.Image image = null;
            try {
                image = ImageIO.read(Objects.requireNonNull(SuperMain.class.getResourceAsStream("/icons/MainIcon.png")));
            } catch (IOException ex) {
                System.out.println(ex);
            }


            stage.setOnCloseRequest(t -> hide(stage));
            // create a action listener to listen for default action executed on the tray icon
            final ActionListener closeListener = e -> System.exit(0);

            ActionListener showListener = e -> Platform.runLater(stage::show);
            // create a popup menu
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Montrer");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Fermer");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            /// ... add other items
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(showListener);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        }
    }

    private void hide(final Stage stage) {
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                stage.hide();
            } else {
                System.exit(0);
            }
        });
    }

    public static String getSoftwareVersion() {
        return softwareVersion;
    }
}
