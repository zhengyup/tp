package seedu.letsgethired.testutil;

import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_ACCEPTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_REJECTED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication JANE_STREET = new InternApplicationBuilder()
            .withCompany("Jane Street")
            .withStatus("Pending")
            .withCycle("Summer 2024")
            .withNote("Jane Street is the leading market maker in the APAC region")
            .withRole("SWE Intern")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication OPTIVER = new InternApplicationBuilder()
            .withCompany("Optiver")
            .withStatus("Pending")
            .withNote("Optiver needs very good mental math skills")
            .withCycle("Summer 2023")
            .withRole("Data Engineering Intern")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication GOOGLE = new InternApplicationBuilder()
            .withCompany("Google")
            .withRole("Full Stack Intern")
            .withNote("Need to revise Rust before the Google interview")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication META = new InternApplicationBuilder()
            .withCompany("Meta")
            .withRole("Back End Intern")
            .withNote("Need to revise Rust before the Meta interview")
            .withCycle("Off-cycle 2020")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication BYTEDANCE = new InternApplicationBuilder()
            .withCompany("Bytedance")
            .withRole("Front End Intern")
            .withNote("Bytedance requires back end developers to know the MERN stack")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication GRAB = new InternApplicationBuilder()
            .withCompany("Grab")
            .withRole("Web Dev Intern")
            .withNote("Grab online assessment has a difficult mental math section")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication STRIPE = new InternApplicationBuilder()
            .withCompany("Stripe")
            .withRole("DevOps Intern")
            .withNote("Stripe requires previous experience with SQL")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();

    // Manually added
    public static final InternApplication BOSCH = new InternApplicationBuilder().withCompany("Bosch")
            .withRole("AI Research")
            .withCycle("Summer 2021").withStatus("pending").build();
    public static final InternApplication SCHNEIDER = new InternApplicationBuilder().withCompany("Schneider")
            .withRole("IoT Engineer")
            .withCycle("Summer 2021").withStatus("accepted").build();

    // Manually added - Intern Application's details found in {@code CommandTestUtil}
    public static final InternApplication A = new InternApplicationBuilder()
            .withCompany(VALID_COMPANY_JANE_STREET)
            .withRole(VALID_ROLE_FULL_STACK)
            .withCycle(VALID_CYCLE_SUMMER)
            .withStatus(VALID_STATUS_ACCEPTED)
            .withDeadline(VALID_DEADLINE)
            .build();
    public static final InternApplication B = new InternApplicationBuilder()
            .withCompany(VALID_COMPANY_BYTEDANCE)
            .withRole(VALID_ROLE_BACK_END)
            .withCycle(VALID_CYCLE_WINTER)
            .withStatus(VALID_STATUS_REJECTED)
            .withDeadline(VALID_DEADLINE)
            .build();

    public static final String KEYWORD_MATCHING_OCBC = "OCBC"; // A keyword that matches OCBC

    private TypicalInternApplications() {
    } // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical intern applications.
     */
    public static InternTracker getTypicalInternTracker() {
        InternTracker ab = new InternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        return new ArrayList<>(Arrays.asList(JANE_STREET, OPTIVER, GOOGLE, META, BYTEDANCE, GRAB, STRIPE));
    }
}
