package io.github.tjheslin1.tl;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class ColumnWidthCalculator {

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
                .mapToInt(value -> value.lengthOfFieldAtIndex(columnIndex))
                .max().orElse(0);
    }
}
