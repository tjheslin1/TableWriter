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

import static io.github.tjheslin1.tablewriter.FormattingUtils.paddingOfCharacterOfLength;
import static io.github.tjheslin1.tablewriter.FormattingUtils.spaceBeforeAndAfter;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * Converts a list of {@link TableRow} into a formatted table.
 */
public class TableFormatter {

    private static final String LEFT_BORDER_STYLING = "+-";
    private static final String MIDDLE_BORDER_STYLING = "-+-";
    private static final String RIGHT_BORDER_STYLING = "-+";

    private static final char HORIZONTAL_STYLING = '-';
    private static final String VERTICAL_STYLING = "|";
    private static final String CENTRE_STYLING = "|";

    private final ColumnWidthCalculator columnWidthCalculator;

    public TableFormatter() {
        this.columnWidthCalculator = new ColumnWidthCalculator();
    }

    /**
     * @param columnNames The names of the columns headers.
     * @param rows        The content of each row, the length of each row is to match the number of columns.
     * @return The formatted table as a string.
     */
    public String formatTable(String[] columnNames, TableRow[] rows) {
        int[] columnWidths = columnWidthCalculator.columnWidths(columnNames, rows);

        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append(dashedLine(columnWidths));
        tableBuilder.append(columnHeaderLine(columnNames, columnWidths));
        tableBuilder.append(dashedLine(columnWidths));

        for (TableRow row : rows) {
            tableBuilder.append(row.line(columnWidths, VERTICAL_STYLING, CENTRE_STYLING, VERTICAL_STYLING));
        }
        tableBuilder.append(dashedLine(columnWidths));

        return tableBuilder.toString();
    }

    private String columnHeaderLine(String[] columnNames, int[] columnWidths) {
        return range(0, columnNames.length)
                .mapToObj(index -> columnHeader(index, columnNames, columnWidths))
                .collect(joining(CENTRE_STYLING, VERTICAL_STYLING, VERTICAL_STYLING + lineSeparator()));
    }

    private String columnHeader(int index, String[] columnNames, int[] columnWidths) {
        int spaceLeftInColumn = columnWidths[index] - columnNames[index].length();
        return spaceBeforeAndAfter(columnNames[index] + paddingOfCharacterOfLength(' ', spaceLeftInColumn));
    }

    private String dashedLine(int[] columnWidths) {
        return stream(columnWidths)
                .mapToObj(columnWidth -> paddingOfCharacterOfLength(HORIZONTAL_STYLING, columnWidth))
                .collect(joining(MIDDLE_BORDER_STYLING, LEFT_BORDER_STYLING, RIGHT_BORDER_STYLING + lineSeparator()));
    }
}
