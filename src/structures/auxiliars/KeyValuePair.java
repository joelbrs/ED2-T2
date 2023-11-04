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
public class KeyValuePair<TKey, TValue> extends HashUtils {

    // Referência para a chave
    private TKey key;

    // Referência para os valores associados a essa chave em formato de array
    private TValue[] values;

    // Construtor
    public KeyValuePair(TKey key) {
        this.key = key;
        this.values = (TValue[]) new Object[HashTableControl.DEFAULT_LENGTH];
    }

    // Construtor
    public KeyValuePair(TKey key, TValue value, int index) throws Exception {
        this(key);
        this.put(value, index);
    }

    // Construtor
    public KeyValuePair(TKey key, TValue[] values) {
        this.key = key;
        this.values = values;
    }

    // Getter para a chave
    public TKey getKey() {
        return key;
    }

    // Getter para os valores
    public TValue[] getValues() {
        return values;
    }

    /**
     * Método para adicionar um valor associado à chave em uma posição específica.
     *
     * @param value representa o valor a ser associado à chave
     * @param index representa a posição da Tabela Hash em que esse valor será inserido
     * @throws Exception se o valor já existir
     * */
    public void put(TValue value, int index) throws Exception {
        if (this.contains(value)) {
            throw new Exception("O valor já está associado à chave!");
        }

        if (index >= 0 && value != null) {
            if (getLoadFactor(values, values.length) > 0.75) {
                resizeToDouble(values.length);
            }

            if (index >= values.length) {
                rehashing(index);
            }

            if (values[index] != null) {
                int increment = 1, originalIndex = index;
                while (values[index] != null) {
                    index = getIndexByHashFunction(key, values.length, increment);
                    increment++;

                    if (originalIndex == index && increment > values.length - 1) {
                        rehashing(increment);
                    }
                }
            }
            values[index] = value;
        }
    }

    /**
     * Método para verificar se um valor existe nos valores associados à chave.
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
        newLength *= 2;
        TValue[] newValues = (TValue[]) new Object[newLength];
        int increment = 1;

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                int index = getIndexByHashFunction(key, newLength);

                while (newValues[index] != null) {
                    index = getIndexByHashFunction(key, newLength, increment);
                    increment++;
                }

                newValues[index] = values[i];
            } else {
                newValues[i] = null;
            }
        }

        values = newValues;
    }

    @Override
    public String toString() {
        return "\nKeyValuePair: {" +
                "Key: " + key +
                ", Length: " + values.length +
                ", Values: " + Arrays.toString(values) +
                '}';
    }
}
