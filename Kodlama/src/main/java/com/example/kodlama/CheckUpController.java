package com.example.kodlama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CheckUpController {
    @FXML
    private TextField symptomInput;

    @FXML
    private Button checkButton;

    @FXML
    private ListView<String> diagnosisList;

    @FXML
    private TextArea adviceArea;

    @FXML
    private Button callDoctorButton;

    @FXML
    private void initialize() {

        symptomInput.setEditable(true);
        diagnosisList.getItems().clear();
        adviceArea.clear();
    }

    @FXML
    private void analyzeSymptoms() {

        diagnosisList.getItems().clear();
        adviceArea.clear();


        String symptoms = symptomInput.getText().trim().toLowerCase();

        if (symptoms.isEmpty()) {
            diagnosisList.getItems().add("No symptoms entered");
            adviceArea.setText("Please enter your symptoms to get a diagnosis.");
        } else if (symptoms.contains("headache")) {
            diagnosisList.getItems().addAll(
                    "Tension Headache",
                    "Migraine",
                    "Cluster Headache"
            );
            adviceArea.setText("Possible Help:\n" +
                    "- Rest in a quiet, dark room\n" +
                    "- Stay hydrated\n" +
                    "- Use over-the-counter pain relievers\n" +
                    "- Apply cold or warm compress\n" +
                    "- Practice stress-reduction techniques");
        } else if (symptoms.contains("fever")) {
            diagnosisList.getItems().addAll(
                    "Viral Infection",
                    "Bacterial Infection",
                    "Flu-like Symptoms"
            );
            adviceArea.setText("Recommended Care:\n" +
                    "- Rest and get plenty of sleep\n" +
                    "- Stay hydrated\n" +
                    "- Take fever-reducing medication\n" +
                    "- Monitor body temperature\n" +
                    "- Seek medical attention if fever persists");
        } else if (symptoms.contains("cough")) {
            diagnosisList.getItems().addAll(
                    "Common Cold",
                    "Bronchitis",
                    "Allergic Reaction"
            );
            adviceArea.setText("Suggested Treatment:\n" +
                    "- Use cough suppressants\n" +
                    "- Stay hydrated\n" +
                    "- Use honey or herbal tea\n" +
                    "- Rest and avoid irritants\n" +
                    "- Consult doctor if cough persists");
        } else {
            diagnosisList.getItems().add("Unable to diagnose");
            adviceArea.setText("General Health Advice:\n" +
                    "- Maintain good hygiene\n" +
                    "- Eat a balanced diet\n" +
                    "- Stay hydrated\n" +
                    "- Get regular exercise\n" +
                    "- Consult a healthcare professional for accurate diagnosis");
        }

        symptomInput.clear();
    }

    @FXML
    public void handleCallDoctor(ActionEvent actionEvent) {
        adviceArea.setText("Connecting to healthcare provider...\n" +
                "Please wait while we connect you to a medical professional.");


        System.out.println("Initiating doctor call protocol");
    }
}