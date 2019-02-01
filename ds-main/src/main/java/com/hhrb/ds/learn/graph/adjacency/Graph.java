package com.hhrb.ds.learn.graph.adjacency;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Z J Wu Date: 2019-02-01 Time: 10:22 Package: com.hhrb.ds.learn.graph
 */
public class Graph<V, E> {

  private Map<Vertex<V>, Set<Edge<V, E>>> store = Maps.newHashMap();

  public boolean addVertex(Vertex<V> v) {
    Set<Edge<V, E>> relations = store.get(v);
    if (relations == null) {
      store.put(v, Sets.newHashSet());
      return true;
    } else {
      return false;
    }
  }

  public void addRelation(Vertex<V> source, Vertex<V> target, E edgeVal) {
    if (!store.containsKey(source) || !store.containsKey(target)) {
      return;
    }
    Set<Edge<V, E>> relationsOfSource = store.get(source);
    relationsOfSource.add(new Edge<>(source, target, edgeVal));
  }

  public void dumpAll() {
    store.forEach((v, e) -> {
      System.out.println(v);
      e.stream().forEach(r -> System.out.println('\t' + r.toString()));
    });
  }

  public void bfs(int rootVertexId) {
    Set<Vertex> visited = Sets.newHashSet();
    Vertex current;
    Queue<Vertex> queue = Queues.newLinkedBlockingQueue();
    queue.add(Lists.newArrayList(store.keySet()).get(0));
    while ((current = queue.poll()) != null) {
      System.out.println(current);
      visited.add(current);
      Set<Edge<V, E>> edges = store.get(current);
      edges.stream().map(e -> e.target()).filter(v -> !visited.contains(v)).forEach(queue::add);
    }
  }

  private void dfs(Set<Vertex> visited, Vertex v) {
    if (visited.contains(v)) {
      return;
    }
    System.out.println(v);
    visited.add(v);
    Set<Edge<V, E>> relations = store.get(v);
    for (Edge r : relations) {
      dfs(visited, r.target());
    }
  }

  public void dfs() {
    Set<Vertex> visited = Sets.newHashSet();
    Vertex root = Lists.newArrayList(store.keySet()).get(0);
    dfs(visited, root);
  }

  public static void main(String[] args) {
    AtomicInteger counter = new AtomicInteger(0);
    Graph<String, String> matrixGraph = new Graph<>();
    Vertex<String> neo = new Vertex<>(counter.getAndIncrement(), "Neo");
    Vertex<String> trinity = new Vertex<>(counter.getAndIncrement(), "Trinity");
    Vertex<String> morpheus = new Vertex<>(counter.getAndIncrement(), "Morpheus");
    Vertex<String> smith = new Vertex<>(counter.getAndIncrement(), "Agent Smith");
    Vertex<String> niobe = new Vertex<>(counter.getAndIncrement(), "Niobe");
    Vertex<String> oracle = new Vertex<>(counter.getAndIncrement(), "The Oracle");
    Vertex<String> architect = new Vertex<>(counter.getAndIncrement(), "The Architect");

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
    matrixGraph.dfs();
  }

}
