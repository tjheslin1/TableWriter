# Table Logger

Fluent api for outputting a table of stats

```java
new TableWriter(table -> System.out.println(table))
            .withColumn("Name").withColumn("Role").withColumn("Working Days")
        .withRows(DONT_ENFORCE_ROW_LENGTHS)
            .row("Tom", "Developer", "5")
            .row("Paul", "Tester", "4")
            .row("Karen", "Support")
        .printTable();
```

Output:

```
+-------+-----------+--------------+
| Name  | Role      | Working Days |
+-------+-----------+--------------+
| Tom   | Developer | 5            |
| Paul  | Tester    | 4            |
| Karen | Support   |              |
+-------+-----------+--------------+
```

The implementation of the interface [OutputStrategy](src/main/java/io/github/tjheslin1/tablewriter/OutputStrategy.java) allows you to choose how this table gets outputted.
The example here simply prints it to std out.

##How do I get it?

###Maven
```xml
<dependency>
    <groupId>io.github.tjheslin1</groupId>
    <artifactId>TableWriter</artifactId>
    <version>1.3</version>
</dependency>
```
###Gradle
```groovy
compile 'io.github.tjheslin1:TableWriter:1.3'
```
###SBT
```scala
libraryDependencies += "io.github.tjheslin1" % "TableWriter" % "1.3"
```
