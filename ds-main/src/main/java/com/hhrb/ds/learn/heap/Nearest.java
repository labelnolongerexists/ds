package com.hhrb.ds.learn.heap;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * User: Z J Wu Date: 2019-02-14 Time: 21:38 Package: com.hhrb.ds.learn.heap
 */
public class Nearest {
  public static final class Node{
    private int x;
    private int y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {
    Comparator<List<Integer>> c= (o1, o2) -> {
      double d1=o1.get(2)-0;
      return o1.get(0).compareTo(o2.get(0));
    };
    List<Integer> l=null;

    PriorityQueue<List<Integer>>  queue=new PriorityQueue<>()      ;
  }
}
