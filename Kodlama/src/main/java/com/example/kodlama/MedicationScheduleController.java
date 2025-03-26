package com.example.kodlama;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class MedicationScheduleController {

    @FXML
    private TableView<Medication> medicationTable;

    @FXML
    private TableColumn<Medication, String> medicationNameColumn;

    @FXML
    private TableColumn<Medication, String> timeColumn;

    @FXML
    private TableColumn<Medication, String> statusColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button closeButton;


    private ObservableList<Medication> medicationList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        medicationNameColumn.setCellValueFactory(cellData -> cellData.getValue().medicationNameProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());


        medicationList.add(new Medication("Aspirin", "08:00 AM", "Pending"));
        medicationList.add(new Medication("Ibuprofen", "02:00 PM", "Taken"));
        medicationTable.setItems(medicationList);


        editButton.disableProperty().bind(medicationTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void addMedication(ActionEvent event) {
        Dialog<Medication> dialog = new Dialog<>();
        dialog.setTitle("Add Medication");
        dialog.setHeaderText("Enter new medication details");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Medication Name");
        TextField timeField = new TextField();
        timeField.setPromptText("Time (e.g., 08:00 AM)");
        TextField statusField = new TextField();
        statusField.setPromptText("Status (e.g., Pending)");

        // Add fields to dialog pane
        dialog.getDialogPane().setContent(new javafx.scene.layout.VBox(10,
                new Label("Name:"), nameField,
                new Label("Time:"), timeField,
                new Label("Status:"), statusField));

        // Convert result to Medication object
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Medication(
                        nameField.getText(),
                        timeField.getText(),
                        statusField.getText()
                );
            }
            return null;
        });

        // Show dialog and add result to list
        dialog.showAndWait().ifPresent(medication -> {
            if (!medication.getMedicationName().isEmpty()) {
                medicationList.add(medication);
            } else {
                showAlert("Error", "Medication name cannot be empty.");
            }
        });
    }

    @FXML
    private void editMedication(ActionEvent event) {
        // Get the selected medication
        Medication selectedMedication = medicationTable.getSelectionModel().getSelectedItem();
        if (selectedMedication == null) return;

        // Create a dialog to edit the selected medication
        Dialog<Medication> dialog = new Dialog<>();
        dialog.setTitle("Edit Medication");
        dialog.setHeaderText("Edit medication details");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create input fields pre-filled with current values
        TextField nameField = new TextField(selectedMedication.getMedicationName());
        TextField timeField = new TextField(selectedMedication.getTime());
        TextField statusField = new TextField(selectedMedication.getStatus());

        // Add fields to dialog pane
        dialog.getDialogPane().setContent(new javafx.scene.layout.VBox(10,
                new Label("Name:"), nameField,
                new Label("Time:"), timeField,
                new Label("Status:"), statusField));

        // Convert result to Medication object
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Medication(
                        nameField.getText(),
                        timeField.getText(),
                        statusField.getText()
                );
            }
            return null;
        });

        // Show dialog and update the selected medication
        dialog.showAndWait().ifPresent(newMedication -> {
            if (!newMedication.getMedicationName().isEmpty()) {
                int index = medicationTable.getSelectionModel().getSelectedIndex();
                medicationList.set(index, newMedication);
            } else {
                showAlert("Error", "Medication name cannot be empty.");
            }
        });
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        // Close the window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // Helper method to show alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Inner class to represent a Medication entry
    public static class Medication {
        private final StringProperty medicationName;
        private final StringProperty time;
        private final StringProperty status;

        public Medication(String medicationName, String time, String status) {
            this.medicationName = new SimpleStringProperty(medicationName);
            this.time = new SimpleStringProperty(time);
            this.status = new SimpleStringProperty(status);
        }

        public String getMedicationName() {
            return medicationName.get();
        }

        public StringProperty medicationNameProperty() {
            return medicationName;
        }

        public String getTime() {
            return time.get();
        }

        public StringProperty timeProperty() {
            return time;
        }

        public String getStatus() {
            return status.get();
        }

        public StringProperty statusProperty() {
            return status;
        }
    }
}