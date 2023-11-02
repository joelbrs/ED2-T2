package structures.maps;

import structures.auxiliar.KeyValuePair;
import structures.utils.HashTableControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void put(TKey key, TValue value) throws Exception{
        if (key != null && value != null) {
            var index = getIndexByHashFunction(key);

            while (pairs[index] != null) {
                if (index > length) {
                    resize(index);
                }

                if (pairs[index].getKey().equals(key)) {
                    if (pairs[index].contains(index, value)) {
                        throw new Exception("Value Already Exists!");
                    }
                    pairs[index].add(index, value);
                }
                index++;
            }

            pairs[index] = new KeyValuePair(key, value);
            return;
        }
        throw new IllegalArgumentException();
    }

    public List<TValue> findAll(TKey key) throws Exception{
        var index = getIndexByHashFunction(key);

        if (pairs[index] != null) {
            List<TValue> values = new ArrayList<>();
            while (!pairs[index].getKey().equals(key)) {
                index++;
            }

            for (TValue v : (TValue[]) pairs[index].getValues()) {
                values.add(v);
            }
            return values;
        }

        throw new Exception("Key does not exists!");
    }

    public int getIndexByHashFunction(TKey key) {
        return Math.abs(key.hashCode() % length);
    }

    public void resize(int length) {
        this.length = length;
        pairs = Arrays.copyOf(pairs, length);
    }

    @Override
    public String toString() {
        return "MultiMap:" +
                "Pares de Chave-Valor:" + Arrays.toString(pairs) +
                "\n";
    }
}
