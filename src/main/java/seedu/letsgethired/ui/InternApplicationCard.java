package seedu.letsgethired.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.letsgethired.model.application.InternApplication;

/**
 * An UI component that displays information of a {@code InternApplication}.
 */
public class InternApplicationCard extends UiPart<Region> {

    private static final String FXML = "InternApplicationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on InternTracker level 4</a>
     */

    public final InternApplication internApplication;

    @FXML
    private HBox cardPane;
    @FXML
    private Label company;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label note;
    @FXML
    private Label cycle;

    /**
     * Creates a {@code InternApplicationCard} with the given {@code internApplication} and index to display.
     */
    public InternApplicationCard(InternApplication internApplication, int displayedIndex) {
        super(FXML);
        this.internApplication = internApplication;
        id.setText(displayedIndex + ". ");
        company.setText(internApplication.getCompany().value);
        role.setText(internApplication.getRole().value);
        status.setText(internApplication.getStatus().value);
        cycle.setText(internApplication.getCycle().value);
    }
}
