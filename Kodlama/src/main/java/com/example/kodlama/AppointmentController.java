package com.example.kodlama;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

public class AppointmentController {

    @FXML
    private TextField nameField, ageField;
    @FXML
    private ChoiceBox<String> genderBox, departmentBox, doctorBox, timeBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button bookButton, cancelButton;

    @FXML
    private void initialize() {
        departmentBox.setOnAction(event -> updateDoctors());
    }

    private void updateDoctors() {
        String selectedDepartment = departmentBox.getValue();
        ObservableList<String> doctors = FXCollections.observableArrayList();

        switch (selectedDepartment) {
            case "Cardiology":
                doctors.addAll("Dr. Smith", "Dr. Adams");
                break;
            case "Dermatology":
                doctors.addAll("Dr. Brown", "Dr. Wilson");
                break;
            case "Neurology":
                doctors.addAll("Dr. Taylor", "Dr. Anderson");
                break;
            case "Orthopedics":
                doctors.addAll("Dr. Thomas", "Dr. White");
                break;
        }

        doctorBox.setItems(doctors);
    }

    @FXML
    private void handleBook() {
        String name = nameField.getText();
        String age = ageField.getText();
        String gender = genderBox.getValue();
        String department = departmentBox.getValue();
        String doctor = doctorBox.getValue();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
        String time = timeBox.getValue();

        if (name.isEmpty() || age.isEmpty() || gender == null || department == null || doctor == null || date.isEmpty() || time == null) {
            showAlert("Error", "Please fill all the fields.");
            return;
        }

        showAlert("Appointment Confirmed", "Appointment booked for " + name + " with " + doctor + " on " + date + " at " + time);
    }

    @FXML
    private void handleCancel() {
        nameField.clear();
        ageField.clear();
        genderBox.setValue(null);
        departmentBox.setValue(null);
        doctorBox.setValue(null);
        datePicker.setValue(null);
        timeBox.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
