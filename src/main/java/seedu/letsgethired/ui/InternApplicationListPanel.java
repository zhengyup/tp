package seedu.letsgethired.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Panel containing the list of intern applications.
 */
public class InternApplicationListPanel extends UiPart<Region> {
    private static final String FXML = "InternApplicationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternApplicationListPanel.class);

    @FXML
    private ListView<InternApplication> internApplicationListView;

    /**
     * Creates a {@code InternApplicationListPanel} with the given {@code ObservableList}.
     */
    public InternApplicationListPanel(ObservableList<InternApplication> internApplicationList) {
        super(FXML);
        internApplicationListView.setItems(internApplicationList);
        internApplicationListView.setCellFactory(listView -> new InternApplicationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code InternApplication} using a {@code InternApplicationCard}.
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

}
