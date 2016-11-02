/*
 * Copyright 2016 Thomas Heslin <tjheslin1@gmail.com>.
 *
 * This file is part of TableWriter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.tjheslin1.tablewriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Fluent API for constructing a table of stats.
 */
public class TableWriter {

    private final List<String> columnNames;
    private final List<TableRow> rows;

    private final TableFormatter tableFormatter;
    private final OutputStrategy outputStrategy;

    public TableWriter(TableFormatter tableFormatter, OutputStrategy outputStrategy) {
        this.tableFormatter = tableFormatter;
        this.outputStrategy = outputStrategy;

        this.columnNames = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    /**
     * Appends an extra column to the table.
     * The columns appear in the order this method is called.
     *
     * @param columnName The name of the column.
     * @return The current state of the {@link TableWriter} including the new column.
     */
    public TableWriter withColumn(String columnName) {
        columnNames.add(columnName);
        return this;
    }

    /**
     * Appends a row to the table.
     * The rows appear in the order this method is called.
     * Throws an IllegalStateException if the number of values doesn't match the number of columns.
     *
     * @param values The values of the row. The number of values must match the number of columns.
     */
    public void addRow(String... values) {
        if (values.length != columnNames.size()) {
            throw new IllegalStateException(
                    format("Attempting to log data with '%s' values into '%s' columns", values.length, columnNames.size()));
        }

        rows.add(new TableRow(values));
    }

    /**
     * Return the table a string, alternative to using an {@link OutputStrategy}.
     *
     * @return The formatted table as a string.
     */
    public String tableAsString() {
        return tableFormatter.formatTable(columns(), rows());
    }

    /**
     * Prints the formatted table by invoking the provided {@link OutputStrategy}.
     */
    public void print() {
        outputStrategy.print(tableAsString());
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
