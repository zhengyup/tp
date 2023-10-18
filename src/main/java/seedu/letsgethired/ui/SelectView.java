package seedu.letsgethired.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.letsgethired.model.application.InternApplication;

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

    /**
     * Sets detail text onto the select view text area
     * @param entry the intern application to be viewed
     */
    public void displayDetails(InternApplication entry) {
        requireNonNull(entry);
        String details = "Company: " + entry.getCompany().toString() + "\n"
                + "Role:" + entry.getRole().toString() + "\n"
                + "Cycle:" + entry.getCycle().toString() + "\n"
                + "Status:" + entry.getStatus().toString() + "\n"
                + "Additional Notes:\n" + entry.getNote().toString();
        selectView.setText(details);
    }
}
