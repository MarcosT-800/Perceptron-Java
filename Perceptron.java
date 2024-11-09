/*
 * Classe PERCEPTRON responsável pelo aprendizado
 */
public class Perceptron {

    // Pesos sinápticos: [0] para entrada 1, [1] para entrada 2, [2] para o BIAS
    private double[] w = new double[3];

    // Variável responsável pelo somatório (NET)
    private double NET = 0;

    // Número máximo de épocas (iterações de treino)
    private final int epocasMax = 30;

    // Contador de épocas durante o treinamento
    private int count = 0;

    // Matriz de aprendizado que define as entradas e saídas esperadas
    private int[][] matrizAprendizado = new int[4][3];

    // Método de retorno do contador de épocas
    public int getCount() {
        return this.count;
    }

    // Construtor: inicializa o vetor da matriz de aprendizado e os pesos sinápticos
    public Perceptron() {

        // Configura as entradas e saídas esperadas (AND lógico)
        this.matrizAprendizado[0][0] = 0; // entrada 1
        this.matrizAprendizado[0][1] = 0; // entrada 2
        this.matrizAprendizado[0][2] = 0; // valor esperado

        this.matrizAprendizado[1][0] = 0;
        this.matrizAprendizado[1][1] = 1;
        this.matrizAprendizado[1][2] = 0;

        this.matrizAprendizado[2][0] = 1;
        this.matrizAprendizado[2][1] = 0;
        this.matrizAprendizado[2][2] = 0;

        this.matrizAprendizado[3][0] = 1;
        this.matrizAprendizado[3][1] = 1;
        this.matrizAprendizado[3][2] = 1;

        // Inicializa os pesos sinápticos em 0
        w[0] = 0;
        w[1] = 0;
        w[2] = 0;
    }

    // Método responsável pelo somatório e função de ativação
    public int executar(int x1, int x2) {
        // Calcula o somatório (NET)
        NET = (x1 * w[0]) + (x2 * w[1]) + ((-1) * w[2]);

        // Função de ativação (limiar): retorna 1 se NET >= 0, caso contrário 0
        return (NET >= 0) ? 1 : 0;
    }

    // Método para o treinamento da rede
    public void treinar() {
        boolean treinou = true; // Controle de treinamento
        int saida;

        // Faz o treinamento usando cada entrada da matriz de aprendizado
        for (int i = 0; i < matrizAprendizado.length; i++) {
            saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1]);

            // Verifica se a saída gerada é igual à saída esperada
            if (saida != matrizAprendizado[i][2]) {
                // Se for diferente, corrige os pesos
                corrigirPeso(i, saida);
                treinou = false;
            }
        }

        this.count++; // Incrementa a contagem de épocas

        // Repete o treinamento se houver erro e ainda não tiver atingido o número máximo de épocas
        if (!treinou && this.count < this.epocasMax) {
            treinar();
        }
    }

    // Método para a correção de pesos
    private void corrigirPeso(int i, int saida) {
        w[0] = w[0] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]);
        w[1] = w[1] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]);
        w[2] = w[2] + (1 * (matrizAprendizado[i][2] - saida) * (-1));
    }
}


