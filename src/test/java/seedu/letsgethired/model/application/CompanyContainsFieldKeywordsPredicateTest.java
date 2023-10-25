package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.letsgethired.testutil.InternApplicationBuilder;


public class CompanyContainsFieldKeywordsPredicateTest {
    @Test
    public void equals() {
        String firstPredicateSearchString = "first";
        String secondPredicateSearchString = "second";

        CompanyContainsFieldKeywordsPredicate firstPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, firstPredicateSearchString)));
        CompanyContainsFieldKeywordsPredicate secondPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, secondPredicateSearchString)));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CompanyContainsFieldKeywordsPredicate firstPredicateCopy = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, firstPredicateSearchString)));
        assertEquals(firstPredicate, firstPredicateCopy);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different intern application -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_companyPartialMatch_returnsTrue() {
        // Empty search string
        CompanyContainsFieldKeywordsPredicate emptyPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "")));
        assertTrue(emptyPredicate.test(new InternApplicationBuilder().withCompany("Google").build()));

        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "Jane")));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Partial match
        CompanyContainsFieldKeywordsPredicate partialMatchPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "J S")));
        assertTrue(partialMatchPredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Mixed case
        CompanyContainsFieldKeywordsPredicate mixedCasePredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "jnsT")));
        assertTrue(mixedCasePredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Non-matching
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "Google")));
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Incorrect spacing
        CompanyContainsFieldKeywordsPredicate incorrectSpacingPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "JaneStreet ")));
        assertFalse(incorrectSpacingPredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Keywords match role, but does not match company
        predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "SWE")));
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Google").withRole("SWE").build()));
    }

    @Test
    public void toStringMethod() {
        String searchStringCompany = "Jane Street";
        String searchStringCycle = "Summer";
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, searchStringCompany),
                        new Pair<>(PREFIX_CYCLE, searchStringCycle)));

        String expected =
                CompanyContainsFieldKeywordsPredicate.class.getCanonicalName()
                        + "{keywords=[n/=" + searchStringCompany
                        + ", c/=" + searchStringCycle + "]}";
        assertEquals(expected, predicate.toString());
    }
}
