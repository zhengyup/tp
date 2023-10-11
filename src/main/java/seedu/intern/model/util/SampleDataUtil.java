package seedu.intern.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intern.model.InternTracker;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.application.Company;
import seedu.intern.model.application.Cycle;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternTracker} with sample data.
 */
public class SampleDataUtil {
    public static InternApplication[] getSamplePersons() {
        return new InternApplication[] {
            new InternApplication(new Company("Alex Yeoh"), new Role("DevOps"), new Cycle("Summer 2024"),
                    new Status("Pending"), getTagSet("friends")),
            new InternApplication(new Company("Bernice Yu"), new Role("Systems Integrator"), new Cycle("Summer 2023"),
                    new Status("Rejected"), getTagSet("colleagues", "friends")),
            new InternApplication(new Company("Charlotte Oliveiro"), new Role("Jobless"),
                    new Cycle("Summer 2103"), new Status("Pending"),
                    getTagSet("neighbours")),
            new InternApplication(new Company("David Li"), new Role("SWE"), new Cycle("Off-cycle 2024"),
                    new Status("Rejected"), getTagSet("family")),
            new InternApplication(new Company("Irfan Ibrahim"), new Role("SWE"), new Cycle("Winter 2021"),
                    new Status("Pending"), getTagSet("classmates")),
            new InternApplication(new Company("Roy Balakrishnan"), new Role("Economist"), new Cycle("Summer 2022"),
                    new Status("Pending"), getTagSet("colleagues"))};
    }

    public static ReadOnlyInternTracker getSampleInternTracker() {
        InternTracker sampleAb = new InternTracker();
        for (InternApplication sampleInternApplication : getSamplePersons()) {
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
