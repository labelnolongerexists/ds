package com.hhrb.ds.learn.tree.bi;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.hhrb.ds.learn.Utils;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * User: Z J Wu Date: 2019/1/30 Time: 23:18 Package: com.hhrb.ds.learn.tree.bi
 */
public class BST<T extends Comparable<T>> implements BinaryTree<T> {

  private static final int ROOT = 0;
  private static final int LEFT = 1;
  private static final int RIGHT = 2;

  private static class Node<T> {

    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T nodeElement;

    private int nodeDirection;

    private Node(int nodeDirection, Node<T> parent, Node<T> left, Node<T> right, T nodeElement) {
      this.nodeDirection = nodeDirection;
      this.parent = parent;
      this.left = left;
      this.right = right;
      this.nodeElement = nodeElement;
    }

    public static final <T> Node<T> root(T t) {
      return new Node<>(ROOT, null, null, null, t);
    }

    public static final <T> Node<T> leftChild(Node<T> parent, T nodeElement) {
      return new Node<>(LEFT, parent, null, null, nodeElement);
    }

    public static final <T> Node<T> rightChild(Node<T> parent, T nodeElement) {
      return new Node<>(RIGHT, parent, null, null, nodeElement);
    }

    public boolean hasChildren() {
      return hasLeft() || hasRight();
    }

    public boolean hasLeft() {
      return getLeft() != null;
    }

    public boolean hasRight() {
      return getRight() != null;
    }

    public boolean isLeft() {
      Node parent = getParent();
      return getParent() != null && parent.getLeft() == this;
    }

    public boolean isRight() {
      Node parent = getParent();
      return getParent() != null && parent.getRight() == this;
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

    private void printNode() {
      String nodeDir;
      switch (nodeDirection) {
        case LEFT:
          nodeDir = "L";
          break;
        case RIGHT:
          nodeDir = "R";
          break;
        case ROOT:
          nodeDir = "ROOT";
          break;
        default:
          throw new IllegalStateException();
      }
      System.out.println(nodeElement + "(" + nodeDir + ")");
    }

    public void removeLeft() {
      setLeft(null);
    }

    public void removeRight() {
      setRight(null);
    }

    public int getNodeDirection() {
      return nodeDirection;
    }

    public void setNodeDirection(int nodeDirection) {
      this.nodeDirection = nodeDirection;
    }

    private Node<T> minNode() {
      Node<T> current = this;
      while (current.getLeft() != null) {
        current = current.getLeft();
      }
      return current;
    }

    public void visit(TraversalType traversalType) {
      Node<T> left = getLeft(), right = getRight();
      switch (traversalType) {
        case DFS_PRE:
          printNode();
          if (left != null) {
            left.visit(traversalType);
          }
          if (right != null) {
            right.visit(traversalType);
          }
          return;
        case DFS_IN:
          if (left != null) {
            left.visit(traversalType);
          }
          printNode();
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
          printNode();
          return;
        case BFS:
          this.bfs();
      }
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

    public void setLeft(Node<T> l) {
      this.left = l;
      l.setParent(this);
      l.setNodeDirection(LEFT);
    }

    public Node<T> getRight() {
      return right;
    }

    public void setRight(Node<T> r) {
      this.right = r;
      r.setParent(this);
      r.setNodeDirection(RIGHT);
    }

    public T getNodeElement() {
      return nodeElement;
    }

    public void setNodeElement(T nodeElement) {
      this.nodeElement = nodeElement;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this).add("nodeElement", nodeElement)
                        .add("nodeDirection", nodeDirection).toString();
    }
  }

  private Node<T> root;

  public BST() {
  }

  public BST(T rootVal) {
    this.root = Node.root(rootVal);
  }

  @Override
  public void insert(T t) {
    if (root == null) {
      root = Node.root(t);
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
      // 如果孩子节点是空的, 造一个
      if (child == null) {
        if (attachRight) {
          child = Node.rightChild(node, t);
          node.setRight(child);
        } else {
          child = Node.leftChild(node, t);
          node.setLeft(child);
        }

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
  public void visit(TraversalType traversalType) {
    this.root.visit(traversalType);
  }

  @Override
  public boolean delete(T t) {
    if (t == null) {
      return false;
    }
    // 找到符合值的元素
    Node<T> node = root;
    while (node != null) {
      T nodeVal = node.getNodeElement();
      int compareVal = t.compareTo(nodeVal);
      if (Utils.aGtB(compareVal)) {
        node = node.getRight();
      } else if (Utils.aEqB(compareVal)) {
        break;
      } else if (Utils.aLtB(compareVal)) {
        node = node.getLeft();
      } else {
        throw new IllegalStateException();
      }
    }
    if (node == null) {
      return false;
    }
    System.out.println(node);

    if (node == root) {
      deleteRoot(node);
    } else {
      deleteChild(node);
    }
    return true;
  }

  private void deleteRoot(Node node) {

  }

  private void deleteChild(Node node) {
    boolean hasLeft = node.hasLeft(), hasRight = node.hasRight();
    Node parent = node.getParent(), child = hasLeft ? node.getLeft() : node.getRight();
    if (hasLeft && hasRight) {
      // 查找右子树的minNode, minNode不可能有左孩子, 因为左孩子更小. 但是可能有右孩子
      // 如果minNode有右孩子, 因为这个minNode要替代被删除的节点, 因此要把minNode的右孩子接到
      Node minNode = node.getRight().minNode();
      System.out.println("minNode - " + minNode);
      if (minNode.hasRight()) {
        minNode.getParent().setLeft(minNode.getRight());
      }
      minNode.setLeft(node.getLeft());
      if (node.isLeft()) {
        parent.setLeft(minNode);
      } else if (node.isRight()) {
        parent.setRight(minNode);
      } else {
        throw new IllegalStateException();
      }
    } else {
      if (node == parent.getLeft()) {
        parent.setLeft(child);
      } else if (node == parent.getRight()) {
        parent.setRight(child);
      } else {
        throw new IllegalStateException();
      }
    }
  }

  public static void main(String[] args) {
    Set<Integer> set = Sets.newHashSet();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      set.add(random.nextInt(100));
    }
    List<Integer> list = Lists.newArrayList(set);
    list = Lists.newArrayList(50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100);
    System.out.println(list);
    System.out.println("---------------------------------");
    BST<Integer> bst = new BST<>();
    list.stream().forEach(bst::insert);
    bst.visit(TraversalType.DFS_PRE);
    System.out.println(bst.delete(80));
    System.out.println("---------------------------------");
    bst.visit(TraversalType.DFS_PRE);
  }

}
