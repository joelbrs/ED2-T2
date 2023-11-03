package structures.maps;

import structures.auxiliars.KeyValuePair;
import utils.HashUtils;
import utils.HashTableControl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiMap<TKey, TValue> extends HashUtils<TKey> {

    //Lista de pares de chave-valor (representação da Hash Table)
    private KeyValuePair[] pairs;


    //Tamanho da Hash Table com o padrão especificado pela variável DEFAULT_LENGTH da classe de controle HashTableControl
    private Integer length = HashTableControl.DEFAULT_LENGTH;

    public MultiMap() {
        this.pairs = new KeyValuePair[length];
    }

    public MultiMap(int length) {
        this.length = length;
        this.pairs = new KeyValuePair[length];
    }

    /**
     * Put
     * Insere um valor associado a uma chave no MultiMapa
     *
     * Requisitos:
     *   1 - Uma chave pode ter vários valores
     *   2 - Uma chave não pode ter valores repetidos
     *   3 - Não pode haver chaves repetidas
     *
     * @param key A chave à qual o valor será associado
     * @param value O valor a ser associado à chave
     * @throws Exception Se a chave ou o valor fornecidos forem nulos
     */
    public void put(TKey key, TValue value) throws Exception {
        if (key != null && value != null) {
            /*
             * index: variável que determina a posição inicial de inserção do valor na tabela
             * increment: variável responsável pelo incremento (quando há colisão, utilizamos a abordagem de sondagem linear)
             * originalIndex: variável que guarda o mesmo valor da posição inicial de inserção do valor na tabela, utilizamos para controle
             *
             * */
            int index = getIndexByHashFunction(key, length), increment = 1, originalIndex = index;

            /*
             * verifica se o fator de carga da Hash Table atual está acima de 0,75 para efetuar o redimensionamento
             * para o dobro de tamanho a fim de diminuir possíveis colisões
             * */
            if (getLoadFactor(pairs, length) > 0.75) {
                resizeToDouble(length);
            }

            //verificando se na posição calculada já existe um elemento
            if (pairs[index] != null) {

                /*
                 * Percorrendo a tabela hash até encontrar uma posição válida para inserir o novo par de chave-valor, ou para inserir
                 * o elemento na chave já existente
                 * */
                while (pairs[index] != null && !pairs[index].getKey().equals(key)) {
                    index = getIndexByHashFunction(key, length, increment);
                    increment++;

                    /*
                     * Verificando se a posição atual (posição inicial incrementada) é diferente da posição inicial (em outros termos, se já passamos por todos os elementos
                     * da Tabela Hash e, ainda assim, não encontramos espaço disponível), então, redimensionamos a Tabela Hash
                     */
                    if (originalIndex == index && increment > length - 1) {
                        rehashing(increment);
                    }
                }

                //Verificando se existe alguma chave na posição encontrada
                if (pairs[index] != null) {
                    pairs[index].put(value, index);
                    return;
                }
            }

            /* caso não tenha elemento inserido na posição inicial de inserção, criamos um novo par de chave-valor,
             * inserindo o valor em sua lista de valores
             * **/
            pairs[index] = new KeyValuePair(key, value, index);
            return;
        }
        throw new IllegalArgumentException("A chave e o valor não podem ser nulos!");
    }


    /**
     * FindAll
     *
     * Busca todos os valores de uma chave específica
     *
     * O método retorna uma lista de todos os valores associados à chave fornecida
     *
     * @param key: A chave que os valores estão associados
     * @return Uma lista de valores associados à chave
     * @throws Exception Se a chave fornecida for nula ou se a chave não existir na tabela
     */
    public List<TValue> findAll(TKey key) throws Exception {
        if (key != null) {
            // Obtendo o índice correspondente à chave
            int index = getIndexByHashFunction(key, length), increment = 1;

            if (pairs[index] != null) {
                // Iterando até encontrar a chave ou uma posição vazia
                while (pairs[index] != null && !pairs[index].getKey().equals(key)) {
                    index = getIndexByHashFunction(key, length, increment);
                    increment++;
                }

                if (pairs[index] != null) {
                    // Filtrando valores não nulos e retornando como lista
                    return (List<TValue>) pairs[index].getValues().stream().filter(v -> v != null).collect(Collectors.toList());
                }
            }
            throw new Exception("Essa chave não existe!");
        }
        throw new IllegalArgumentException("A chave não pode ser nula!");
    }


    /**
     * Rehashing
     *
     * Esse método redimensiona a tabela hash quando o fator de carga é maior que 0,75 ou caso não haja mais posições disponíveis para inserção de um elemento na tabela
     *
     * @param newLength: novo tamanho (que será dobrado) desejado para a tabela hash após o redimensionamento.
     */
    @Override
    public void rehashing(int newLength) {
        // Dobrando o tamanho para garantir espaço suficiente
        newLength *= 2;

        // Criando um novo array para armazenar os pares chave-valor
        KeyValuePair[] newPairs = new KeyValuePair[newLength];
        int increment = 1;

        // Iterando sobre os pares na tabela hash original
        for (int i = 0; i < length; i++) {
            if (pairs[i] != null) {
                // Calculando o novo índice para o par chave-valor
                int index = getIndexByHashFunction((TKey) pairs[i].getKey(), newLength);

                // Tratando colisões usando sondagem linear
                while (newPairs[index] != null) {
                    index = getIndexByHashFunction((TKey) pairs[i].getKey(), newLength, increment);
                    increment++;
                }

                // Adicionando o par chave-valor para a nova tabela hash
                newPairs[index] = new KeyValuePair(pairs[i].getKey(), pairs[i].getValues());
            } else {
                // Mantendo posições vazias na nova tabela
                newPairs[i] = null;
            }
        }

        // Atualizando o tamanho e os pares da tabela hash original
        length = newLength;
        pairs = newPairs;
    }


    @Override
    public String toString() {
        return  "MultiMap: {"
                + "Length: " + length
                + ", Pairs: " + Arrays.toString(pairs)
                + '}';
    }
}
