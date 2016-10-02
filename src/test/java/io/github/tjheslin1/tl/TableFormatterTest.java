package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class TableFormatterTest implements WithAssertions, WithMockito {

    private ColumnWitdthCalculator columnWitdthCalculator = mock(ColumnWitdthCalculator.class);
    private final TableFormatter tableFormatter = new TableFormatter(columnWitdthCalculator);

    @Test
    public void calculatesCharacterWidth() throws Exception {
        String[] columnNames = {"testColumn1", "testColumn2"};
        TableRow[] rows = {new TableRow("field1", "field2"),
                new TableRow("field1", "field2"),
                new TableRow("longlonglongfield1..", "field2"),
                new TableRow("field1", "field2")};

        when(columnWitdthCalculator.indexes(columnNames, rows)).thenReturn(new int[]{23, 11});

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