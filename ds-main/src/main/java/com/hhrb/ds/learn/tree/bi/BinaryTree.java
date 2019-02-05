package com.hhrb.ds.learn.tree.bi;

/**
 * User: Z J Wu Date: 2019/1/30 Time: 23:13 Package: com.hhrb.ds.learn.tree.bi
 */
public interface BinaryTree<T extends Comparable<T>> {

  enum TraversalType {
    DFS_PRE,
    DFS_IN,
    DFS_POST,
    BFS
  }

  void insert(T t);

  int size();

  void visit(TraversalType traversalType);

  boolean delete(T t);

}
