package com.example.kodlama;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationsController {
    @FXML
    private ListView<String> notificationsList;

    @FXML
    private void initialize() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm a");

        notificationsList.getItems().addAll(
                "Next Appointment: Dr. Smith - " + now.plusDays(7).format(formatter),
                "Medication Reminder: Take Aspirin at " + now.plusHours(2).format(formatter),
                "Prescription Refill: Amoxicillin due in 5 days",
                "Annual Check-up: Schedule upcoming blood tests",
                "Vaccination Reminder: Flu shot recommended",
                "Health Tip: Stay hydrated and get regular exercise"
        );
    }

    // Optional method to add new notifications dynamically
    public void addNotification(String notification) {
        notificationsList.getItems().add(notification);
    }

    // Optional method to clear all notifications
    public void clearNotifications() {
        notificationsList.getItems().clear();
    }
}