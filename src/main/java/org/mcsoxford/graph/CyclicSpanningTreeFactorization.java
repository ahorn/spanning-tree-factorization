/*
 * Copyright (C) 2011 A. Horn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mcsoxford.graph;

/**
 * <p>
 * Let <em>G</em> be a simple and finite graph. A <b>decomposition</b> of
 * <em>G</em> is a set of pairwise edge-disjoint subgraphs
 * <em>H<sub>1</sub></em>, <em>H<sub>2</sub></em>, &hellip;
 * <em>H<sub>k</sub></em> such that every edge of <em>G</em> belongs to exactly
 * one subgraph <em>H<sub>i</sub></em> for <em>1 <= i <= k</em>. If every such
 * subgraph is isomorphic to a graph <em>H</em>, then <em>G</em> is said to have
 * an <b><em>H</em>-decomposition</b>. If <em>H</em> has the same order as
 * <em>G</em> and none of the vertices in <em>H</em> are isolated, then
 * <em>H</em> is a connected factor and the decomposition is called an <b>
 * <em>H</em>-factorization</b>. In particular, if <em>H</em> is a spanning
 * tree, we say that <em>G</em> has a <b>spanning tree factorization</b>.
 * </p>
 * <p>
 * An <em>H</em>-decomposition of a graph <em>G</em> of order <em>n</em> into
 * <em>H<sub>1</sub></em>, &hellip; <em>H<sub>k</sub></em> is said to be
 * <b>cyclic</b> if there exists an ordering
 * <em>(v<sub>1</sub>, v<sub>2</sub>, &hellip;, v<sub>n</sub>)</em> of the
 * vertices of <em>G</em> and graph isomorphisms
 * <em>f<sub>i</sub> : H<sub>1</sub> &rarr;  H<sub>i</sub><em>, for all <em>1 < i <= k</em>
 * where <em>k = E(G)/E(H)</em>, such that
 * <em>f<sub>i</sub>(v<sub>j</sub>) = v<sub>(i+j) mod n</sub><em>
 * for all <em>1 <= j <= n</em> .
 * </p>
 * <p>
 * This algorithm constructs the cyclic spanning tree decomposition of complete
 * graphs of even order such that the spanning tree has an Euler trail.
 * </p>
 * 
 * @author A. Horn
 */
public class CyclicSpanningTreeFactorization {

  /**
   * Finds the spanning tree <em>S</em> with an Euler trail such that the
   * <em>n</em>-complete graph of even order has an <em>S</em>-decomposition.
   * The trail is formed by the edges incident to vertices <em>k</em> and
   * <em>trails[k]</em> where <em>0 <= k < n</em>.
   * 
   * @param trail
   *          <em>trail[k] = k</em> for all <em>0 <= k < n</em>
   * @param n
   *          an even natural number greater or equal to 2
   */
  void spanning(int[] trail, int n) {
    int H = n / 2;

    int i = 0;
    int j = H - 1;
    int x = n - 1;
    int y = H;
    for (;;) {
      trail[i] = j;
      trail[x] = y;
      i++;
      y++;

      if (i == j) {
        break;
      }

      trail[j] = i;
      trail[y] = x;
      j--;
      x--;

      if (i == j) {
        break;
      }
    }

    if (n > 2) {
      trail[i] = y;
    }
  }

  /**
   * Given an <em>n</em>-complete graph of even order, this function returns
   * <em>n/2</em> cyclic permutations of the spanning tree which contains an
   * Euler trail. Denote the return value by <em>R</em>. Then, each tree
   * traversal starts at vertex <em>R[s][s]</em> where <em>0 <= s < n/2</em>.
   * 
   * @param n
   *          an even natural number greater or equal to 2
   * @return open, edge-disjoint trails of length n - 1 which decompose the
   *         complete graph of even order into n/2 spanning trees
   */
  int[][] decompose(int n) {
    int copies = n / 2;
    int[][] trails = new int[copies][n];
    for (int i = 0; i < copies; i++) {
      for (int j = 0; j < n; j++) {
        trails[i][j] = j;
      }
    }

    spanning(trails[0], n);

    int v;
    for (int i = 1; i < copies; i++) {
      for (int j = 0; j < n; j++) {
        v = (trails[i - 1][j] + 1) % n;
        trails[i][(j + 1) % n] = v;
      }
    }

    return trails;
  }

}
