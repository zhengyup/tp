package seedu.letsgethired.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the select view that displays the notes of a selected application.
 */
public class SelectView extends UiPart<Region> {
    private static final String FXML = "SelectView.fxml";
    @FXML
    private TextArea selectView;

    /**
     * Creates a {@code SelectView}.
     */
    public SelectView() {
        super(FXML);
    }

    public void setNotesOfCard(String notes) {
        requireNonNull(notes);
        selectView.setText(notes);
    }
}
