package com.hhrb.ds.learn.tree.bi;

/**
 * User: Z J Wu
 * Date: 2019/1/30
 * Time: 23:13
 * Package: com.hhrb.ds.learn.tree.bi
 */
public interface BinaryTree<T> {
  enum TraversalType {
    PreOrder, InOrder, PostOrder
  }

  TreeNode<T> getLeft();

  TreeNode<T> getRight();

  int size();

  int depth();

  void visit(TraversalType traversalType);

  default boolean isFull() {
    return size() == Math.pow(2, depth()) - 1;
  }

}
