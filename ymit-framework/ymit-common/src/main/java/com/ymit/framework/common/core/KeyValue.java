package com.ymit.framework.common.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * Key Value 的键值对
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class KeyValue<K, V> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 键
     */
    private final K key;
    /**
     * 值
     */
    private V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
