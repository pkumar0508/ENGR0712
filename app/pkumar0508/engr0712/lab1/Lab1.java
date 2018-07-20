package app.pkumar0508.engr0712.lab1;

/** Main entry point for Lab1. Prints to standard output only. */
public class Lab1 {
  public static void main(String[] args) {
    int size = 200;
    double p = .60;
    double delta = .005;
    double cutoff = .9;
    int n = 100;

    Grid g = new Grid(size);
    boolean q = false; // found percolation constant
    int i;
    int count;

    while (!q) {
      count = 0;
      i = 0;
      System.out.println("*** Attempting p = " + p + " ***");
      while (i < n) {
        System.out.print(".");
        g.clear();
        g.populate(p);
        if (g.hasPath()) {
          count++;
        }
        i++;
      }
      System.out.println();

      if (count / n > cutoff) {
        q = true;
      } else {
        p += delta;
      }
    }

    System.out.println("\n\n\nPc Found!  Pc = " + p);
  }
}
