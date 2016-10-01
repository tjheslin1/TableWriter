package io.github.tjheslin1.tl;

import java.util.stream.IntStream;

public class TableFormatter {

    private final static String DASH = "-";
    public static final String PADDING = " | ";

    private final ColumnWitdthCalculator columnWitdthCalculator;

    public TableFormatter(ColumnWitdthCalculator columnWitdthCalculator) {
        this.columnWitdthCalculator = columnWitdthCalculator;
    }

    public String format(String[] columnNames, TableRow[] rows) {
        int[] columnWidths = columnWitdthCalculator.indexes(columnNames, rows);
        int tableCharacterWidth = tableCharacterWidth(columnWidths);

        StringBuilder stringBuilder = new StringBuilder();
        printDashedLine(tableCharacterWidth, stringBuilder);

        stringBuilder.append(columnLine(columnNames, columnWidths));
        stringBuilder.append(System.lineSeparator());

        printDashedLine(tableCharacterWidth, stringBuilder);

        for (TableRow row : rows) {
            stringBuilder.append(row.line(columnWidths, PADDING));
        }

        stringBuilder.append(dashedLineOfSize(tableCharacterWidth));
        return stringBuilder.toString();
    }

    private String columnLine(String[] columnNames, int[] columnWidths) {
        StringBuilder columnLineBuilder = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            int spaceLeftInColumn = columnWidths[i] - columnNames[i].length();
            boolean lastColumn = i == columnNames.length - 1;
            if (lastColumn) {
                columnLineBuilder.append(columnNames[i]);
            } else {
                columnLineBuilder.append(columnNames[i] + restOfCellAsSpaces(spaceLeftInColumn));
            }
        }
        return columnLineBuilder.toString();
    }

    private StringBuilder printDashedLine(int tableCharacterWidth, StringBuilder stringBuilder) {
        return stringBuilder.append(dashedLineOfSize(tableCharacterWidth) + System.lineSeparator());
    }

    private String dashedLineOfSize(int tableCharacterWidth) {
        return new String(new char[tableCharacterWidth]).replace("\0", DASH);
    }

    private int tableCharacterWidth(int[] columnWidths) {
        return IntStream.of(columnWidths).reduce(0, (a, b) -> a + b);
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn - 3]).replace("\0", " ") + PADDING;
    }
}
