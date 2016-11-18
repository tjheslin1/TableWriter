/*
 * Copyright 2016 Thomas Heslin <tjheslin1@gmail.com>.
 *
 * This file is part of TableWriter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.tjheslin1.tablewriter;

import static java.lang.String.format;

/**
 * Useful methods for formatting lines to conform a table structure.
 */
public class FormattingUtils {

    /**
     * @param line The line to be padded with spaces
     * @return The line passed in with a space before and after.
     */
    public static String spaceBeforeAndAfter(String line) {
        return format(" %s ", line);
    }

    /**
     * @param padding The character to be repeated to populate the String.
     * @param length  The number of times to repeat the padding character.
     * @return A String of length provided, consisting of the padding character
     * repeated until the length is reached.
     */
    public static String paddingOfCharacterOfLength(char padding, int length) {
        return new String(new char[length]).replace('\0', padding);
    }
}
