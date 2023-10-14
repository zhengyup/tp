package seedu.letsgethired.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternTracker} with sample data.
 */
public class SampleDataUtil {
    public static InternApplication[] getSampleInternApplications() {
        return new InternApplication[] {
            new InternApplication(new Company("Apple"), new Role("DevOps"), new Cycle("Summer 2024"),
                    new Status("Pending"), getTagSet("friends")),
            new InternApplication(new Company("Bytedance"), new Role("Systems Integrator"), new Cycle("Summer 2023"),
                    new Status("Rejected"), getTagSet("colleagues", "friends")),
            new InternApplication(new Company("Grab"), new Role("Data intern"),
                    new Cycle("Summer 2103"), new Status("Pending"),
                    getTagSet("neighbours")),
            new InternApplication(new Company("Shoppee"), new Role("SWE"), new Cycle("Off-cycle 2024"),
                    new Status("Rejected"), getTagSet("family")),
            new InternApplication(new Company("Stripe"), new Role("SWE"), new Cycle("Winter 2021"),
                    new Status("Pending"), getTagSet("classmates")),
            new InternApplication(new Company("Jane Street"), new Role("Economist"), new Cycle("Summer 2022"),
                    new Status("Pending"), getTagSet("colleagues"))};
    }

    public static ReadOnlyInternTracker getSampleInternTracker() {
        InternTracker sampleAb = new InternTracker();
        for (InternApplication sampleInternApplication : getSampleInternApplications()) {
            sampleAb.addApplication(sampleInternApplication);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
