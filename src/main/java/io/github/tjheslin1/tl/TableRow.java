package io.github.tjheslin1.tl;

import java.util.List;

public class TableRow {

    private final List<String> data;

    public TableRow(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.stream().reduce("", this::concat) + System.lineSeparator();
    }

    // TODO duplicate of TableFormatter
    private String concat(String current, String toBeAppended) {
        if(current.isEmpty()) {
            return current + toBeAppended;
        } else {
            return current + "\t\t" + toBeAppended;
        }
    }
}
