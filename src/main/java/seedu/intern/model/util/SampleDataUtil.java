package seedu.intern.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intern.model.InternTracker;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.application.Email;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Name;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternTracker} with sample data.
 */
public class SampleDataUtil {
    public static InternApplication[] getSamplePersons() {
        return new InternApplication[] {
            new InternApplication(new Name("Alex Yeoh"), new Role("87438807"), new Email("alexyeoh@example.com"),
                new Status("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new InternApplication(new Name("Bernice Yu"), new Role("99272758"), new Email("berniceyu@example.com"),
                new Status("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new InternApplication(new Name("Charlotte Oliveiro"), new Role("93210283"),
                    new Email("charlotte@example.com"),
                new Status("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new InternApplication(new Name("David Li"), new Role("91031282"), new Email("lidavid@example.com"),
                new Status("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new InternApplication(new Name("Irfan Ibrahim"), new Role("92492021"), new Email("irfan@example.com"),
                new Status("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new InternApplication(new Name("Roy Balakrishnan"), new Role("92624417"), new Email("royb@example.com"),
                new Status("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
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
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
