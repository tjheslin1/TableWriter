package io.github.tjheslin1.tl;

public class ColumnWitdthCalculator {

    public int[] indexes(String[] columnNames, TableRow[] rows) {
        int[] columnIndexes = new int[columnNames.length];

        for (int column = 0; column < columnNames.length; column++) {
            boolean lastColumn = column == columnNames.length - 1;
            int columnWidth = widthOfColumnInChars(column, lastColumn, columnNames, rows);
            columnIndexes[column] = columnWidth;
        }

        return columnIndexes;
    }

    private int widthOfColumnInChars(int columnIndex, boolean lastColumn, String[] columnNames, TableRow[] rows) {
        int columnWidth = columnNames[columnIndex].length();
        for (TableRow row : rows) {
            int lengthOfField = row.lengthOfFieldAtIndex(columnIndex);
            if (lengthOfField > columnWidth) {
                columnWidth = lengthOfField;
            }
        }
        return lastColumn ? columnWidth : columnWidth + TableFormatter.PADDING.length();
    }
}
