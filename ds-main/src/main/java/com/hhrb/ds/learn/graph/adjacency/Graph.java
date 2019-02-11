package com.hhrb.ds.learn.graph.adjacency;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Z J Wu Date: 2019-02-01 Time: 10:22 Package: com.hhrb.ds.learn.graph
 */
public class Graph<V, E> {

  private static final class StoreNode<V, E> {

    private final Vertex<V> vertex;
    private final Set<Edge<V, E>> outEdges = Sets.newHashSet();

    public StoreNode(Vertex<V> vertex) {
      this.vertex = vertex;
    }

    public StoreNode<V, E> addEdge(Edge<V, E> edge) {
      this.outEdges.add(edge);
      return this;
    }

    public Vertex<V> getVertex() {
      return vertex;
    }

    public Set<Edge<V, E>> getOutEdges() {
      return outEdges;
    }
  }

  private Map<Integer, StoreNode<V, E>> store = Maps.newHashMap();

  public boolean addVertex(Vertex<V> v) {
    store.put(v.getId(), new StoreNode(v));
    return true;
  }

  public void addRelation(Vertex<V> source, Vertex<V> target, E edgeVal) {
    addRelation(source, target, edgeVal, 0);
  }

  public void addRelation(Vertex<V> source, Vertex<V> target, E edgeVal, int weight) {
    if (!store.containsKey(source) || !store.containsKey(target)) {
      return;
    }
    Set<Edge<V, E>> relationsOfSource = store.get(source).getOutEdges();
    relationsOfSource.add(new Edge<>(source, target, edgeVal, weight));
  }

  public void dumpAll() {
    store.forEach((v, node) -> {
      System.out.println(v);
      node.getOutEdges().stream().forEach(r -> System.out.println('\t' + r.toString()));
    });
  }

  public void bfs(int vertexId) {
    Set<Vertex> visited = Sets.newHashSet();
    Vertex current;
    Queue<Vertex> queue = Queues.newLinkedBlockingQueue();

    StoreNode sn = store.get(vertexId);
    if (sn == null) {
      return;
    }
    queue.add(sn.getVertex());
    while ((current = queue.poll()) != null) {
      System.out.println(current);
      visited.add(current);
      sn = store.get(current.getId());
      Set<Edge<V, E>> edges = sn.getOutEdges();
      edges.stream().map(e -> e.target()).filter(v -> !visited.contains(v)).forEach(queue::add);
    }
  }

  private void dfs(Set<Vertex> visited, Vertex v) {
    if (visited.contains(v)) {
      return;
    }
    System.out.println(v);
    visited.add(v);
    Set<Edge<V, E>> relations = store.get(v.getId()).getOutEdges();
    for (Edge r : relations) {
      dfs(visited, r.target());
    }
  }

  public void dfs(int vertexId) {
    StoreNode sn = store.get(vertexId);
    if (sn == null) {
      return;
    }
    Set<Vertex> visited = Sets.newHashSet();
    dfs(visited, sn.getVertex());
  }

  public static void testCaseMatrix() {
    AtomicInteger counter = new AtomicInteger(0);
    Graph<String, String> matrixGraph = new Graph<>();
    Vertex<String> neo = Vertex.create(counter.getAndIncrement(), "Neo");
    Vertex<String> trinity = Vertex.create(counter.getAndIncrement(), "Trinity");
    Vertex<String> morpheus = Vertex.create(counter.getAndIncrement(), "Morpheus");
    Vertex<String> smith = Vertex.create(counter.getAndIncrement(), "Agent Smith");
    Vertex<String> niobe = Vertex.create(counter.getAndIncrement(), "Niobe");
    Vertex<String> oracle = Vertex.create(counter.getAndIncrement(), "The Oracle");
    Vertex<String> architect = Vertex.create(counter.getAndIncrement(), "The Architect");

    matrixGraph.addVertex(neo);
    matrixGraph.addVertex(trinity);
    matrixGraph.addVertex(morpheus);
    matrixGraph.addVertex(smith);
    matrixGraph.addVertex(niobe);
    matrixGraph.addVertex(oracle);
    matrixGraph.addVertex(architect);

    matrixGraph.addRelation(neo, trinity, "love");
    matrixGraph.addRelation(trinity, neo, "love");
    matrixGraph.addRelation(neo, morpheus, "learnFrom");
    matrixGraph.addRelation(morpheus, neo, "teach");
    matrixGraph.addRelation(neo, smith, "fight");
    matrixGraph.addRelation(smith, neo, "fight");
    matrixGraph.addRelation(niobe, morpheus, "love");
    matrixGraph.addRelation(niobe, neo, "believe");
    matrixGraph.addRelation(neo, niobe, "thank");
    matrixGraph.addRelation(oracle, neo, "guide");
    matrixGraph.addRelation(oracle, morpheus, "guide");
    matrixGraph.addRelation(oracle, trinity, "guide");
    matrixGraph.addRelation(oracle, niobe, "guide");
    matrixGraph.addRelation(oracle, architect, "against");
    matrixGraph.addRelation(architect, oracle, "against");
    matrixGraph.addRelation(architect, smith, "create");

    matrixGraph.dumpAll();
    System.out.println("---------------------------------");
    matrixGraph.bfs(0);
    System.out.println("---------------------------------");
    matrixGraph.dfs(0);
  }

  public List<Integer> shortestPath(int startVertexId, int endVertexId) {
    StoreNode sn1 = store.get(startVertexId), sn2 = store.get(endVertexId);
    if (sn1 == null || sn2 == null) {
      return Collections.emptyList();
    }
    List<Integer> path = Lists.newArrayList();
    PriorityQueue<Integer> queue = Queues.newPriorityQueue();

    
    return path;
  }

  public static void main(String[] args) {
    Graph<Integer, Integer> g = new Graph<>();
    AtomicInteger counter = new AtomicInteger(1);

    int i = counter.incrementAndGet();
    Vertex<Integer> v1 = Vertex.create(i, i);
    i = counter.incrementAndGet();
    Vertex<Integer> v2 = Vertex.create(i, i);
    i = counter.incrementAndGet();
    Vertex<Integer> v3 = Vertex.create(i, i);
    i = counter.incrementAndGet();
    Vertex<Integer> v4 = Vertex.create(i, i);
    i = counter.incrementAndGet();
    Vertex<Integer> v5 = Vertex.create(i, i);
    i = counter.incrementAndGet();
    Vertex<Integer> v6 = Vertex.create(i, i);

    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);
    g.addVertex(v5);
    g.addVertex(v6);

    g.addRelation(v1, v2, counter.incrementAndGet(), 1);
    g.addRelation(v1, v3, counter.incrementAndGet(), 12);

    g.addRelation(v2, v3, counter.incrementAndGet(), 9);
    g.addRelation(v2, v4, counter.incrementAndGet(), 3);

    g.addRelation(v3, v5, counter.incrementAndGet(), 5);

    g.addRelation(v4, v3, counter.incrementAndGet(), 4);
    g.addRelation(v4, v5, counter.incrementAndGet(), 13);
    g.addRelation(v4, v6, counter.incrementAndGet(), 15);

    g.addRelation(v5, v6, counter.incrementAndGet(), 4);

  }

}
