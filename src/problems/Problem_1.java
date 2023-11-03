package problems;

import structures.maps.MultiMap;

import java.util.Random;

public class Problem_1 {
    public static void main(String[] args) throws Exception {
        /*
         *
         *   Implemente uma estrutura de dados que permite que vários valores sejam associados à
         *   mesma chave. Essa estrutura é chamada de multimapa. Ela deve ter um método put (k, v),
         *   que insere um item com a chave k e valor v mesmo se já houver um item com a chave k (mas
         *   não o mesmo par de valor-chave).
         *
         * */

        /*
         *
         * Exemplo 1: put (“a”, 1), put (“b”, 2), put (“a”, 3), put (“c”, 4), put (“b”, 5), put (“a”, 6)
         * Espera-se que o multimapa contenha os seguintes itens: (“a”, 1), (“b”, 2), (“a”, 3), (“c”, 4), (“b”, 5), (“a”, 6)
         * Espera-se que o multimapa mantenha a ordem de inserção dos itens com a mesma chave: “a” -> [1, 3, 6], “b” -> [2, 5], “c” -> [4]
         * Espera-se que o multimapa permita recuperar todos os valores associados a uma chave: get (“a”) -> [1, 3, 6], get (“b”) -> [2, 5], get (“c”) -> [4], get (“d”) -> null
         *
         * */

        MultiMap<String, Integer> multiMap = new MultiMap<>(50);
        Random random = new Random();

        for (int i = 1; i <= 8; i++) {
            String chave = "chave" + i;
            for (int j = 1; j <= 10; j++) {
                int valor = random.nextInt(100); // Gera um número aleatório entre 0 e 99
                try {
                    multiMap.put(chave, valor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println(multiMap);
    }
}
