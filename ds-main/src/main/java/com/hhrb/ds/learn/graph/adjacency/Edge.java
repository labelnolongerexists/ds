package com.hhrb.ds.learn.graph.adjacency;

import java.util.Objects;

/**
 * User: Z J Wu Date: 2019-02-01 Time: 10:14 Package: com.hhrb.ds.learn.graph
 */
public class Edge<V, E> {

  private Vertex<V> node1;
  private Vertex<V> node2;

  private E e;
  private int weight;

  public Edge(Vertex<V> node1, Vertex<V> node2, E e) {
    this(node1, node2, e, 0);
  }

  public Edge(Vertex<V> node1, Vertex<V> node2, E e, int weight) {
    this.node1 = node1;
    this.node2 = node2;
    this.e = e;
    this.weight = weight;
  }

  public Vertex<V> source() {
    return node1;
  }

  public Vertex<V> target() {
    return node2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Edge))
      return false;
    Edge<?, ?> edge = (Edge<?, ?>) o;
    return node1.equals(edge.node1) && node2.equals(edge.node2) && e.equals(edge.e);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node1, node2, e);
  }

  @Override
  public String toString() {
    return "[" + node1.getValue() + "]-" + e.toString() + "@" + weight + "->[" + node2
      .getValue() + "]";
  }
}
