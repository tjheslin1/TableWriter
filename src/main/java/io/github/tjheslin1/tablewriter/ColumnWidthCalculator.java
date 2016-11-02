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

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

/**
 * Handles the calculation of the width of the columns of the table. Based on the content inside it.
 */
public class ColumnWidthCalculator {

    /**
     * Returns the necessary widths of the columns to be created. Based on the widest value from the given rows.
     *
     * @param columnNames The list of column header names.
     * @param rows The rows of data, the size of which is to match the number of columns.
     * @return The widths, in characters, of the columns to be printed as part of the table.
     */
    public int[] columnWidths(String[] columnNames, TableRow[] rows) {
        return range(0, columnNames.length)
                .map(index -> widthOfColumnInChars(index, columnNames, rows))
                .toArray();
    }

    private int widthOfColumnInChars(int columnIndex, String[] columnNames, TableRow[] rows) {
        int columnNameWidth = columnNames[columnIndex].length();
        int columnDataWidth = columnDataWidth(columnIndex, rows);
        return Math.max(columnNameWidth, columnDataWidth);
    }

    private int columnDataWidth(int columnIndex, TableRow[] rows) {
        return stream(rows)
                .mapToInt(tableRow -> tableRow.lengthOfFieldAtIndex(columnIndex))
                .max().orElse(0);
    }
}
