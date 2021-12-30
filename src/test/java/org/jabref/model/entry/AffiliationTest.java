package org.jabref.model.entry;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class AffiliationTest {
    private BibEntry entry1 = new BibEntry()
            .withField(StandardField.AFFILIATION, "FCT");

    private BibEntry entry2 = new BibEntry()
            .withField(StandardField.AFFILIATION, "Tecnico");

    @Test
    void affiliationConsideredEqualIfSame() {
        assertEquals(StandardField.AFFILIATION, StandardField.AFFILIATION);
    }

    @Test
    void differentAffiliationEntries() {
        String affiliationOfEntry1 = entry1.getFieldValues().iterator().next();
        String affiliationOfEntry2 = entry2.getFieldValues().iterator().next();
        assertNotEquals(affiliationOfEntry1, affiliationOfEntry2);
    }

    @Test
    void changeInValueOfAffiliationEntries() {
        entry2.setField(StandardField.AFFILIATION, "FCT");
        String affiliationOfEntry1 = entry1.getField(StandardField.AFFILIATION).orElse("");
        String affiliationOfEntry2 = entry2.getField(StandardField.AFFILIATION).orElse("");
        assertEquals(affiliationOfEntry2, affiliationOfEntry1);
    }

    @Test
    void differentEntries() {
        assertNotEquals(entry1, entry2);
    }



}
