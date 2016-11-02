# Table Logger

Fluent api for outputting a table of stats

```
TableWriter tableWriter = new TableWriter(new TableFormatter(columnWidthCalculator), table -> System.out.println(table))
        .withColumn("Name")
        .withColumn("Role")
        .withColumn("Working Days");

tableWriter.addRow("Tom", "Developer", "5");
tableWriter.addRow("Paul", "Tester", "4");
tableWriter.addRow("Karen", "Support", "6");

tableWriter.print();
```

Output:

```
+-------+-----------+--------------+
| Name  | Role      | Working Days |
+-------+-----------+--------------+
| Tom   | Developer | 5            |
| Paul  | Tester    | 4            |
| Karen | Support   | 6            |
+-------+-----------+--------------+
```

The implementation of the interface [OutputStrategy](src/main/java/io/github/tjheslin1/tablewriter/OutputStrategy.java) allows you to choose how this table gets outputted.
The example here simply prints it to std out.