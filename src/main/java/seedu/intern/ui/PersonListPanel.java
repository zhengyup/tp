package seedu.intern.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.model.application.InternApplication;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<InternApplication> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<InternApplication> internApplicationList) {
        super(FXML);
        personListView.setItems(internApplicationList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<InternApplication> {
        @Override
        protected void updateItem(InternApplication internApplication, boolean empty) {
            super.updateItem(internApplication, empty);

            if (empty || internApplication == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(internApplication, getIndex() + 1).getRoot());
            }
        }
    }

}
