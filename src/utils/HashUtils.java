package utils;

/**
 * HashUtils
 *
 * Possui métodos que serão implementados/utilizados tanto no MultiMap quanto no KeyValuePair a fim de padronizar os comportamentos dessas Tabelas Hash
 *
 * OBS.: alguns métodos deixamos de maneira genérica porque vão ser utilizados nas duas classes
 *
 * */
public abstract class HashUtils {

    /**
     * Rehashing
     *
     * Assinatura do método que redimensiona a tabela hash quando o fator de carga é maior que 0,75 ou caso não haja mais posições disponíveis para inserção de um elemento na tabela
     *
     * @param newLength: novo tamanho (que será dobrado) desejado para a tabela hash após o redimensionamento.
     */
    public abstract void rehashing(int newLength);


    /**
     * GetIndexByHashFunction
     *
     * Método genérico que calcula a Função Hash a partir da chave e do tamanho da tabela a fim de determinar sua posição de inserção/remoção/busca
     *
     * @param key chave a ser utilizada para calcular a posição da tabela
     * @param length tamanho da tabela
     * @return retorna o resultado do cálculo da Função Hash
     * */
    public int getIndexByHashFunction(Object key, int length) {
        return Math.abs(key.hashCode() % length);
    }


    /**
     * GetIndexByHashFunction (Sobrecarga)
     *
     * Versão que possui um parâmetro a mais de incremento, utilizado para tratar de colisões
     *
     * @param os mesmos do método anterior
     * @param increment incremento
     * @return o mesmo do método anterior
     * */
    public int getIndexByHashFunction(Object key, int length, int increment) {
        return Math.abs((key.hashCode() + increment) % length);
    }

    /**
     * GetLoadFactor
     *
     * Método genérico que calcula o Fator de Carga de uma determinada função hash
     *
     * @params o representa a tabela hash
     * @params length representa o tamanho da tabela hash
     * @return retorna o valor resultante da operação de cálculo do Fator de Carga
     * */
    public double getLoadFactor(Object[] o, int length) {
        int sum = 0;
        for (var item: o) {
            if (item != null) {
                sum++;
            }
        }

        return (double) sum / length;
    }


    /**
     * ResizeToDouble
     *
     * Método genérico que faz uma chamada para o Rehashing, a fim de duplicar o tamanho da Tabela Hash
     *
     * @param length representa o tamanho da tabela
     * */
    public void resizeToDouble(int length) {
        rehashing(length);
    }
}
