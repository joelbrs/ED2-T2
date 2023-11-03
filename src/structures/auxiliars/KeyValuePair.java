package structures.auxiliars;

import utils.HashTableControl;

import java.util.*;

public class KeyValuePair<TKey, TValue> {
    private TKey key;
    private List<TValue> values = new ArrayList<>();

    public KeyValuePair(TKey key) {
        this.key = key;

        for (int i = 0; i < HashTableControl.DEFAULT_LENGTH; i++) {
            values.add(null);
        }
    }

    public KeyValuePair(TKey key, TValue value, int index) throws Exception {
        this(key);
        this.put(value, index);
    }

    public TKey getKey() {
        return key;
    }

    public void put(TValue value, int index) throws Exception {
        if (this.contains(value)) {
            throw new Exception("Value already exists!");
        }

        if (index >= values.size()) {
            resize(index);
        }

        values.add(index, value);
    }

    private boolean contains(TValue value) {
        for (TValue v : values) {
            if (v != null && v.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private void resize(int newLength) {
        for (int i = values.size(); i < newLength; i++) {
            values.add(null);
        }
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "key=" + key +
                ", values=" + values.toString() +
                " length=" + values.size() +
                '}';
    }
}
