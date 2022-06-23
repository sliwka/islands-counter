package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class IslandsCounterTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testSmallCases(String mapAsString, int expectedIslandsCount) {

        // given
        byte[][] map = readMapFromString(mapAsString);

        // when
        var islandsCounter = new IslandsCounter(map);
        int islandsCount = islandsCounter.count();

        // then
        Assertions.assertEquals(expectedIslandsCount, islandsCount);
    }

    private byte[][] readMapFromString(String mapAsString) {
        var lines = mapAsString.trim().split("\\r?\\n|\\r");
        if (lines.length == 0) {
            return new byte[0][0];
        }
        byte[][] map = new byte[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            var fields = lines[i].trim().split("\\s+");
            map[i] = new byte[fields.length];
            for (int j = 0; j < fields.length; j++) {
                map[i][j] = (byte) (fields[j].equals("1") ? 1 : 0);
            }
        }
        return map;
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("1", 1),
                Arguments.of("0", 0),
                Arguments.of("""
                        0 0 0 0 0 0 0 0 0
                        0 1 0 0 0 0 0 0 0
                        1 1 1 0 0 0 1 0 0
                        1 1 0 0 0 1 1 1 0
                        0 0 0 0 0 1 1 0 0
                        0 0 1 0 0 0 0 0 0
                        1 1 0 0 0 0 0 0 0
                        0 0 0 0 0 1 1 0 0
                        """, 4),
                Arguments.of("""
                        0 0 0 1 0 0 0 0 0
                        0 0 1 0 1 0 0 0 0
                        0 1 0 0 0 1 0 0 0
                        1 0 0 0 0 0 1 0 0
                        0 0 0 0 0 1 0 0 0
                        """, 1),
                Arguments.of("""
                        0 0 0 0 0 0 0 0
                        0 1 0 0 0 0 0 0
                        1 1 1 0 0 0 1 0
                        1 1 0 0 0 1 1 1
                        0 0 0 0 0 1 1 0
                        0 0 1 0 0 0 0 0
                        1 1 0 0 0 0 0 0
                        0 0 0 0 0 1 1 0
                        """, 4),
                Arguments.of("""
                        0 0 0 0 0 0 0 0 0
                        0 0 0 0 0 0 0 0 0
                        0 0 0 0 0 0 0 0 0
                        0 0 0 0 0 0 0 0 0
                        """, 0),
                Arguments.of("""
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        """, 1),
                Arguments.of("0 ".repeat(1000000), 0),
                Arguments.of("1 ".repeat(1000000), 1),
                Arguments.of("1 0 ".repeat(1000000), 1000000),
                Arguments.of(("1 ".repeat(1000) + "0 ").repeat(1000), 1000),
                Arguments.of(("0 ".repeat(1000) + "1 ").repeat(1000), 1000)
        );
    }
}