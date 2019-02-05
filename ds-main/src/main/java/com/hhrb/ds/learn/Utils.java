package com.hhrb.ds.learn;

/**
 * User: Z J Wu Date: 2019/2/5 Time: 18:21 Package: com.hhrb.ds.learn
 */
public class Utils {

  public static final boolean aGtB(Comparable a, Comparable b) {
    return a.compareTo(b) == 1;
  }

  public static final boolean aGtB(int compareVal) {
    return compareVal == 1;
  }

  public static final boolean aEqB(Comparable a, Comparable b) {
    return a.compareTo(b) == 0;
  }

  public static final boolean aEqB(int compareVal) {
    return compareVal == 0;
  }

  public static final boolean aLtB(Comparable a, Comparable b) {
    return a.compareTo(b) == -1;
  }

  public static final boolean aLtB(int compareVal) {
    return compareVal == -1;
  }
}
