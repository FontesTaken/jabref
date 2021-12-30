package org.jabref.model.entry;

        import org.jabref.model.entry.BibEntry;
        import org.jabref.model.entry.field.StandardField;

        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotEquals;


class AreaOfStudyTest {
    private BibEntry entry1 = new BibEntry()
            .withField(StandardField.AREAOFSTUDY, "software engineer");

    private BibEntry entry2 = new BibEntry()
            .withField(StandardField.AREAOFSTUDY, "artificial intelligence");

    @Test
    void aosConsideredEqualIfSame() {
        assertEquals(StandardField.AREAOFSTUDY, StandardField.AREAOFSTUDY);
    }

    @Test
    void differentAOSEntries() {
        String affiliationOfEntry1 = entry1.getFieldValues().iterator().next();
        String affiliationOfEntry2 = entry2.getFieldValues().iterator().next();
        assertNotEquals(affiliationOfEntry1, affiliationOfEntry2);
    }

    @Test
    void changeInValueOfAOSEntries() {
        entry2.setField(StandardField.AREAOFSTUDY, "software engineer");
        String affiliationOfEntry1 = entry1.getField(StandardField.AREAOFSTUDY).orElse("");
        String affiliationOfEntry2 = entry2.getField(StandardField.AREAOFSTUDY).orElse("");
        assertEquals(affiliationOfEntry2, affiliationOfEntry1);
    }

    @Test
    void differentEntries() {
        assertNotEquals(entry1, entry2);
    }



}
