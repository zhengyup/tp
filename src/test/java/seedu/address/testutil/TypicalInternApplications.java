package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
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
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withRole("SWE Intern")
            .withTags("friends").build();
    public static final InternApplication BENSON = new InternApplicationBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withRole("Data Engineering Intern")
            .withTags("owesMoney", "friends").build();
    public static final InternApplication CARL = new InternApplicationBuilder().withName("Carl Kurz")
            .withRole("Full Stack Intern")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final InternApplication DANIEL = new InternApplicationBuilder().withName("Daniel Meier")
            .withRole("Back End Intern")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final InternApplication ELLE = new InternApplicationBuilder().withName("Elle Meyer")
            .withRole("Front End Intern")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final InternApplication FIONA = new InternApplicationBuilder().withName("Fiona Kunz")
            .withRole("Web Dev Intern")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final InternApplication GEORGE = new InternApplicationBuilder().withName("George Best")
            .withRole("DevOps Intern")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final InternApplication HOON = new InternApplicationBuilder().withName("Hoon Meier")
            .withRole("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final InternApplication IDA = new InternApplicationBuilder().withName("Ida Mueller")
            .withRole("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final InternApplication AMY = new InternApplicationBuilder().withName(VALID_NAME_AMY)
            .withRole(VALID_ROLE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final InternApplication BOB = new InternApplicationBuilder().withName(VALID_NAME_BOB)
            .withRole(VALID_ROLE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
