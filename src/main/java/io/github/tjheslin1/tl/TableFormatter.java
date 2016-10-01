package io.github.tjheslin1.tl;

import java.util.stream.Stream;

public class TableFormatter {

    private final static String DASH = "-";
    public static final String PADDING = " | ";
    public static final int PADDING_LENGTH = PADDING.length();

    private final ColumnWitdthCalculator columnWitdthCalculator;

    public TableFormatter(ColumnWitdthCalculator columnWitdthCalculator) {
        this.columnWitdthCalculator = columnWitdthCalculator;
    }

    public String format(String[] columnNames, TableRow[] rows) {
        int[] columnWidths = columnWitdthCalculator.indexes(columnNames, rows);
        int tableCharacterWidth = tableCharacterWidth(columnNames);

        StringBuilder stringBuilder = new StringBuilder();
        printDashedLine(tableCharacterWidth, stringBuilder);

        for (int i = 0; i < columnNames.length; i++) {
            int spaceLeftInColumn = columnWidths[i] - columnNames[i].length();
            boolean lastColumn = i == columnNames.length - 1;
            if (lastColumn) {
                stringBuilder.append(columnNames[i]);
            } else {
                stringBuilder.append(columnNames[i] + restOfCellAsSpaces(spaceLeftInColumn));
            }
        }
        stringBuilder.append(System.lineSeparator());
        printDashedLine(tableCharacterWidth, stringBuilder);

        for (TableRow row : rows) {
            stringBuilder.append(row.line(columnWidths));
        }

        stringBuilder.append(dashedLineOfSize(tableCharacterWidth));
        return stringBuilder.toString();
    }

    private StringBuilder printDashedLine(int tableCharacterWidth, StringBuilder stringBuilder) {
        return stringBuilder.append(dashedLineOfSize(tableCharacterWidth) + System.lineSeparator());
    }

    private String dashedLineOfSize(int tableCharacterWidth) {
        return new String(new char[tableCharacterWidth]).replace("\0", DASH);
    }

    private int tableCharacterWidth(String[] columnNames) {
        int charactersInColumnNames = Stream.of(columnNames).map(String::length).reduce(0, (a, b) -> a + b);
        return charactersInColumnNames + (columnNames.length * PADDING_LENGTH);
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn - 3]).replace("\0", " ") + " | ";
    }
}
