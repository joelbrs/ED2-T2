package problems;

import structures.maps.MultiMap;

import java.util.Arrays;
import java.util.Random;

public class Problem_1 {
    public static void main(String[] args) throws Exception {
        /**
         * Implemente uma estrutura de dados que permite que vários valores sejam associados à
         * mesma chave. Essa estrutura é chamada de multimapa. Ela deve ter um método put (k, v),
         * que insere um item com a chave k e valor v mesmo se já houver um item com a chave k (mas
         * não o mesmo par de valor-chave) e um método FindAll (k), que retorna todos os valores que
         * possuem a chave k. Sua estrutura deve executar o método put (k, v) no tempo O (1) e o
         * método FindAll (k) seja executado no tempo O (1 + v), onde v é o número de valores com
         * chave k. OBS: Você só pode utilizar as estruturas estudadas na segunda unidade (Hash e
         * Árvores).
         */

        MultiMap<String, Integer> multiMap = new MultiMap<>();
        Random random = new Random();

        for (int i = 1; i <= 8; i++) {
            String chave = "chave" + i;
            for (int j = 1; j <= 6; j++) {
                int valor = random.nextInt(1000); // Gera um número aleatório entre 0 e 999
                try {
                    multiMap.put(chave, valor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(multiMap);
        System.out.println("Key: chave1 ->" + Arrays.toString(multiMap.findAll("chave1")));
        System.out.println("Key: chave5 ->" + Arrays.toString(multiMap.findAll("chave5")));
    }
}
