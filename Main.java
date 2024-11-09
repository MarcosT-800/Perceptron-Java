public class Main {
    public static void main(String[] args) {
        // Cria uma instancia do Perceptron
        Perceptron perceptron = new Perceptron();
        
        // Treina o perceptron
        perceptron.treinar();
        
        // Exibe o numero de epocas utilizadas no treinamento
        System.out.println("Treinamento concluído em " + perceptron.getCount() + " épocas.");
        
        // Testa o perceptron com entradas diferentes
        int[][] testes = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        
        System.out.println("Resultados dos testes:");
        for (int[] teste : testes) {
            int resultado = perceptron.executar(teste[0], teste[1]);
            System.out.println("Input: (" + teste[0] + ", " + teste[1] + ") -> Output: " + resultado);
        }
    }
}
