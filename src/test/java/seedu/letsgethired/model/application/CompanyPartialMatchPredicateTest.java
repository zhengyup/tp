package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.testutil.InternApplicationBuilder;

public class CompanyPartialMatchPredicateTest {
    @Test
    public void equals() {
        String firstPredicateSearchString = "first";
        String secondPredicateSearchString = "second";

        CompanyPartialMatchPredicate firstPredicate = new CompanyPartialMatchPredicate(firstPredicateSearchString);
        CompanyPartialMatchPredicate secondPredicate = new CompanyPartialMatchPredicate(secondPredicateSearchString);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CompanyPartialMatchPredicate firstPredicateCopy = new CompanyPartialMatchPredicate(firstPredicateSearchString);
        assertEquals(firstPredicate, firstPredicateCopy);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different intern application -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_companyPartialMatch_returnsTrue() {
        // Empty search string
        CompanyPartialMatchPredicate emptyPredicate = new CompanyPartialMatchPredicate("");
        assertTrue(emptyPredicate.test(new InternApplicationBuilder().withCompany("Google").build()));

        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate("Jane");
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Partial match
        CompanyPartialMatchPredicate partialMatchPredicate = new CompanyPartialMatchPredicate("J S");
        assertTrue(partialMatchPredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Mixed case
        CompanyPartialMatchPredicate mixedCasePredicate = new CompanyPartialMatchPredicate("jnsT");
        assertTrue(mixedCasePredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Non-matching
        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate("Google");
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Incorrect spacing
        CompanyPartialMatchPredicate incorrectSpacingPredicate = new CompanyPartialMatchPredicate("JaneStreet ");
        assertFalse(incorrectSpacingPredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Keywords match role, but does not match company
        predicate = new CompanyPartialMatchPredicate("SWE");
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Google").withRole("SWE").build()));
    }

    @Test
    public void toStringMethod() {
        String searchString = "Jane Street";
        CompanyPartialMatchPredicate predicate = new CompanyPartialMatchPredicate(searchString);

        String expected =
                CompanyPartialMatchPredicate.class.getCanonicalName() + "{searchString=" + searchString + "}";
        assertEquals(expected, predicate.toString());
    }
}
