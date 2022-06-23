package org.example;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInRectangle(int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }
}
