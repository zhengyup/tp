package seedu.letsgethired.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.logic.commands.CommandResult;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Panel containing the list of intern applications.
 */
public class InternApplicationListPanel extends UiPart<Region> {
    private static final String FXML = "InternApplicationListPanel.fxml";
    private CommandExecutor commandExecutor;
    private final Logger logger = LogsCenter.getLogger(InternApplicationListPanel.class);
    @FXML
    private ListView<InternApplication> internApplicationListView;
    private EventHandler<MouseEvent> onCardClick = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            int itemIndex = getSelectedItemIndex();
            String commandText = "view " + itemIndex;
            try {
                commandExecutor.execute(commandText);
            } catch (CommandException | ParseException e) {
                //should never reach here unless the hard-coded input is wrong
            }
        }
    };

    /**
     * Creates a {@code InternApplicationListPanel} with the given {@code ObservableList}.
     */
    public InternApplicationListPanel(ObservableList<InternApplication> internApplicationList, CommandExecutor commandExecutor) {
        super(FXML);
        internApplicationListView.setItems(internApplicationList);
        internApplicationListView.setCellFactory(listView -> new InternApplicationListViewCell());
        this.commandExecutor = commandExecutor;

        internApplicationListView.setOnMouseClicked(onCardClick);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of
     * a {@code InternApplication} using a {@code InternApplicationCard}.
     */
    class InternApplicationListViewCell extends ListCell<InternApplication> {
        @Override
        protected void updateItem(InternApplication internApplication, boolean empty) {
            super.updateItem(internApplication, empty);

            if (empty || internApplication == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternApplicationCard(internApplication, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Gets selected Intern Application from the list panel
     * @return Selected InternApplication from the list
     */
    public InternApplication getSelectedItem() {
        return internApplicationListView.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets index of selected Intern Application from the list panel
     * @return Index of selected InternApplication from the list
     */
    public int getSelectedItemIndex() {
        return internApplicationListView.getSelectionModel().getSelectedIndex() + 1;
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.letsgethired.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
