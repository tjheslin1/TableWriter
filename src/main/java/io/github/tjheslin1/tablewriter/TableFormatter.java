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

import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * Converts a list of {@link TableRow} into a formatted table.
 */
public class TableFormatter {

    private static final char DASH = '-';

    private static final String LEFT_HEADER_PADDING = "+-";
    private static final String MIDDLE_HEADER_PADDING = "-+-";
    private static final String RIGHT_HEADER_PADDING = "-+";

    private static final String LEFT_PADDING = "| ";
    private static final String MIDDLE_PADDING = " | ";
    private static final String RIGHT_PADDING = " |";

    private final ColumnWidthCalculator columnWidthCalculator;

    public TableFormatter() {
        this.columnWidthCalculator = new ColumnWidthCalculator();
    }

    /**
     *
     * @param columnNames The names of the columns headers.
     * @param rows The content of each row, the length of each row is to match the number of columns.
     * @return The formatted table as a string.
     */
    public String formatTable(String[] columnNames, TableRow[] rows) {
        int[] columnWidths = columnWidthCalculator.columnWidths(columnNames, rows);

        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append(dashedLine(columnWidths));
        tableBuilder.append(columnHeaderLine(columnNames, columnWidths));
        tableBuilder.append(dashedLine(columnWidths));

        for (TableRow row : rows) {
            tableBuilder.append(row.line(columnWidths, LEFT_PADDING, MIDDLE_PADDING, RIGHT_PADDING));
        }
        tableBuilder.append(dashedLine(columnWidths));

        return tableBuilder.toString();
    }

    private String columnHeaderLine(String[] columnNames, int[] columnWidths) {
        return range(0, columnNames.length)
                .mapToObj(index -> columnHeader(index, columnNames, columnWidths))
                .collect(joining(MIDDLE_PADDING, LEFT_PADDING, RIGHT_PADDING + lineSeparator()));
    }

    private String columnHeader(int index, String[] columnNames, int[] columnWidths) {
        int spaceLeftInColumn = MIDDLE_PADDING.length() + columnWidths[index] - columnNames[index].length();
        return columnNames[index] + restOfCellAsSpaces(spaceLeftInColumn);
    }

    private String dashedLine(int[] columnWidths) {
        return stream(columnWidths)
                .mapToObj(this::dashedLineOfSize)
                .collect(joining(MIDDLE_HEADER_PADDING, LEFT_HEADER_PADDING, RIGHT_HEADER_PADDING + lineSeparator()));
    }

    private String dashedLineOfSize(int width) {
        return new String(new char[width]).replace('\0', DASH);
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn - 3]).replace('\0', ' ');
    }
}
