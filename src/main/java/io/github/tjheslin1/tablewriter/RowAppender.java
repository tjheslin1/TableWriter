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

import static java.lang.String.format;

/**
 * Handles the appending of rows to a {@link TableWriter} in a fluent api fashion.
 */
public class RowAppender {

    private final TableWriter tableWriter;

    public RowAppender(TableWriter tableWriter) {
        this.tableWriter = tableWriter;
    }

    /**
     * Appends a row to the table.
     * The rows appear in the order this method is called.
     * Throws an IllegalStateException if the number of values doesn't match the number of columns.
     *
     * @param values The values of the row. The number of values must match the number of columns.
     */
    public RowAppender row(String... values) {
        if (values.length != tableWriter.columnCount()) {
            throw new IllegalStateException(
                    format("Attempting to log data with '%s' values into '%s' columns", values.length, tableWriter.columnCount()));
        }

        tableWriter.addRow(new TableRow(values));
        return this;
    }

    /**
     * Prints the formatted table by invoking the provided {@link OutputStrategy}.
     */
    public void printTable() {
        tableWriter.print();
    }
}
