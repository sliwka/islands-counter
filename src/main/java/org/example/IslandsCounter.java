package org.example;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class IslandsCounter {

    private final byte[][] disappearingMap;
    private final int width;
    private final int height;

    public IslandsCounter(byte[][] map) {
        disappearingMap = new byte[map.length][];
        if (map.length == 0) {
            width = 0;
            height = 0;
        } else {
            width = map[0].length;
            height = map.length;
            for (int i = 0; i < map.length; i++) {
                disappearingMap[i] = Arrays.copyOf(map[i], map[i].length);
            }
        }
    }

    public int countIslands() {
        int islandsCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (disappearingMap[y][x] == 1) {
                    islandsCount++;
                    eatAllIslandFieldsFromTheMapStartingFrom(new Position(x, y));
                }
            }
        }
        return islandsCount;
    }

    private void eatAllIslandFieldsFromTheMapStartingFrom(Position initialPosition) {
        Queue<Position> queue = new ArrayDeque<>();
        tryToEatAndProgress(initialPosition, queue);
        while (!queue.isEmpty()) {
            var position = queue.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    tryToEatAndProgress(position.move(dx, dy), queue);
                }
            }
        }
    }

    private void tryToEatAndProgress(Position position, Queue<Position> queue) {
        if (position.isInRectangle(width, height) && disappearingMap[position.getY()][position.getX()] == 1) {
            queue.add(position);
            disappearingMap[position.getY()][position.getX()] = 0;
        }
    }
}
