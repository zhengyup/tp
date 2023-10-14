package seedu.letsgethired.testutil;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A utility class to help with building Interntracker objects.
 * Example usage: <br>
 *     {@code InternTracker ab = new InternTrackerBuilder().withPerson("John", "Doe").build();}
 */
public class InternTrackerBuilder {

    private InternTracker internTracker;

    public InternTrackerBuilder() {
        internTracker = new InternTracker();
    }

    public InternTrackerBuilder(InternTracker internTracker) {
        this.internTracker = internTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code InternTracker} that we are building.
     */
    public InternTrackerBuilder withPerson(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        return this;
    }

    public InternTracker build() {
        return internTracker;
    }
}
