package io.github.tjheslin1.tablewriter;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class RowAppenderTest implements WithAssertions, WithMockito {

    private final TableWriter tableWriter = mock(TableWriter.class);

    @Test
    public void throwsExceptionIfNotEnoughRowValuesAreProvidedWhenThisCheckIsEnabled() throws Exception {
        when(tableWriter.columnCount()).thenReturn(3);

        RowAppender rowAppender = new RowAppender(tableWriter, true);

        rowAppender.row("val1", "val2", "val3");
        try {
            rowAppender.row("valA", "valB");
            fail("IllegalStateException should have been thrown as we are enforcing the correct number of values.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Attempting to log data with '2' values into '3' columns");
        }
    }

    @Test
    public void padsUnprovidedCellsWithEmptyStringsIfNotEnforcingRowLength() throws Exception {
        RowAppender rowAppender = new TableWriter(table -> {
            String expected = "+---------+---------+---------+\n" +
                    "| header1 | header2 | header3 |\n" +
                    "+---------+---------+---------+\n" +
                    "| val1    | val2    | val3    |\n" +
                    "| valA    | valB    |         |\n" +
                    "+---------+---------+---------+\n";
            assertThat(table).isEqualTo(expected);
        })
                .withColumn("header1").withColumn("header2").withColumn("header3")
                .withRows(false);

        rowAppender.row("val1", "val2", "val3");
        rowAppender.row("valA", "valB");

        rowAppender.printTable();
    }
}