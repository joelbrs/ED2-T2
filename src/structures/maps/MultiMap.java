package structures.maps;

import structures.auxiliars.KeyValuePair;
import utils.HashUtils;
import utils.HashTableControl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 *   REQUISITOS:
 *
 *   1 - UMA CHAVE PODE TER VARIOS VALORES
 *   2 - UMA CHAVE NAO PODE TER VALORES REPETIDOS
 *   3 - NAO PODE HAVER CHAVES REPETIDAS
 *
 * */
public class MultiMap<TKey, TValue> extends HashUtils<TKey> {
    private KeyValuePair[] pairs;
    private Integer length = HashTableControl.DEFAULT_LENGTH;

    public MultiMap() {
        this.pairs = new KeyValuePair[length];
    }

    public MultiMap(int length) {
        this.length = length;
        this.pairs = new KeyValuePair[length];
    }

    public void put(TKey key, TValue value) throws Exception {
        if (key != null && value != null) {
            int index = getIndexByHashFunction(key, length), increment = 1, originalIndex = index;

            if (getLoadFactor(pairs, length) > 0.75) {
                resizeToDouble(length);
            }

            if (pairs[index] != null) {
                while (pairs[index] != null && !pairs[index].getKey().equals(key)) {
                    index = getIndexByHashFunction(key, length, increment);
                    increment++;

                    if (originalIndex != index && increment > length - 1) {
                        rehashing(increment);
                    }
                }

                if (pairs[index] != null) {
                    pairs[index].put(value, index);
                    return;
                }
            }
            pairs[index] = new KeyValuePair(key, value, index);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void rehashing(int newLength) {
        newLength*=2;
        KeyValuePair[] newPairs = new KeyValuePair[newLength];
        int increment = 1;

        for (int i = 0; i < length; i++) {
            if (pairs[i] != null) {
                int index = getIndexByHashFunction((TKey) pairs[i].getKey(), newLength);

                while (newPairs[index] != null) {
                    index = getIndexByHashFunction((TKey) pairs[i].getKey(), newLength, increment);
                    increment++;
                }

                newPairs[index] = new KeyValuePair(pairs[i].getKey(), pairs[i].getValues());
            } else {
                newPairs[i] = null;
            }
        }

        length = newLength;
        pairs = newPairs;
    }

    public List<TValue> findAll(TKey key) throws Exception {
        if (key != null) {
            int index = getIndexByHashFunction(key, length), increment = 1;

            if (pairs[index] != null) {
                while (pairs[index] != null && !pairs[index].getKey().equals(key)) {
                    index = getIndexByHashFunction(key, length, increment);
                    increment++;
                }

                if (pairs[index] != null) {
                    return (List<TValue>) pairs[index].getValues().stream().filter(v -> v != null).collect(Collectors.toList());
                }
            }
            throw new Exception("That key does not exists!");

        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return  "MultiMap: {"
                + "Length: " + length
                + ", Pairs: " + Arrays.toString(pairs)
                + '}';
    }
}
