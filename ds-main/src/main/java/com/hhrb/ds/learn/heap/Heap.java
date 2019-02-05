package com.hhrb.ds.learn.heap;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * User: Z J Wu Date: 2019-02-02 Time: 09:58 Package: com.hhrb.ds.learn.heap
 */
public class Heap<T extends Comparable<T>> {

  private List<T> store;

  public Heap() {
    store = Lists.newArrayList();
  }

  public int size() {
    return CollectionUtils.size(store);
  }

  private T max(T t1, T t2) {
    return gt(t1, t2) ? t1 : t2;
  }

  private int indexOfMax(int idx1, int idx2) {
    return gt(get(idx1), get(idx2)) ? idx1 : idx2;
  }

  private boolean gt(T t1, T t2) {
    return t1.compareTo(t2) == 1;
  }

  public T get(int idx) {
    return store.get(idx);
  }

  public Heap(List<T> raw) {
    store = Lists.newArrayList(raw);
    // 从最后一个叶子节点的父节点开始, 直到根节点
    for (int i = (size() - 1) / 2; i >= 0; i--) {
      int currentNodeIdx = i;
      for (; ; ) {
        int leftChildIdx = getLeftChildIdx(currentNodeIdx);
        if (leftChildIdx < 0) {
          break;
        }
        int rightChildIdx = getRightChildIdx(currentNodeIdx), maxNodeIdx;
        if (rightChildIdx > 0) {
          maxNodeIdx = indexOfMax(currentNodeIdx, indexOfMax(leftChildIdx, rightChildIdx));
        } else {
          maxNodeIdx = indexOfMax(currentNodeIdx, leftChildIdx);
        }
        if (currentNodeIdx == maxNodeIdx) {
          break;
        }
        swap(currentNodeIdx, maxNodeIdx);
        currentNodeIdx = maxNodeIdx;
      }
    }
  }

  public void insert(T t) {
    store.add(t);
    int currentNodeIdx = store.size() - 1, parentNodeIdx;
    while ((parentNodeIdx = getParent(currentNodeIdx)) >= 0) {
      T parentNode = get(parentNodeIdx);
      if (gt(t, parentNode)) {
        swap(currentNodeIdx, parentNodeIdx);
        currentNodeIdx = parentNodeIdx;
      } else {
        break;
      }
    }
  }

  private boolean hasChildren(int idx) {
    return getLeftChildIdx(idx) > 0;
  }

  public int getParent(int idx) {
    if (idx <= 0 || idx >= store.size()) {
      return -1;
    }
    return (idx - 1) / 2;
  }

  public T getLeftChild(int idx) {
    return get(getLeftChildIdx(idx));
  }

  public int getLeftChildIdx(int idx) {
    if (idx < 0 || idx >= store.size() || (idx * 2 + 1) >= store.size()) {
      return -1;
    }
    return idx * 2 + 1;
  }

  public T getRightChild(int idx) {
    return get(getRightChildIdx(idx));
  }

  public int getRightChildIdx(int idx) {
    if (idx < 0 || idx > store.size() || (idx * 2 + 2) >= store.size()) {
      return -1;
    }
    return idx * 2 + 2;
  }

  private void swap(int i, int j) {
    T t = store.get(i);
    store.set(i, store.get(j));
    store.set(j, t);
  }

  public void delete() {
    swap(0, size() - 1);
    store.remove(store.size() - 1);
    int currentIdx = 0;
    for (; ; ) {
      int leftChildIdx = getLeftChildIdx(currentIdx);
      if (leftChildIdx < 0) {
        break;
      }
      int rightChildIdx = getRightChildIdx(currentIdx), maxNodeIdx;
      if (rightChildIdx > 0) {
        maxNodeIdx = indexOfMax(currentIdx, indexOfMax(leftChildIdx, rightChildIdx));
      } else {
        maxNodeIdx = indexOfMax(currentIdx, leftChildIdx);
      }
      if (currentIdx == maxNodeIdx) {
        break;
      }
      swap(currentIdx, maxNodeIdx);
      currentIdx = maxNodeIdx;
    }
  }

  public static void main(String[] args) {
    Random random = new Random();

    int elementCnt = 10;
    List<Integer> list = Lists.newArrayListWithCapacity(elementCnt);

    for (int i = 0; i < elementCnt; i++) {
      list.add(random.nextInt(100));
    }
    System.out.println(list);
    Heap<Integer> heap = new Heap<>(list);
    System.out.println(heap.store);

    int newInt = random.nextInt(150);
    System.out.println(newInt);
    heap.insert(newInt);
    System.out.println(heap.store);
    heap.delete();
    System.out.println(heap.store);
  }

}
