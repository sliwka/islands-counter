package org.example;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class IslandsCounter {

    private final byte[][] disappearingMap;
    private final int width;
    private final int height;

    public IslandsCounter(byte[][] disappearingMap) {
        this.disappearingMap = new byte[disappearingMap.length][];
        if (disappearingMap.length == 0) {
            this.width = 0;
            this.height = 0;
        } else {
            this.width = disappearingMap[0].length;
            this.height = disappearingMap.length;
            for (int i = 0; i < disappearingMap.length; i++) {
                this.disappearingMap[i] = Arrays.copyOf(disappearingMap[i], disappearingMap[i].length);
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
        tryVisitAndEat(initialPosition, queue);
        while (!queue.isEmpty()) {
            var position = queue.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    tryVisitAndEat(position.move(dx, dy), queue);
                }
            }
        }
    }

    private void tryVisitAndEat(Position position, Queue<Position> queue) {
        if (position.isInRectangle(width, height) && disappearingMap[position.getY()][position.getX()] == 1) {
            queue.add(position);
            disappearingMap[position.getY()][position.getX()] = 0;
        }
    }
}
