package io.github.tjheslin1.tl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class TableLogger {

    private final List<String> columnNames;
    private final List<TableRow> rows;
    private final TableFormatter tableFormatter;

    public TableLogger(TableFormatter tableFormatter) {
        this.tableFormatter = tableFormatter;

        this.columnNames = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public TableLogger withColumn(String columnName) {
        columnNames.add(columnName);
        return this;
    }

    public void addRow(String... values) {
        if (values.length != columnNames.size()) {
            throw new IllegalStateException(
                    format("Attempting to log data with '%s' values into '%s' columns", values.length, columnNames.size()));
        }

        rows.add(new TableRow(asList(values)));
    }

    public void print() {
        System.out.println(tableFormatter.format(columnNames, rows));
    }
}
