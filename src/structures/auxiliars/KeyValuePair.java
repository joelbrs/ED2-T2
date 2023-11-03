package structures.auxiliars;

import utils.HashUtils;
import utils.HashTableControl;

import java.util.*;

/**
 * Classe que vai representar cada par de chave-valor a ser utilizado no MultiMapa
 *
 * OBS¹.: Antes de verificar essa classe mais adiante, dê uma olhada na classe HashUtils, uma classe abstrata utilizada para padronizar alguns comportamentos da Tabela Hash
 * que foram implementadas tanto na classe corrente, quanto na de MultiMapa.
 *
 * OBS²: Utilizamos a mesma abordagem de Tabela Hash para guardar os valores associados à chave a fim de minimzar o custo e o tempo para operações de inserção e busca desses valores
 * */
public class KeyValuePair<TKey, TValue> extends HashUtils<TKey> {

    //Referência para a chave
    private TKey key;

    //Referência para os valores associados a essa chave em formato de Tabela Hash
    private List<TValue> values = new ArrayList<>();

    //Construtor
    public KeyValuePair(TKey key) {
        this.key = key;

        for (int i = 0; i < HashTableControl.DEFAULT_LENGTH; i++) {
            values.add(null);
        }
    }

    //Construtor
    public KeyValuePair(TKey key, TValue value, int index) throws Exception {
        this(key);
        this.put(value, index);
    }

    //Construtor
    public KeyValuePair(TKey key, List<TValue> values) {
        this.key = key;
        this.values = values;
    }

    //Getters
    public TKey getKey() {
        return key;
    }

    //Getters
    public List<TValue> getValues() {
        return values;
    }

    /**
     * Put
     *
     * Utilizamos abordagem e verificações parecidas com o método Put do MultiMapa, já que ambas tratam de Hash Tables
     *
     * @param value representa o valor a ser associado à chave
     * @param index representa a posição da Tabela Hash em que esse valor será inserido
     * */
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

                    if (originalIndex == index && increment > values.size() - 1) {
                        rehashing(increment);
                    }
                }
            }
            values.set(index, value);
        }
    }

    /**
     * Contains
     *
     * @param value representa o valor a ser buscado na Tabela Hash de valores associados à chave do par chave-valor
     * @return um booleano se encontrou ou não o valor na Tabela Hash
     * */
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
