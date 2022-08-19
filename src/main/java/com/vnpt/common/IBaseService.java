package com.vnpt.common;

import java.util.List;

public interface IBaseService<V,N> {
    List<V> getList();
    V getById(N id);
    V updateById(N id, V model);
    V save(V model);
    void deleteById(N id);
}
