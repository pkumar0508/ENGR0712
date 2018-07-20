package app.pkumar0508.engr0712.lab1;

import java.util.Random;

/** Mutable lattice represented by a boolean matrix. */
public class Grid {
  private boolean[][] grid;
  private final int size;

  // constructor
  public Grid(int n) {
    size = n;
    clear();
  }

  public boolean get(int x, int y) {
    return grid[x][y];
  }

  public int size() {
    return size;
  }

  // mutators
  public void clear() {
    grid = new boolean[size][size];
  }

  public void set(int x, int y, boolean value) {
    grid[x][y] = value;
  }

  public void populate(double p) {
    Random r = new Random();
    int n = (int) (size * size * p);
    int i = 0;
    int x, y;

    while (i < n) {
      x = r.nextInt(size);
      y = r.nextInt(size);
      if (!grid[x][y]) {
        grid[x][y] = true;
        i++;
      }
    }
  }

  // tester method
  public boolean hasPath() {
    Grid c = new Grid(size);
    return hasPath(0, c);
  }

  private boolean hasPath(int x, Grid c) {
    if (x == size - 1) {
      return hasPath(x, 0, c);
    }
    return hasPath(x, 0, c) || hasPath(x + 1, c);
  }

  private boolean hasPath(int x, int y, Grid c) {
    if (x < 0 || y < 0 || y >= size || x >= size) {
      return false;
    }
    if (c.get(x, y)) {
      return false;
    }
    if (!get(x, y)) {
      return false;
    }
    if (y == size - 1) {
      return true;
    }

    c.set(x, y, true);

    return hasPath(x - 1, y, c)
        || hasPath(x + 1, y, c)
        || hasPath(x, y + 1, c)
        || hasPath(x, y - 1, c);
  }
}
