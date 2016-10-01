package io.github.tjheslin1.tl;

import java.util.List;

public class TableFormatter {

    private final static String DASH = "-";
    private static final int TAB_SIZE = 4;

    public String format(List<String> columnNames, List<TableRow> rows) {
        int tableCharacterWidth = tableCharacterWidth(columnNames);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dashedLineOfSize(tableCharacterWidth) + System.lineSeparator());

        String columnLine = columnNames.stream().reduce("", this::concat);
        stringBuilder.append(columnLine + System.lineSeparator());

        stringBuilder.append(dashedLineOfSize(tableCharacterWidth) + System.lineSeparator());

        rows.stream().forEach(stringBuilder::append);

        stringBuilder.append(dashedLineOfSize(tableCharacterWidth));
        return stringBuilder.toString();
    }

    // TODO duplicate of TableRow
    private String concat(String current, String toBeAppended) {
        if(current.isEmpty()) {
            return current + toBeAppended;
        } else {
            return current + "\t\t" + toBeAppended;
        }
    }

    private String dashedLineOfSize(int tableCharacterWidth) {
        return new String(new char[tableCharacterWidth]).replace("\0", DASH);
    }

    private int tableCharacterWidth(List<String> columnNames) {
        int charactersInColumnNames = columnNames.stream().map(String::length).reduce(0, (a, b) -> a + b);
        return charactersInColumnNames + columnNames.size() * TAB_SIZE;
    }
}
