package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;


public class Controller {
    public Label questions;
    public ListView<Label> sideListView;
    public TextField bottomTextField;
    private Model model;

    public void initialize() {
        questions.setText("Question 1: What will you bring if you were stranded on an island?");

        // Try restoring saved text from file
        try {
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));
                model = new Model(input);
                input.close();
            } else {
                model = new Model();
            }
        } catch (Exception e) {
            System.out.println("Controller initialize EXCEPTION");
            model = new Model();
        }

        // Now that model has been initialized from a file, update View with saved values from Model
        bottomTextField.setText(model.getBottomTextFieldText());
        ArrayList sideListViewTexts = model.getSideListViewTexts();
        for (int i = 0; i < sideListViewTexts.size(); i++) {
            sideListView.getItems().add(new Label((String)sideListViewTexts.get(i)));
        }
    }

    void save() {
        System.out.println("Controller save");

        // Update the model with final text typed in View
        model.setBottomTextFieldText(bottomTextField.getText());
        int length = sideListView.getItems().size();
        model.getSideListViewTexts().clear();
        for (int i = 0; i < length; i++) {
            model.addToSideListViewTexts(sideListView.getItems().get(i).getText());
        }

        // Write the final model to a saved file
        try {
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                model.save(writer);
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Controller save EXCEPTION");
        }
    }

    public void bottomTextFieldReady() {
        System.out.println("bottomTextFieldReady: " + bottomTextField.getText());

        // Update the list view with the text from the bottom text field
        sideListView.getItems().add(new Label(bottomTextField.getText()));
        // Clear the bottom text field because it has been used.
        bottomTextField.setText("");
    }
}
