package io.github.tjheslin1.tl;

public class TableRow {

    private final String[] data;

    public TableRow(String... data) {
        this.data = data;
    }

    public int lengthOfFieldAtIndex(int index) {
        return data[index].length();
    }

    public String line(int[] columnIndexes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int spaceLeftInColumn = columnIndexes[i] - data[i].length();
            boolean lastColumn = i == data.length - 1;
            if (lastColumn) {
                stringBuilder.append(data[i]);
            } else {
                stringBuilder.append(data[i] + restOfCellAsSpaces(spaceLeftInColumn));
            }
        }
        return stringBuilder.toString() + System.lineSeparator();
    }

    private String restOfCellAsSpaces(int spaceLeftInColumn) {
        return new String(new char[spaceLeftInColumn - 3]).replace("\0", " ") + " | ";
    }
}
