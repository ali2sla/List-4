package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public Label questions;
    public TextField answer;

    public void initialize() {
        questions.setText("Question 1: What will you bring if you were stranded on an island?");
    }

}
