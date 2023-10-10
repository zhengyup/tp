package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternTracker;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.Address;
import seedu.address.model.application.Cycle;
import seedu.address.model.application.InternApplication;
import seedu.address.model.application.Name;
import seedu.address.model.application.Role;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static InternApplication[] getSamplePersons() {
        return new InternApplication[] {
            new InternApplication(new Name("Alex Yeoh"), new Role("87438807"), new Cycle("Summer 2024"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new InternApplication(new Name("Bernice Yu"), new Role("99272758"), new Cycle("Summer 2023"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends")),
            new InternApplication(new Name("Charlotte Oliveiro"), new Role("93210283"),
                    new Cycle("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new InternApplication(new Name("David Li"), new Role("91031282"), new Cycle("Off-cycle 2024"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family")),
            new InternApplication(new Name("Irfan Ibrahim"), new Role("92492021"), new Cycle("Winter 2021"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates")),
            new InternApplication(new Name("Roy Balakrishnan"), new Role("92624417"), new Cycle("Summer 2022"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"))};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
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
