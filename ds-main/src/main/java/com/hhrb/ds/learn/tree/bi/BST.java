package com.hhrb.ds.learn.tree.bi;

import com.google.common.collect.Queues;
import org.apache.commons.lang3.StringUtils;

import java.util.Queue;
import java.util.Random;

/**
 * User: Z J Wu Date: 2019/1/30 Time: 23:18 Package: com.hhrb.ds.learn.tree.bi
 */
public class BST<T extends Comparable<T>> implements BinaryTree<T> {

  private static class Node<T> {

    private final int depth;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T nodeElement;

    public Node(int depth, Node<T> parent, Node<T> left, Node<T> right, T nodeElement) {
      this.depth = depth;
      this.parent = parent;
      this.left = left;
      this.right = right;
      this.nodeElement = nodeElement;
    }

    public Node(Node<T> parent, T nodeElement) {
      this(parent.depth + 1, parent, null, null, nodeElement);
    }

    public static final <T> Node<T> root(T t) {
      return new Node<>(1, null, null, null, t);
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

    private void printLR(int depth, boolean left) {
      if (left) {
        System.out.print(StringUtils.repeat('\t', depth) + "L->");
      } else {
        System.out.print(StringUtils.repeat('\t', depth) + "R->");
      }
    }

    public void visit(TraversalType traversalType) {
      Node<T> left = getLeft(), right = getRight();
      T current = getNodeElement();
      switch (traversalType) {
        case DFS_PRE:
          System.out.println(current);
          if (left != null) {
            printLR(depth, true);
            left.visit(traversalType);
          }
          if (right != null) {
            printLR(depth, false);
            right.visit(traversalType);
          }
          return;
        case DFS_IN:
          if (left != null) {
            left.visit(traversalType);
          }
          System.out.println(current);
          if (right != null) {
            right.visit(traversalType);
          }
          return;
        case DFS_POST:
          if (left != null) {
            left.visit(traversalType);
          }
          if (right != null) {
            right.visit(traversalType);
          }
          System.out.println(current);
          return;
        case BFS:
          this.bfs();
      }
    }

    public int getDepth() {
      return depth;
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
  }

  private Node<T> root;

  private int totalDepth;

  public BST() {
  }

  public BST(T rootVal) {
    this.root = Node.root(rootVal);
  }

  @Override
  public void insert(T t) {
    if (root == null) {
      root = Node.root(t);
      this.totalDepth = root.getDepth();
      return;
    }
    Node<T> node = root, child;
    for (; ; ) {
      if (node == null) {
        break;
      }
      T nodeVal = node.getNodeElement();
      boolean attachRight;
      int compareVal = t.compareTo(nodeVal);

      if (compareVal == 0) {
        return;
      }
      // 放右子树
      else if (compareVal == 1) {
        child = node.getRight();
        attachRight = true;
      }
      // 放左子樹
      else {
        child = node.getLeft();
        attachRight = false;
      }
      if (child == null) {
        child = new Node<>(node, t);
        if (attachRight) {
          node.attachRight(child);
        } else {
          node.attachLeft(child);
        }
        totalDepth = child.getDepth();
        return;
      }
      node = child;
    }
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int depth() {
    return totalDepth;
  }

  @Override
  public void visit(TraversalType traversalType) {
    this.root.visit(traversalType);
  }

  public static void main(String[] args) {
    Random random = new Random();
    BST<Integer> bst = new BST<>();
    for (int i = 0; i < 10; i++) {
      int intVal = random.nextInt(50);
      System.out.println(intVal);
      bst.insert(intVal);
    }
    System.out.println("---------------------------------");
    bst.visit(TraversalType.DFS_PRE);
    System.out.println(bst.depth());
  }

}
