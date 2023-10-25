package seedu.letsgethired.model.application;

import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;
import seedu.letsgethired.commons.util.StringUtil;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.parser.Prefix;

/**
 * Tests that a {@code InternApplication}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsFieldKeywordsPredicate implements Predicate<InternApplication> {
    private static final String EMPTY_FIELD_VALUE = "";

    private final List<Pair<Prefix, String>> fieldKeywords;

    public CompanyContainsFieldKeywordsPredicate(List<Pair<Prefix, String>> fieldKeywords) {
        this.fieldKeywords = fieldKeywords;
    }

    /**
     * Returns whether the keyword has a partial match in the search string.
     *
     * @param searchString String to be searched
     * @param keyword Keyword to be searched for
     * @return whether the keyword has a partial match in the search string
     */
    public boolean partialMatch(String searchString, String keyword) {
        int keywordIndex = 0;
        int searchIndex = 0;
        while (keywordIndex < keyword.length() && searchIndex < searchString.length()) {
            if (Character.toLowerCase(keyword.charAt(keywordIndex))
                    == Character.toLowerCase(searchString.charAt(searchIndex))) {
                keywordIndex++;
            }
            searchIndex++;
        }
        return keywordIndex == keyword.length();
    }

    public String getFieldValue(Prefix fieldPrefix, InternApplication internApplication) {
        if (fieldPrefix.equals(PREFIX_COMPANY)) {
            return internApplication.getCompany().value;
        }
        if (fieldPrefix.equals(PREFIX_CYCLE)) {
            return internApplication.getCycle().value;
        }
        if (fieldPrefix.equals(PREFIX_NOTE)) {
            return internApplication.getNote().value;
        }
        if (fieldPrefix.equals(PREFIX_STATUS)) {
            return internApplication.getStatus().value;
        }
        if (fieldPrefix.equals(PREFIX_ROLE)) {
            return internApplication.getRole().value;
        }

        return EMPTY_FIELD_VALUE;
    }

    @Override
    public boolean test(InternApplication internApplication) {
        return fieldKeywords.stream()
                .anyMatch(keyword -> keyword.getKey().equals(PREFIX_COMPANY)
                            ? partialMatch(getFieldValue(keyword.getKey(), internApplication), keyword.getValue())
                            : StringUtil.containsWordIgnoreCase(getFieldValue(keyword.getKey(), internApplication),
                        keyword.getValue()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyContainsFieldKeywordsPredicate)) {
            return false;
        }

        CompanyContainsFieldKeywordsPredicate otherCompanyContainsFieldKeywordsPredicate =
                (CompanyContainsFieldKeywordsPredicate) other;
        return fieldKeywords.equals(otherCompanyContainsFieldKeywordsPredicate.fieldKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", fieldKeywords).toString();
    }
}
