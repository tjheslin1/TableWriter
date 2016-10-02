package io.github.tjheslin1.tl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TableLogger {

    private final List<String> columnNames;
    private final List<TableRow> rows;

    private final TableFormatter tableFormatter;
    private final OutputStrategy outputStrategy;

    public TableLogger(TableFormatter tableFormatter, OutputStrategy outputStrategy) {
        this.tableFormatter = tableFormatter;
        this.outputStrategy = outputStrategy;

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

        rows.add(new TableRow(values));
    }

    public void print() {
        outputStrategy.print(tableFormatter.writeTable(columns(), rows()));
    }

    private String[] columns() {
        String[] columnsArray = new String[columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
            columnsArray[i] = columnNames.get(i);
        }
        return columnsArray;
    }

    private TableRow[] rows() {
        TableRow[] rowsArray = new TableRow[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            rowsArray[i] = rows.get(i);
        }
        return rowsArray;
    }
}
