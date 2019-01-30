package com.hhrb.ds.learn.tree.bi;

import static com.hhrb.ds.learn.tree.bi.BinaryTree.TraversalType.InOrder;
import static com.hhrb.ds.learn.tree.bi.BinaryTree.TraversalType.PostOrder;
import static com.hhrb.ds.learn.tree.bi.BinaryTree.TraversalType.PreOrder;

/**
 * User: Z J Wu Date: 2019/1/30 Time: 23:18 Package: com.hhrb.ds.learn.tree.bi
 */
public class TreeNode<T> implements BinaryTree<T> {

  private TreeNode<T> parent;

  private TreeNode<T> left;
  private TreeNode<T> right;

  private T nodeElement;

  public static final <T> TreeNode<T> newNode(T t) {
    return new TreeNode<>(null, t);
  }

  public TreeNode(TreeNode<T> parent, T nodeElement) {
    this.parent = parent;
    this.nodeElement = nodeElement;
  }

  public TreeNode<T> attachLeft(T leftVal) {
    this.left = new TreeNode(this, leftVal);
    return this;
  }

  public TreeNode<T> attachLeft(TreeNode<T> left) {
    this.left = left;
    left.setParent(this);
    return this;
  }

  public TreeNode<T> attachRight(T rightVal) {
    this.right = new TreeNode(this, rightVal);
    return this;
  }

  public TreeNode<T> attachRight(TreeNode<T> right) {
    this.right = right;
    right.setParent(this);
    return this;
  }

  public T getNodeElement() {
    return nodeElement;
  }

  public void setParent(TreeNode<T> parent) {
    this.parent = parent;
  }

  public TreeNode<T> getParent() {
    return parent;
  }

  @Override
  public TreeNode<T> getLeft() {
    return left;
  }

  @Override
  public TreeNode<T> getRight() {
    return right;
  }

  @Override
  public int size() {
    TreeNode<T> left = getLeft(), right = getRight();
    return ((left == null) ? 0 : left.size()) + ((right == null) ? 0 : right.size()) + 1;
  }

  @Override
  public int depth() {
    TreeNode<T> parent = getParent();
    return parent == null ? 1 : parent.depth() + 1;
  }

  @Override
  public void visit(TraversalType traversalType) {
    TreeNode<T> left = getLeft(), right = getRight();
    T current = getNodeElement();
    switch (traversalType) {
      case PreOrder:
        System.out.println(current);
        if (left != null) {
          left.visit(traversalType);
        }
        if (right != null) {
          right.visit(traversalType);
        }
        return;
      case InOrder:
        if (left != null) {
          left.visit(traversalType);
        }
        System.out.println(current);
        if (right != null) {
          right.visit(traversalType);
        }
        return;
      case PostOrder:
        if (left != null) {
          left.visit(traversalType);
        }
        if (right != null) {
          right.visit(traversalType);
        }
        System.out.println(current);
        return;
    }

  }

  public static void main(String[] args) {
    TreeNode<Integer> parent = TreeNode.newNode(1)
                                       .attachLeft(TreeNode.newNode(2).attachLeft(4).attachRight(5))
                                       .attachRight(TreeNode.newNode(3).attachRight(6));
    parent.visit(PreOrder);
    System.out.println("---------------------------------");
    parent.visit(InOrder);
    System.out.println("---------------------------------");
    parent.visit(PostOrder);

  }

}
