package com.hhrb.ds.learn.db;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * User: Z J Wu
 * Date: 2019-04-25
 * Time: 10:47
 * Package: com.hhrb.ds.learn.db
 * <p>
 * 备忘录实现
 */
public class PipeCut {

  private Map<Integer, Integer> priceMap = Maps.newHashMapWithExpectedSize(10);
  private Map<Integer, Integer> bestPriceMap = Maps.newHashMapWithExpectedSize(10);

  private int getPrice(int len) {
    Integer i = priceMap.get(len);
    return i == null ? 0 : i.intValue();
  }

  public PipeCut putPrice(int len, int price) {
    priceMap.put(len, price);
    return this;
  }

  public int bestCutVal(int n) {
    int noCutPrice = getPrice(n);
    if (n <= 1) {
      return noCutPrice;
    }
    Integer bestCutPrice = bestPriceMap.get(n);
    if (bestCutPrice != null) {
      return bestCutPrice.intValue();
    }
    int max = noCutPrice;
    for (int i = 1; i <= n / 2; i++) {
      max = Math.max(max, bestCutVal(i) + bestCutVal(n - i));
    }
    bestPriceMap.put(n, max);
    return max;
  }

  public static void main(String[] args) {
    PipeCut pipeCut = new PipeCut().putPrice(1, 1).putPrice(2, 5).putPrice(3, 8).putPrice(4, 9)
                                   .putPrice(5, 10).putPrice(6, 17).putPrice(7, 17).putPrice(8, 20)
                                   .putPrice(9, 24).putPrice(10, 30);
    System.out.println("---------------------------------");
    for (int i = 0; i < 15; i++) {
      int l = i + 1;
      System.out.println(l + " - " + pipeCut.bestCutVal(l));
    }
  }
}
