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
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * A representation of a the data in a row in the table.
 */
public class TableRow {

    private final String[] data;

    public TableRow(String... data) {
        this.data = data;
    }

    /**
     * String representation of the row of data.
     *
     * @param columnWidths The calculated widths of the columns of the table.
     * @param leftPadding The left padding of the table.
     * @param middlePadding The padding in between each cell in a row.
     * @param rightPadding The right padding of the table.
     * @return The row of data as a line complete with whitespace padding and separators.
     */
    public String line(int[] columnWidths, String leftPadding, String middlePadding, String rightPadding) {
        return range(0, columnWidths.length)
                .mapToObj(index -> paddedData(index, columnWidths))
                .collect(joining(middlePadding, leftPadding, rightPadding + lineSeparator()));
    }

    /**
     *
     * @param index the 0 based index of the column of the data in the row.
     * @return The length of the table cell, in characters.
     */
    public int lengthOfFieldAtIndex(int index) {
        return data[index].length();
    }

    private String paddedData(int index, int[] columnWidths) {
        int spaceLeftInColumn = columnWidths[index] - data[index].length();
        return data[index] + restOfCellAsSpaces(spaceLeftInColumn);
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn]).replace('\0', ' ');
    }
}
