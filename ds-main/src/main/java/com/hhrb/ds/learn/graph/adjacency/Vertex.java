package com.hhrb.ds.learn.graph.adjacency;

import java.util.Objects;

/**
 * User: Z J Wu Date: 2019-02-01 Time: 10:12 Package: com.hhrb.ds.learn.graph
 */
public class Vertex<V> {

  private int id;

  private V value;

  public Vertex(int id) {
    this.id = id;
  }

  public Vertex(int id, V value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Vertex))
      return false;
    Vertex<?> vertex = (Vertex<?>) o;
    return id == vertex.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "V(id=" + id + ",val=" + value + ")";
  }

  public V getValue() {
    return value;
  }
}
