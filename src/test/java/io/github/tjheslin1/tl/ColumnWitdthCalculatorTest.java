package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class ColumnWitdthCalculatorTest implements WithAssertions {

    private final ColumnWitdthCalculator columnWitdthCalculator = new ColumnWitdthCalculator();

    @Test
    public void calculatesColumnIndexes() throws Exception {
        String[] columnNames = {"short", "longColumnName"};

        TableRow[] rows = {new TableRow("val1", "val2"),
                new TableRow("reallyReallyReallyLongCell", "val4")};

        int[] indexes = columnWitdthCalculator.indexes(columnNames, rows);

        assertThat(indexes).isEqualTo(new int[]{29, 14});
    }
}