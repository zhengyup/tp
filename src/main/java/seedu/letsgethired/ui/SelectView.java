package seedu.letsgethired.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import static java.util.Objects.requireNonNull;

public class SelectView extends UiPart<Region>{
    private static final String FXML = "SelectView.fxml";
    @FXML
    private TextArea selectView;

    public SelectView() {
        super(FXML);
        selectView.setText("This is the select view");
    }

    public void setNotesOfCard(String notes) {
        requireNonNull(notes);
        selectView.setText(notes);
    }
}
