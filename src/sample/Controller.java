package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Pagination;
import java.util.ArrayList;


public class Controller {
    public Label question;
    public ListView<Label> sideListView;
    public TextField bottomTextField;
    private Model model;
    private Pagination page;
    public Label questionList;

    int currentQuestion;
    int totalQuestions;

    String[] questions;

    public void initialize() {
        currentQuestion = 0;
        totalQuestions = 5;
        questions = new String[totalQuestions + 1];
        questions[0] = "Are you ready to start the survey?";
        questions[1] = "Question 1: What will you bring if you were stranded on an island?";
        questions[2] = "Question 2: If you receive a request to make dinner for the president, would you do it?";
        questions[3] = "Question 3: If someone was coughing repeatedly at your restaurant after eating, what will you do?";
        questions[4] = "Question 4: What is something that is in your closet?";
        questions[5] = "Question 5: If free candy is free candy, would you go to the haunted house?";

        question.setText(questions[currentQuestion]);
        questionList.setText("Question " + currentQuestion + " of " + totalQuestions);

        model = new Model();
        page = new Pagination(6, 0);

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
        sideListView.getItems().add(new Label(question.getText()));
        sideListView.getItems().add(new Label(bottomTextField.getText()));
        // Clear the bottom text field because it has been used.
        bottomTextField.setText("");

        // Go to next question
        currentQuestion = currentQuestion + 1;
        question.setText(questions[currentQuestion]);
        questionList.setText("Question " + currentQuestion + " of " + totalQuestions);
    }

    public void backTextReady() {
        currentQuestion = currentQuestion - 1;
        question.setText(questions[currentQuestion]);
        questionList.setText("Question " + currentQuestion + " of " + totalQuestions);
    }

    public void nextTextReady() {
        currentQuestion = currentQuestion + 1;
        question.setText(questions[currentQuestion]);
        questionList.setText("Question " + currentQuestion + " of " + totalQuestions);
    }

    public void clearButtonReady() {
        System.out.println("Controller cleared");
        model.removeAllData(bottomTextField.getText(), sideListView.getItems());
        model.save();
    }
}
