# Table Logger

Fluent api for outputting a table of stats

```
    TableLogger tableLogger = new TableLogger(new TableFormatter(columnWidthCalculator), table -> System.out.println(table))
            .withColumn("Name")
            .withColumn("Role")
            .withColumn("Working Days");
    
    tableLogger.addRow("Tom", "Developer", "5");
    tableLogger.addRow("Paul", "Tester", "4");
    tableLogger.addRow("Karen", "Support", "6");
    
    tableLogger.print();
```

Output:

```
    --------------------------------
    Name  | Role      | Working Days
    --------------------------------
    Tom   | Developer | 5
    Paul  | Tester    | 4
    Karen | Support   | 6
    --------------------------------
```

The implementation of the interface `OutputStrategy` allows you to choose how this table gets outputted.
The example here simply prints it to std out.