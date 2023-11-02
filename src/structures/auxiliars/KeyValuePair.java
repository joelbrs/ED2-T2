package structures.auxiliars;

import utils.HashTableControl;

import java.util.Arrays;
import java.util.Objects;

public class KeyValuePair<TKey, TValue> {
    private TKey key;
    private TValue[] values;
    private Integer valuesLength = HashTableControl.DEFAULT_LENGTH;

    public KeyValuePair(TKey key) {
        this.key = key;
        this.values = (TValue[]) new Object[valuesLength];
    }

    public KeyValuePair(TKey key, TValue value) {
        this.key = key;
        this.values = (TValue[]) new Object[valuesLength];
        this.add(0, value);
    }

    public KeyValuePair(TKey key, int valuesLength) {
        this.key = key;
        this.valuesLength = valuesLength;
        this.values = (TValue[]) new Object[valuesLength];
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue[] getValues() {
        return values;
    }

    public void setValues(TValue[] values) {
        this.values = values;
    }

    public Integer getValuesLength() {
        return valuesLength;
    }

    public void setValuesLength(Integer valuesLength) {
        this.valuesLength = valuesLength;
    }

    public boolean contains(int index, TValue value) {
        if (index >= 0 && value != null) {
            for (TValue v : getValues()) {
                if (v != null && v.equals(value)) {
                    return true;
                }
            }
            return false;
        }

        throw new IllegalArgumentException();
    }

    public void add(int index, TValue value) {
        while (values[index] != null) {
            if (index > valuesLength) {
                resize(index);
            }
            index++;
        }
        values[index] = value;
    }

    public void resize(int valuesLength) {
        this.valuesLength = valuesLength;
        values = Arrays.copyOf(values, valuesLength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValuePair<?, ?> that = (KeyValuePair<?, ?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "{" +
                " key: " + key +
                ", valores: " + Arrays.toString(values) +
                '}';
    }
}
