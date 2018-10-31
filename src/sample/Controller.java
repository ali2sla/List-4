package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;


public class Controller {
    public Label questions;
    public ListView<Label> sideListView;
    public TextField bottomTextField;
    private Model model;

    public void initialize() {
        questions.setText("Question 1: What will you bring if you were stranded on an island?");
        model = new Model();

        // Now that model has been initialized from a file, update View with saved values from Model
        bottomTextField.setText(model.getBottomTextFieldText());
        ArrayList sideListViewTexts = model.getSideListViewTexts();
        for (int i = 0; i < sideListViewTexts.size(); i++) {
            sideListView.getItems().add(new Label((String)sideListViewTexts.get(i)));
        }
    }

    void save() {
        System.out.println("Controller save");
        // push the latest GUI text into the model
        model.setAllData(bottomTextField.getText(), sideListView.getItems());
        model.save();
    }

    public void bottomTextFieldReady() {
        System.out.println("bottomTextFieldReady: " + bottomTextField.getText());
        // Update the list view with the text from the bottom text field
        sideListView.getItems().add(new Label(bottomTextField.getText()));
        // Clear the bottom text field because it has been used.
        bottomTextField.setText("");
    }
}
