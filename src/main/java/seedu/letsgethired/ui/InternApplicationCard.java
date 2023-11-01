package seedu.letsgethired.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.InternApplication;

/**
 * An UI component that displays information of a {@code InternApplication}.
 */
public class InternApplicationCard extends UiPart<Region> {

    private static final String FXML = "InternApplicationListCard.fxml";
    private static final String ACCEPTED_COLOR = "acceptStatus";
    private static final String ASSESSMENT_COLOR = "assessStatus";
    private static final String INTERVIEW_COLOR = "interviewStatus";
    private static final String OFFERED_COLOR = "offerStatus";
    private static final String PENDING_COLOR = "pendingStatus";
    private static final String REJECTED_COLOR = "rejectStatus";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on InternTracker level 4</a>
     */

    public final InternApplication internApplication;

    private final CommandExecutor commandExecutor;
    private int indexNum;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label company;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label cycle;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code InternApplicationCard} with the given {@code internApplication} and index to display.
     */
    public InternApplicationCard(InternApplication internApplication,
                                 int displayedIndex,
                                 CommandExecutor commandExecutor) {
        super(FXML);
        this.internApplication = internApplication;
        this.commandExecutor = commandExecutor;
        this.indexNum = displayedIndex;
        id.setText(displayedIndex + ". ");
        company.setText(internApplication.getCompany().value);
        role.setText(internApplication.getRole().value);
        cycle.setText(internApplication.getCycle().value);
        deadline.setText(internApplication.getDeadline().value);
        status.setText(internApplication.getStatus().value);
        setStatusColor(internApplication.getStatus().value);
    }

    /**
     * Gets the status label color coded by the internship application status.
     *
     * @return the status label color coded by the internship application status.
     */
    private void setStatusColor(String statusString) {
        switch (statusString) {
        case "Interview":
            status.setId(INTERVIEW_COLOR);
            break;
        case "Accepted":
            status.setId(ACCEPTED_COLOR);
            break;
        case "Offered":
            status.setId(OFFERED_COLOR);
            break;
        case "Rejected":
            status.setId(REJECTED_COLOR);
            break;
        case "Assessment":
            status.setId(ASSESSMENT_COLOR);
            break;
        case "Pending":
            status.setId(PENDING_COLOR);
            break;
        default:
            break;
        }
    }

    /**
     * Displays card details on the SelectView
     */
    @FXML
    public void handleCardClick() {
        String commandText = "view " + this.indexNum;
        try {
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            //should never reach here unless the hard-coded input is wrong
            assert false : "The program should never reach this block";
        }
    }
}
