package io.github.tjheslin1.tl;

public class Main {

    private static ColumnWitdthCalculator columnWitdthCalculator = new ColumnWitdthCalculator();

    public static void main(String[] args) {
        TableLogger tableLogger = new TableLogger(new TableFormatter(columnWitdthCalculator), System.out::println)
                .withColumn("testColumn4")
                .withColumn("testColumn5")
                .withColumn("testColumn")
                .withColumn("testColumn62")
                .withColumn("testColumn8")
                .withColumn("testCol");

        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1testValue1testValue1testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2testValue2testValue2testValue2", "testValue1", "testValue2");
        tableLogger.addRow("testValue1", "testValue2", "testValue1", "testValue2", "testValue1", "testValue2");

        tableLogger.print();
    }
}
