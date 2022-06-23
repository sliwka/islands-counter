package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class IslandsCounterTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testSmallCases(String mapAsString, int expectedIslandsCount) {

        // given
        byte[][] map = readMapFromString(mapAsString);

        // when
        var islandsCounter = new IslandsCounter(map);
        int islandsCount = islandsCounter.countIslands();

        // then
        Assertions.assertEquals(expectedIslandsCount, islandsCount);
    }

    private byte[][] readMapFromString(String mapAsString) {
        var lines = mapAsString.trim().split("\\r?\\n|\\r");
        if (lines.length == 0) {
            return new byte[0][0];
        }
        byte[][] map = new byte[lines.length][];
        for (int y = 0; y < lines.length; y++) {
            var fields = lines[y].trim().split("\\s+");
            map[y] = new byte[fields.length];
            for (int x = 0; x < fields.length; x++) {
                map[y][x] = (byte) (fields[x].equals("1") ? 1 : 0);
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
                        0 0 0 0 0 0 0
                        0 0 0 0 0 0 0
                        0 0 0 0 0 0 0
                        0 0 0 0 0 0 0
                        """, 0),
                Arguments.of("""
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        1 1 1 1 1 1 1 1 1
                        """, 1),
                Arguments.of("0 ".repeat(100000), 0),
                Arguments.of("1 ".repeat(100000), 1),
                Arguments.of("0 \n".repeat(100000), 0),
                Arguments.of("1 \n".repeat(100000), 1),
                Arguments.of("1 0 ".repeat(50000), 50000),
                Arguments.of(("1 ".repeat(200) + "0 ").repeat(200), 200),
                Arguments.of(("0 ".repeat(200) + "1 ").repeat(200), 200),
                // snake, eg:
                //                        1 1 1 0 1 1 1 0 1
                //                        1 0 1 0 1 0 1 0 1
                //                        1 0 1 0 1 0 1 0 1
                //                        1 0 1 1 1 0 1 1 1
                Arguments.of("1 1 1 0 ".repeat(100) + "\n" +
                                ("1 0 1 0 ".repeat(100) + "\n").repeat(100) +
                                "1 0 1 1 ".repeat(100)
                        , 1),
                // chessboard
                Arguments.of(("1 0 ".repeat(100) + "\n" +
                                "0 1 ".repeat(100) + "\n").repeat(50)
                        , 1)
        );
    }
}