package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class ColumnWidthCalculatorTest implements WithAssertions {

    private static final int EXPECTED_FIRST_COLUMN_WIDTH = 29;
    private static final int EXPECTED_SECOND_COLUMN_WIDTH = 14;

    private final ColumnWidthCalculator columnWidthCalculator = new ColumnWidthCalculator();

    @Test
    public void calculatesColumnIndexes() throws Exception {
        String[] columnNames = {"short", "longColumnName"};

        TableRow[] rows = {new TableRow("val1", "val2"),
                new TableRow("reallyReallyReallyLongCell", "val4")};

        int[] indexes = columnWidthCalculator.indexes(columnNames, rows);

        assertThat(indexes).isEqualTo(new int[]{EXPECTED_FIRST_COLUMN_WIDTH, EXPECTED_SECOND_COLUMN_WIDTH});
    }
}