package com.hhrb.ds.learn.tree.bi;

import static com.google.common.base.Preconditions.checkArgument;
import static com.hhrb.ds.learn.tree.bi.RBT.NodeColor.BLACK;
import static com.hhrb.ds.learn.tree.bi.RBT.NodeColor.RED;

/**
 * User: Z J Wu Date: 2019-02-13 Time: 14:40 Package: com.hhrb.ds.learn.tree.bi
 */
public class RBT<T extends Comparable<T>> extends BST<T> {

  public enum NodeColor {
    RED,
    BLACK
  }

  public static class ColorNode<T> extends Node<T> {

    private NodeColor color;

    public ColorNode(Node<T> parent, Node<T> left, Node<T> right, T nodeElement, NodeColor color) {
      super(parent, left, right, nodeElement);
      checkArgument(RED.equals(color) || BLACK.equals(color));
      this.color = color;
    }

    public NodeColor getColor() {
      return color;
    }

    // 设定1, 根节点必须是黑的
    public static final <T> ColorNode root(T t) {
      return new ColorNode(null, null, null, t, BLACK);
    }

    // 设定4, 红色节点的2个孩子必须是黑色
    public static boolean validateColor(NodeColor parentColor, NodeColor childColor) {
      return BLACK.equals(parentColor) || BLACK.equals(childColor);
    }

    public static final <T> ColorNode<T> leftChild(ColorNode<T> parent, T nodeElement,
                                                   NodeColor color) {
      checkArgument(validateColor(parent.getColor(), color));
      return new ColorNode<>(parent, null, null, nodeElement, color);
    }

    public static final <T> ColorNode<T> rightChild(ColorNode<T> parent, T nodeElement,
                                                    NodeColor color) {
      checkArgument(validateColor(parent.getColor(), color));
      return new ColorNode<>(parent, null, null, nodeElement, color);
    }

    public void rorateLeft(ColorNode root) {
      // 没有右孩子的无法左旋(见hashmap的2185)
      if (!hasRight()) {
        return;
      }
      ColorNode current = this, right = (ColorNode) current.right, rightLeft, currentParent;
      /*
       * 左旋p(当前节点), 也就是让当前节点下沉1层
       * p的右孩子r会替代p的位置. 但是要重新处理p的左孩子.
       * 因为p的右子树都比p大, 那么p的右孩子的左子树
       * 很适合假借在p下沉1层后右子树的位置.
       *
       * current的右孩子(曾经是right), 现在变成right的左孩子,
       * 同时把right的左孩子的双亲设置为当前current
       * */
      if ((rightLeft = (ColorNode) (current.right = right.left)) != null) {
        rightLeft.parent = current;
      }
      // p曾是树根, 顺便把值付给t2, 顺便把颜色改了
      // r的双亲从p变成p的parent(可能没有, 但没关系, 说明r变成了树的新树根(不过颜色得改改))
      if ((currentParent = (ColorNode) (right.parent = current.parent)) == null) {
        root.color = BLACK;
      }
      // p曾是左孩子, 那也得让r变成左孩子
      else if (currentParent.left == current) {
        currentParent.left = right;
      }
      // p曾是右孩子, 让r变成右孩子
      else {
        currentParent.right = right;
      }
      right.left = current;
      // current 的新双亲变成 right
      current.parent = right;
    }

    public void rotateRight() {
      if (!hasLeft()) {
        return;
      }

    }

  }

  public RBT() {
  }

  public RBT(T rootVal) {
    super(rootVal);
  }

}
