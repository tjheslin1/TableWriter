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

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * Handles the appending of rows to a {@link TableWriter} in a fluent api fashion.
 */
public class RowAppender {

    private final TableWriter tableWriter;
    private final boolean enforceRowLengths;

    public RowAppender(TableWriter tableWriter, boolean enforceRowLengths) {
        this.tableWriter = tableWriter;
        this.enforceRowLengths = enforceRowLengths;
    }

    /**
     * Appends a row to the table.
     * The rows appear in the order this method is called.
     * 'enforceRowsLength' checks that the number of cells in a row provided match the number of columns.
     * <p>
     * Throws an IllegalStateException if the number of values doesn't match the number of columns when this check is enabled.
     *
     * @param values The values of the row.
     */
    public RowAppender row(String... values) {
        if (values.length > tableWriter.columnCount()
                || (enforceRowLengths && values.length < tableWriter.columnCount())) {
            throw new IllegalStateException(
                    format("Attempting to log data with '%s' values into '%s' columns", values.length, tableWriter.columnCount()));
        }

        tableWriter.addRow(padCells(new TableRow(values)));
        return this;
    }

    /**
     * Prints the formatted table by invoking the provided {@link OutputStrategy}.
     */
    public void printTable() {
        tableWriter.print();
    }

    private TableRow padCells(TableRow tableRow) {
        if (tableRow.cellCount() < tableWriter.columnCount()) {
            String[] cellPadding = new String[tableWriter.columnCount() - tableRow.cellCount()];
            Arrays.fill(cellPadding, "");
            String[] cells = Stream.of(tableRow.data, cellPadding)
                    .flatMap(Stream::of)
                    .toArray(String[]::new);
            tableRow = new TableRow(cells);
        }
        return tableRow;
    }
}
