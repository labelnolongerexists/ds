package com.hhrb.ds.learn.graph;

import java.util.List;

/**
 * User: Z J Wu Date: 2019-02-01 Time: 10:34 Package: com.hhrb.ds.learn.graph
 */
public interface VertexDef<V> {

  int getId();

  V getValue();

  List<VertexDef<V>> getOut();

  List<VertexDef<V>> getIn();

}
