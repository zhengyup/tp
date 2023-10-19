package seedu.letsgethired.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;

/**
 * A ui for utility buttons below the select view
 */
public class UtilityButtonBar extends UiPart<Region> {
    private static final String FXML = "UtilityButtonBar.fxml";
    private final CommandExecutor commandExecutor;
    private final InternApplicationListPanel listPanel;
    @FXML
    private Button deleteNotesButton;

    /**
     * Creates a {@code UtilityButtonBar}.
     */
    public UtilityButtonBar(InternApplicationListPanel listPanel, CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.listPanel = listPanel;
    }

    /**
     * Deletes the selected card in UI
     */
    @FXML
    public void handleDeleteClick() {
        int itemIndex = listPanel.getSelectedItemIndex();
        String commandText = "delete " + itemIndex;
        try {
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            //should never reach here unless the hard-coded input is wrong
        }
    }
}
