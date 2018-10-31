package sample;

import javafx.scene.control.Label;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private ArrayList<String> sideListViewTexts;
    private String bottomTextFieldText ;

    Model() {
        bottomTextFieldText = "";
        sideListViewTexts = new ArrayList();

        // Try restoring saved text from file
        try {
            // Open file to read saved text
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));

                bottomTextFieldText = input.readLine();
                String newSideListText = input.readLine();
                while (newSideListText != null) {
                    sideListViewTexts.add(newSideListText);
                    newSideListText = input.readLine();
                }

                // Close file
                input.close();
            }
        } catch (Exception e) {
            System.out.println("Controller initialize EXCEPTION");
        }
    }

    // write the model to a file
    void save() {
        try {

            // Write the final model to a saved file
            File savedText = new File(getClass().getResource("SavedText.txt").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                if (bottomTextFieldText != null) {
                    writer.write(bottomTextFieldText);
                } else {
                    writer.write("");
                }
                writer.newLine();
                int length = sideListViewTexts.size();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        writer.write(sideListViewTexts.get(i));
                        writer.newLine();
                    }
                } else {
                    writer.write("");
                    writer.newLine();
                }
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Model.save() failed!!!");
        }
    }

    String getBottomTextFieldText() {
        return bottomTextFieldText;
    }
    void setBottomTextFieldText(String updatedText) {
        bottomTextFieldText = updatedText;
    }

    ArrayList getSideListViewTexts() {
        return sideListViewTexts;
    }
    void addToSideListViewTexts(String updatedText) {
        sideListViewTexts.add(updatedText);
    }


    void setAllData(String updatedBottomTextFieldText, List<Label> updatedSideListView) {
        // Update the model with all text currently seen in View
        bottomTextFieldText = updatedBottomTextFieldText;

        int length = updatedSideListView.size();
        sideListViewTexts.clear();
        for (int i = 0; i < length; i++) {
            sideListViewTexts.add(updatedSideListView.get(i).getText());
        }
    }
}
