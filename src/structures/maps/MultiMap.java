package structures.maps;

import structures.auxiliars.KeyValuePair;
import utils.HashTableControl;

import java.util.Arrays;

/*
 *   REQUISITOS:
 *
 *   1 - UMA CHAVE PODE TER VARIOS VALORES
 *   2 - UMA CHAVE NAO PODE TER VALORES REPETIDOS
 *   3 - NAO PODE HAVER CHAVES REPETIDAS
 *
 * */
public class MultiMap<TKey, TValue> {
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
            int index = getIndexByHashFunction(key), increment = 1;

            if (pairs[index] != null) {
                while (!pairs[index].getKey().equals(key) && index < length) {
                    index = getIndexByHashFunction(key, increment);
                    increment++;
                }
                pairs[index].put(value, index);
                return;
            }
            pairs[index] = new KeyValuePair(key, value, index);
            return;
        }
        throw new IllegalArgumentException();
    }

    private int getIndexByHashFunction(TKey key) {
        return Math.abs(key.hashCode() % length);
    }

    private int getIndexByHashFunction(TKey key, int increment) {
        return Math.abs((key.hashCode() + increment) % length);
    }

    @Override
    public String toString() {
        return "MultiMap{" +
                "pairs=" + Arrays.toString(pairs) +
                ", length=" + length +
                '}';
    }
}
