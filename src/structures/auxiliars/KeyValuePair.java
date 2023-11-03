package structures.auxiliars;

import utils.HashUtils;
import utils.HashTableControl;

import java.util.*;

public class KeyValuePair<TKey, TValue> extends HashUtils<TKey> {
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

    public KeyValuePair(TKey key, List<TValue> values) {
        this.key = key;
        this.values = values;
    }

    public TKey getKey() {
        return key;
    }

    public List<TValue> getValues() {
        return values;
    }

    public void put(TValue value, int index) throws Exception {
        if (this.contains(value)) {
            throw new Exception("Value already exists!");
        }

        if (index >= 0 && value != null) {
            if (getLoadFactor(values.toArray(), values.size()) > 0.75) {
                resizeToDouble(values.size());
            }

            if (index >= values.size()) {
                rehashing(index);
            }

            if (values.get(index) != null) {
                int increment = 1, originalIndex = index;
                while (values.get(index) != null) {
                    index = getIndexByHashFunction(key, values.size(), increment);
                    increment++;

                    if (originalIndex != index && increment > values.size() - 1) {
                        rehashing(increment);
                    }
                }
            }
            values.set(index, value);
        }
    }

    private boolean contains(TValue value) {
        for (TValue v : values) {
            if (v != null && v.equals(value)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void rehashing(int newLength) {
        newLength*=2;
        TValue[] newValues = (TValue[]) new Object[newLength];
        int increment = 1;

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != null) {
                int index = getIndexByHashFunction(key, newLength);

                while (newValues[index] != null) {
                    index = getIndexByHashFunction(key, newLength, increment);
                    increment++;
                }

                newValues[index] = values.get(i);
            } else {
                newValues[i] = null;
            }
        }

        values = Arrays.asList(newValues);
    }

    @Override
    public String toString() {
        return "\nKeyValuePair: {" +
                "Key: " + key +
                ", Length: " + values.size() +
                ", Values: " + values +
                '}';
    }

}
