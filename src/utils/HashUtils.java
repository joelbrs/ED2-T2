package utils;

public abstract class HashUtils<TKey> {
    /**
    *
    * Possui métodos que serão implementados/utilizados tanto no MultiMap quanto no KeyValuePair
    *
    * OBS.: alguns métodos deixamos de maneira genérica porque vão ser utilizados nas duas classes
    *
    * */


    /**
    * Assinatura do método de rehashing, que será utilizado quando:
    *   1 - o fator de carga das tabelas hash for superior a 0,75
    *   2 - quando percorrermos toda a tabela e ainda assim não tivermos espaço para adicionar na tabela
    *
    * Basicamente, a implementação vai consistir em:
    *   1 - aumentar o tamanho da tabela hash no dobro do que foi passado no parâmetro do método:
    *   2 - calcular as novas posições dos elementos presentes na tabela anterior
    * */
    public abstract void rehashing(int newLength);


    /**
    * Implementação genérica do método p/ calcular a Função Hash
    * */
    public int getIndexByHashFunction(TKey key, int length) {
        return Math.abs(key.hashCode() % length);
    }


    /**
    * Implementação genérica do método p/ calcular a Função Hash com um incremento (usado p/ tratar colisões)
    * */
    public int getIndexByHashFunction(TKey key, int length, int increment) {
        return Math.abs((key.hashCode() + increment) % length);
    }


    /**
    * Implementação Genérica do cálculo do Fator de Carga da Tabela Hash
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
    * Implementação genérica mais semântica de um redimensionamento do dobro de tamanho da Tabela Hash
    * */
    public void resizeToDouble(int length) {
        rehashing(length);
    }
}
