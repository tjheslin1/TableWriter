package io.github.tjheslin1.tl;

public class Main {

    public static void main(String[] args) {
        TableLogger tableLogger = new TableLogger(new TableFormatter())
                .withColumn("testColumn1")
                .withColumn("testColumn2");

        tableLogger.addRow("testValue1", "testValue2");

        tableLogger.print();
    }
}
