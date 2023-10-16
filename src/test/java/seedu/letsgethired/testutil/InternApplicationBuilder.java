package seedu.letsgethired.testutil;

import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Status;

/**
 * A utility class to help with building InternApplication objects.
 */
public class InternApplicationBuilder {

    public static final String DEFAULT_COMPANY = "Amy Bee";
    public static final String DEFAULT_ROLE = "Intern";
    public static final String DEFAULT_CYCLE = "Summer 2021";
    public static final String DEFAULT_STATUS = "Pending";
    public static final String DEFAULT_NOTE = "Jane Street is the leading market maker in the APAC region";

    private Company company;
    private Role role;
    private Cycle cycle;
    private Status status;
    private Note note;

    /**
     * Creates a {@code InternApplicationBuilder} with the default details.
     */
    public InternApplicationBuilder() {
        company = new Company(DEFAULT_COMPANY);
        role = new Role(DEFAULT_ROLE);
        cycle = new Cycle(DEFAULT_CYCLE);
        status = new Status(DEFAULT_STATUS);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the InternApplicationBuilder with the data of {@code internApplicationToCopy}.
     */
    public InternApplicationBuilder(InternApplication internApplicationToCopy) {
        company = internApplicationToCopy.getCompany();
        role = internApplicationToCopy.getRole();
        cycle = internApplicationToCopy.getCycle();
        status = internApplicationToCopy.getStatus();
        note = internApplicationToCopy.getNote();
    }

    /**
     * Sets the {@code Company} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withCycle(String email) {
        this.cycle = new Cycle(email);
        return this;
    }

    public InternApplication build() {
        return new InternApplication(company, role, cycle, note, status);
    }

}
