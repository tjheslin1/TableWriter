package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class ColumnWidthCalculatorTest implements WithAssertions {

    private final ColumnWidthCalculator columnWidthCalculator = new ColumnWidthCalculator();

    @Test
    public void calculatesColumnIndexes() throws Exception {
        String[] columnNames = {"short", "longColumnName"};

        TableRow[] rows = {new TableRow("val1", "val2"),
                new TableRow("reallyReallyReallyLongCell", "val4")};

        int[] indexes = columnWidthCalculator.indexes(columnNames, rows);

        assertThat(indexes).isEqualTo(new int[]{29, 14});
    }
}