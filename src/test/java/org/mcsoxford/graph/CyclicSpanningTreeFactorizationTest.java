package org.mcsoxford.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class CyclicSpanningTreeFactorizationTest {

  CyclicSpanningTreeFactorization a = new CyclicSpanningTreeFactorization();

  @Test
  public void n4() {
    // setup
    final int n = 4;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(1, t[0]);
    assertEquals(3, t[1]);
    assertEquals(2, t[3]);
    assertEquals(2, t[2]);
  }

  @Test
  public void n6() {
    // setup
    final int n = 6;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(2, t[0]);
    assertEquals(1, t[2]);
    assertEquals(4, t[1]);
    assertEquals(5, t[4]);
    assertEquals(3, t[5]);
    assertEquals(3, t[3]);
  }

  @Test
  public void n8() {
    // setup
    final int n = 8;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(3, t[0]);
    assertEquals(1, t[3]);
    assertEquals(2, t[1]);
    assertEquals(6, t[2]);
    assertEquals(5, t[6]);
    assertEquals(7, t[5]);
    assertEquals(4, t[7]);
    assertEquals(4, t[4]);
  }

  @Test
  public void n10() {
    // setup
    final int n = 10;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(4, t[0]);
    assertEquals(1, t[4]);
    assertEquals(3, t[1]);
    assertEquals(2, t[3]);
    assertEquals(7, t[2]);
    assertEquals(8, t[7]);
    assertEquals(6, t[8]);
    assertEquals(9, t[6]);
    assertEquals(5, t[9]);
    assertEquals(5, t[5]);
  }

  @Test
  public void n12() {
    // setup
    final int n = 12;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(5, t[0]);
    assertEquals(1, t[5]);
    assertEquals(4, t[1]);
    assertEquals(2, t[4]);
    assertEquals(3, t[2]);
    assertEquals(9, t[3]);
    assertEquals(8, t[9]);
    assertEquals(10, t[8]);
    assertEquals(7, t[10]);
    assertEquals(11, t[7]);
    assertEquals(6, t[11]);
    assertEquals(6, t[6]);
  }

  @Test
  public void n14() {
    // setup
    final int n = 14;
    final int[] t = init(n);

    // execute
    a.spanning(t, n);

    // verify
    assertEquals(6, t[0]);
    assertEquals(1, t[6]);
    assertEquals(5, t[1]);
    assertEquals(2, t[5]);
    assertEquals(4, t[2]);
    assertEquals(3, t[4]);
    assertEquals(10, t[3]);
    assertEquals(11, t[10]);
    assertEquals(9, t[11]);
    assertEquals(12, t[9]);
    assertEquals(8, t[12]);
    assertEquals(13, t[8]);
    assertEquals(7, t[13]);
    assertEquals(7, t[7]);
  }

  @Test
  public void cn4() {
    cn(4);
  }

  @Test
  public void cn6() {
    cn(6);
  }

  @Test
  public void cn8() {
    cn(8);
  }

  @Test
  public void cn10() {
    cn(10);
  }

  @Test
  public void cn12() {
    cn(12);
  }

  @Test
  public void cn14() {
    cn(14);
  }

  /* Tests that the decomposition yields a complete graph of order n */
  private void cn(int n) {
    final int[][] t = a.decompose(n);
    verifyGraph(n, t);
    verifyTrails(n, t);
  }

  /* Initializes a graph where each vertex is isolated */
  private int[] init(int n) {
    final int[] t = new int[n];
    for (int i = 0; i < n; i++) {
      t[i] = i;
    }
    return t;
  }

  /* Verifies the n-complete graph by checking the adjacency matrix */
  private void verifyGraph(int n, int[][] t) {
    final int[][] m = new int[n][n];
    int v;
    for (int i = 0; i < t.length; i++) {
      for (int j = 0; j < t[i].length; j++) {
        v = t[i][j];
        if (v != j) {
          m[j][v] += 1;
          m[v][j] += 1;
        }
      }
    }

    for (int i = 0; i < t.length; i++) {
      for (int j = 0; j < t[i].length; j++) {
        if (i == j) {
          assertEquals(0, m[i][j]);
        } else {
          assertEquals(1, m[i][j]);
        }
      }
    }

  }

  /* Verifies that the spanning trees are Eulerian */
  private void verifyTrails(int n, int[][] t) {
    int v, k;
    for (int i = 0; i < t.length; i++) {
      v = i;
      k = 0;
      do {
        v = t[i][v];
        k++;
      } while (v != t[i][v]);
      assertEquals(n - 1, k);
    }
  }
}
