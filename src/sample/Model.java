package sample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Model {
    private ArrayList<String> sideListViewTexts;
    private String bottomTextFieldText ;

    Model() {
        System.out.println("Model()");
        bottomTextFieldText = "";
        sideListViewTexts = new ArrayList();
    }

    // Create a model that is restored from saved data
    Model(BufferedReader input) {
        System.out.println("Model(BufferedReader input)");
        try {
            bottomTextFieldText = input.readLine();
            sideListViewTexts = new ArrayList();
            String newSideListText = input.readLine();
            while (newSideListText != null) {
                sideListViewTexts.add(newSideListText);
                newSideListText = input.readLine();
            }
        } catch (Exception e) {
            System.out.println("Model reading failed!!!");
        }
    }

    // write the model to a file
    void save(BufferedWriter output) {
        try {
            if (bottomTextFieldText != null) {
                output.write(bottomTextFieldText);
            } else {
                output.write("");
            }
            output.newLine();
            int length = sideListViewTexts.size();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    output.write(sideListViewTexts.get(i));
                    output.newLine();
                }
            } else {
                output.write("");
                output.newLine();
            }
        } catch (Exception e) {

            System.out.println("Model writing failed!!!");
            e.printStackTrace();
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

}
