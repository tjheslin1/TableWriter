package io.github.tjheslin1.tablewriter;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class TableFormatterTest implements WithAssertions, WithMockito {

    private final TableFormatter tableFormatter = new TableFormatter(new ColumnWidthCalculator());

    @Test
    public void noRows() throws Exception {
        String[] columnNames = {"testColumn1", "testColumn2"};
        TableRow[] rows = {};

        String format = tableFormatter.formatTable(columnNames, rows);

        assertThat(format).isEqualTo(
                "+-------------+-------------+" + System.lineSeparator() +
                "| testColumn1 | testColumn2 |" + System.lineSeparator() +
                "+-------------+-------------+" + System.lineSeparator() +
                "+-------------+-------------+" + System.lineSeparator());
    }

    @Test
    public void formatsTable() throws Exception {
        String[] columnNames = {"testColumn1", "testColumn2"};
        TableRow[] rows = {new TableRow("field1", "field2"),
                new TableRow("field1", "field2"),
                new TableRow("longlonglongfield1..", "field2"),
                new TableRow("field1", "field2")};

        String format = tableFormatter.formatTable(columnNames, rows);

        assertThat(format).isEqualTo(
                "+----------------------+-------------+" + System.lineSeparator() +
                "| testColumn1          | testColumn2 |" + System.lineSeparator() +
                "+----------------------+-------------+" + System.lineSeparator() +
                "| field1               | field2      |" + System.lineSeparator() +
                "| field1               | field2      |" + System.lineSeparator() +
                "| longlonglongfield1.. | field2      |" + System.lineSeparator() +
                "| field1               | field2      |" + System.lineSeparator() +
                "+----------------------+-------------+" + System.lineSeparator());
    }
}