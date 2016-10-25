package io.github.tjheslin1.tl;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class TableFormatter {

    private static final char DASH = '-';

    private static final String LEFT_HEADER_PADDING = "+-";
    private static final String MIDDLE_HEADER_PADDING = "-+-";
    private static final String RIGHT_HEADER_PADDING = "-+";

    private static final String LEFT_PADDING = "| ";
    private static final String MIDDLE_PADDING = " | ";
    private static final String RIGHT_PADDING = " |";

    private final ColumnWidthCalculator columnWidthCalculator;

    public TableFormatter(ColumnWidthCalculator columnWidthCalculator) {
        this.columnWidthCalculator = columnWidthCalculator;
    }

    public String writeTable(String[] columnNames, TableRow[] rows) {
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
