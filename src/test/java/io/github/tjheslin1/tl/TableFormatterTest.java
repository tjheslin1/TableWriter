package io.github.tjheslin1.tl;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class TableFormatterTest implements WithAssertions {

    private final TableFormatter tableFormatter = new TableFormatter();

    @Test
    public void calculatesCharacterWidth() throws Exception {
        String format = tableFormatter.format(asList("testColumn1", "testColumn2"), singletonList(new TableRow(asList("field1", "field2"))));

        assertThat(format).isEqualTo(
                "------------------------------\n" +
                "testColumn1		testColumn2\n" +
                "------------------------------\n" +
                "field1		field2\n" +
                "------------------------------");
    }
}