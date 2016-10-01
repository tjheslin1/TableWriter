package io.github.tjheslin1.tl;

public class Main {

    private static ColumnWitdthCalculator columnWitdthCalculator = new ColumnWitdthCalculator();

    public static void main(String[] args) {
        TableLogger tableLogger = new TableLogger(new TableFormatter(columnWitdthCalculator), table -> System.out.println(table))
                .withColumn("Name")
                .withColumn("Role")
                .withColumn("Working Days");

        tableLogger.addRow("Tom", "Developer", "5");
        tableLogger.addRow("Paul", "Tester", "4");
        tableLogger.addRow("Karen", "Support", "6");

        tableLogger.print();
    }
}
