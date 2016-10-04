package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class TableFormatterTest implements WithAssertions, WithMockito {

    private static final int EXPECTED_FIRST_COLUMN_WIDTH = 23;
    private static final int EXPECTED_SEOND_COLUMN_WIDTH = 11;

    private ColumnWidthCalculator columnWidthCalculator = mock(ColumnWidthCalculator.class);

    private final TableFormatter tableFormatter = new TableFormatter(columnWidthCalculator);

    @Test
    public void calculatesCharacterWidth() throws Exception {
        String[] columnNames = {"testColumn1", "testColumn2"};
        TableRow[] rows = {new TableRow("field1", "field2"),
                new TableRow("field1", "field2"),
                new TableRow("longlonglongfield1..", "field2"),
                new TableRow("field1", "field2")};

        when(columnWidthCalculator.indexes(columnNames, rows)).thenReturn(new int[]{EXPECTED_FIRST_COLUMN_WIDTH, EXPECTED_SEOND_COLUMN_WIDTH});

        String format = tableFormatter.writeTable(columnNames, rows);

        assertThat(format).isEqualTo(
                "----------------------------------\n" +
                        "testColumn1          | testColumn2\n" +
                        "----------------------------------\n" +
                        "field1               | field2\n" +
                        "field1               | field2\n" +
                        "longlonglongfield1.. | field2\n" +
                        "field1               | field2\n" +
                        "----------------------------------");
    }
}