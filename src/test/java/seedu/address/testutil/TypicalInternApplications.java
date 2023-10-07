package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CYCLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CYCLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternTracker;
import seedu.address.model.application.InternApplication;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication ALICE = new InternApplicationBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withCycle("Summer 2024")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final InternApplication BENSON = new InternApplicationBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withCycle("Summer 2023").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final InternApplication CARL = new InternApplicationBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withCycle("Summer 2021").withAddress("wall street").build();
    public static final InternApplication DANIEL = new InternApplicationBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withCycle("Summer 2021").withAddress("10th street").withTags("friends").build();
    public static final InternApplication ELLE = new InternApplicationBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withCycle("Summer 2021").withAddress("michegan ave").build();
    public static final InternApplication FIONA = new InternApplicationBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withCycle("Summer 2021").withAddress("little tokyo").build();
    public static final InternApplication GEORGE = new InternApplicationBuilder().withName("George Best")
            .withPhone("9482442")
            .withCycle("Summer 2021").withAddress("4th street").build();

    // Manually added
    public static final InternApplication HOON = new InternApplicationBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withCycle("Summer 2021").withAddress("little india").build();
    public static final InternApplication IDA = new InternApplicationBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withCycle("Summer 2021").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final InternApplication AMY = new InternApplicationBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withCycle(VALID_CYCLE_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final InternApplication BOB = new InternApplicationBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withCycle(VALID_CYCLE_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternApplications() {} // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical persons.
     */
    public static InternTracker getTypicalInternTracker() {
        InternTracker ab = new InternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
