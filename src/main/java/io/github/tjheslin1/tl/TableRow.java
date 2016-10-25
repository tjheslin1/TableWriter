package io.github.tjheslin1.tl;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class TableRow {

    private final String[] data;

    public TableRow(String... data) {
        this.data = data;
    }

    public int lengthOfFieldAtIndex(int index) {
        return data[index].length();
    }

    public String line(int[] columnWidths, String leftPadding, String middlePadding, String rightPadding) {
        return range(0, columnWidths.length)
                .mapToObj(index -> paddedData(index, columnWidths))
                .collect(joining(middlePadding, leftPadding, rightPadding + lineSeparator()));
    }

    private String paddedData(int index, int[] columnWidths) {
        int spaceLeftInColumn = columnWidths[index] - data[index].length();
        return data[index] + restOfCellAsSpaces(spaceLeftInColumn);
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn]).replace('\0', ' ');
    }
}
