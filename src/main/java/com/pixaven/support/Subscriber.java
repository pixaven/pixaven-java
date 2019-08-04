package com.pixaven.support;

public interface Subscriber<T> {
    void onResponse(T value);
    void onFailure( T value);
}
