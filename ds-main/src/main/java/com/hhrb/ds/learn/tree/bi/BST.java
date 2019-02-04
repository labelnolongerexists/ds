package com.hhrb.ds.learn.tree.bi;

import com.google.common.collect.Queues;

import java.util.Queue;

/**
 * User: Z J Wu Date: 2019/1/30 Time: 23:18 Package: com.hhrb.ds.learn.tree.bi
 */
public class BST<T> implements BinaryTree<T> {

  private static class Node<T> {

    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T nodeElement;

    public Node(Node<T> parent, Node<T> left, Node<T> right, T nodeElement) {
      this.parent = parent;
      this.left = left;
      this.right = right;
      this.nodeElement = nodeElement;
    }

    public Node(Node<T> parent, T nodeElement) {
      this.parent = parent;
      this.nodeElement = nodeElement;
    }

    public static final <T> Node<T> root(T t) {
      return new Node<>(null, null, null, t);
    }

    public Node<T> attachLeft(T leftVal) {
      this.left = new Node(this, leftVal);
      return this;
    }

    public Node<T> attachLeft(Node<T> left) {
      this.left = left;
      left.setParent(this);
      return this;
    }

    public Node<T> attachRight(T rightVal) {
      this.right = new Node(this, rightVal);
      return this;
    }

    public Node<T> attachRight(Node<T> right) {
      this.right = right;
      right.setParent(this);
      return this;
    }

    public Node<T> getParent() {
      return parent;
    }

    public void setParent(Node<T> parent) {
      this.parent = parent;
    }

    public Node<T> getLeft() {
      return left;
    }

    public void setLeft(Node<T> left) {
      this.left = left;
    }

    public Node<T> getRight() {
      return right;
    }

    public void setRight(Node<T> right) {
      this.right = right;
    }

    public T getNodeElement() {
      return nodeElement;
    }

    public void setNodeElement(T nodeElement) {
      this.nodeElement = nodeElement;
    }

    public boolean hasLeft() {
      return getLeft() != null;
    }

    public boolean hasRight() {
      return getRight() != null;
    }

    public void bfs() {
      Queue<Node<T>> storeQueue = Queues.newLinkedBlockingQueue();
      Node<T> node = this;
      storeQueue.add(node);
      while ((node = storeQueue.poll()) != null) {
        System.out.println(node.getNodeElement());
        if (node.hasLeft()) {
          storeQueue.add(node.getLeft());
        }
        if (node.hasRight()) {
          storeQueue.add(node.getRight());
        }
      }
    }

    public void visit(TraversalType traversalType) {
      Node<T> left = getLeft(), right = getRight();
      T current = getNodeElement();
      switch (traversalType) {
        case dfs_PreOrder:
          System.out.println(current);
          if (left != null) {
            left.visit(traversalType);
          }
          if (right != null) {
            right.visit(traversalType);
          }
          return;
        case dfs_InOrder:
          if (left != null) {
            left.visit(traversalType);
          }
          System.out.println(current);
          if (right != null) {
            right.visit(traversalType);
          }
          return;
        case dfs_PostOrder:
          if (left != null) {
            left.visit(traversalType);
          }
          if (right != null) {
            right.visit(traversalType);
          }
          System.out.println(current);
          return;
        case bfs:
          this.bfs();
      }
    }

  }

  private Node<T> root;

  public BST(T rootVal) {
    this.root = Node.root(rootVal);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int depth() {
    return 0;
  }

  @Override
  public void visit(TraversalType traversalType) {
    this.root.visit(traversalType);
  }

  public static void main(String[] args) {
  }

}
