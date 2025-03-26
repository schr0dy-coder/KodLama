package com.example.kodlama;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;
import java.io.IOException;

public class MainInterfaceController {
    @FXML
    private Button menuButton, checkUpButton, notificationButton, settingsButton, medicationScheduleButton, aboutButton, EXIT;

    @FXML
    private VBox sidebar;

    private boolean isSidebarVisible = true;

    @FXML
    public void initialize() {
        setupMenuButtonEffects();
        setupSidebarAnimation();
    }


    private void setupMenuButtonEffects() {
        ScaleTransition hoverScale = new ScaleTransition(Duration.millis(200), menuButton);
        hoverScale.setToX(1.1);
        hoverScale.setToY(1.1);

        ScaleTransition resetScale = new ScaleTransition(Duration.millis(200), menuButton);
        resetScale.setToX(1.0);
        resetScale.setToY(1.0);

        menuButton.setOnMouseEntered(event -> hoverScale.playFromStart());
        menuButton.setOnMouseExited(event -> resetScale.playFromStart());

        menuButton.setOnAction(event -> toggleSidebar());
    }


    private void setupSidebarAnimation() {
        sidebar.setTranslateX(0);
    }

    private void toggleSidebar() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebar);
        double targetX = isSidebarVisible ? -sidebar.getWidth() : 0;
        transition.setToX(targetX);
        transition.setOnFinished(event -> isSidebarVisible = !isSidebarVisible);
        transition.play();
    }


    private void openInterface(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCheckUpInterface() {
        openInterface("check_up_interface.fxml", "Check-Up");
    }

    @FXML
    private void openNotificationsInterface() {
        openInterface("notifications_interface.fxml", "Notifications");
    }

    @FXML
    private void openSettingsInterface() {
        openInterface("settings_interface.fxml", "Settings");
    }

    @FXML
    private void openAppointmentInterface() {
        openInterface("Appointment.fxml", "Appointment");
    }

    @FXML
    private void openMedicationScheduleInterface() {
        openInterface("medication_schedule_interface.fxml", "Medication Schedule");
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
