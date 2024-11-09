// Classe PerceptronCarro para recomendação de carros com base em características

/* O Perceptron é um modelo básico de rede neural utilizado para tarefas
 de classificação binária. Ele foi proposto por Frank Rosenblatt em 1958
  e é considerado um dos primeiros algoritmos de aprendizado de máquina.
 */

public class PerceptronCarro {

    // Pesos sinápticos para cada característica (inicializados com valores aleatórios)
    private double[] w = {Math.random(), Math.random(), Math.random(), Math.random(), Math.random()};

    // Taxa de aprendizagem define a magnitude do ajuste dos pesos após cada erro
    private final double taxaAprendizagem = 0.1;

    // Número máximo de épocas (iterações de treino)
    private final int epocasMax = 100;

    // Dados de carros (exemplo): Nome dos carros e suas características
    private String[] carros = {"Chevrolet Tracker", "Chevrolet Onix Plus", "Honda Civic", "Toyota Corolla", "Jeep Renegade", "Ford Ka", "Hyundai Creta"};
   
   
      /* 'Espaço',
        'Economia',
        'Potência',
        'Conforto',  */
  
    private int[][] caracteristicas = {
            {8, 6, 7, 7},
            {6, 9, 5, 6},
            {7, 7, 8, 8},
            {7, 8, 7, 8},
            {9, 5, 9, 9},
            {5, 8, 6, 5},
            {8, 6, 7, 7}
    };

    // Função de ativação (limiar): retorna 1 para valores NET >= 0 e 0 caso contrário
    private int ativacao(double net) {
        return (net >= 0) ? 1 : 0;
    }

    // Função de cálculo do somatório (NET) para uma entrada específica
    private double calcularNet(int[] entradas) {
        double net = 0;
        for (int i = 0; i < entradas.length; i++) {
            net += entradas[i] * w[i];
        }
        return net;
    }

    // Treinamento do Perceptron para ajustar os pesos com base nas preferências esperadas
    public void treinar(int[] preferencias, int carroEsperado) {
        for (int epoca = 0; epoca < epocasMax; epoca++) {
            for (int i = 0; i < caracteristicas.length; i++) {
                int[] carro = caracteristicas[i];
                int saida = ativacao(calcularNet(carro));
                int erro = (i == carroEsperado ? 1 : 0) - saida;

                if (erro != 0) {
                    for (int j = 0; j < w.length - 1; j++) {
                        w[j] += taxaAprendizagem * erro * carro[j];
                    }
                    w[w.length - 1] += taxaAprendizagem * erro * (-1);
                }
            }
        }
    }

    // Função para recomendar o carro mais adequado com base nas preferências do cliente
    public String recomendarCarro(int[] preferencias) {
        double maiorNet = Double.NEGATIVE_INFINITY;
        int indiceCarroRecomendado = -1;

        for (int i = 0; i < caracteristicas.length; i++) {
            int[] carro = caracteristicas[i];
            double net = calcularNet(carro);
            if (net > maiorNet) {
                maiorNet = net;
                indiceCarroRecomendado = i;
            }
        }
        return carros[indiceCarroRecomendado];
    }

    // Função para calcular a precisão do perceptron
    public double calcularPrecisao(int[][] dadosTeste, int carroEsperado) {
        int acertos = 0;
        for (int[] preferencia : dadosTeste) {
            String carroRecomendado = recomendarCarro(preferencia);
            if (carroRecomendado.equals(carros[carroEsperado])) {
                acertos++;
            }
        }
        return (double) acertos / dadosTeste.length * 100;
    }

    public static void main(String[] args) {
        PerceptronCarro perceptron = new PerceptronCarro();

        // Preferências do cliente (Espaço, Economia, Potência, Conforto)
        int[] preferenciasCliente = {8, 5, 6, 9};

        // Treinamento do Perceptron com base nas características desejadas
        perceptron.treinar(preferenciasCliente, 4); // Índice 4 refere-se ao Jeep Renegade

        // Recomenda o carro mais adequado com base nas preferências fornecidas
        String carroRecomendado = perceptron.recomendarCarro(preferenciasCliente);
        System.out.println("Carro recomendado para as preferências do cliente: " + carroRecomendado);

        // Calcula e exibe a precisão do modelo
        int[][] dadosTeste = {preferenciasCliente}; // Exemplo de dados de teste (poderia ser uma lista maior)
        double precisao = perceptron.calcularPrecisao(dadosTeste, 4);
        System.out.println("Precisão do modelo: " + precisao + "%");
    }
}
