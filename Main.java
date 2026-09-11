
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        //Lendo os tamanhos M e N
        int M = scanner.nextInt();
        int N = scanner.nextInt(); 


        String[] corretoraA = new String[M];
        //Lendo as ações da corretora A
        for (int i = 0; i < M; i++) {
            corretoraA[i] = scanner.next();
        }

        String[] corretoraB = new String[N];
        //Lendo as ações da corretora B
        for (int i = 0; i < N; i++) {
            corretoraB[i] = scanner.next();
        }


        // PROGRAMAÇÃO DINÂMICA (TABULAÇÃO)
        // Criamos a matriz (M+1) x (N+1). A linha 0 e a coluna 0 representam sequências vazias (zeros).
        int[][] tabela = new int [M+1][N+1];
        //Percorrer a tabela..


        // Construção da solução dos subproblemas
        for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                // Caso 1: Match! As ações coincidem
                // Herdamos o resultado ótimo anterior da diagonal e somamos 1
                if(corretoraA[i-1].equals(corretoraB[j-1])){
                    tabela[i][j] = tabela[i-1][j-1] + 1;
                } 
                // Caso 2: Divergência! As ações são diferentes
                // Herdamos a melhor pontuação acumulada: ou do vizinho de cima, ou da esquerda
                else {
                    tabela [i][j] = Math.max(tabela[i-1][j], tabela[i][j-1]);
               }
            }
        }

        // O tamanho máximo do consenso (K) fica na última célula da tabela
        int K = tabela[M][N];
        System.out.println("\n"+K);

        // Caso especial: se não houver ativos em comum, imprime linha em branco e encerra
        if (K == 0) {
            System.err.println();
            scanner.close();
            return;
        }

        // RECONSTRUÇÃO DO CAMINHO (BACKTRACKING)
        // Vetor para guardar os nomes dos K ativos
        String[] resultado = new String[K];
        int indiceResultado = K - 1; // Preenche de trás para frente

        int i = M;
        int j = N;

        // Rastreamos o caminho de volta a partir de [M][N]
        while (i > 0 && j > 0) {
            if (corretoraA[i-1].equals(corretoraB[j-1])) {
                resultado[indiceResultado] = corretoraA[i-1];
                indiceResultado --;
                i--;
                j--; // Recua na diagonal
            } 
            // Se veio de cima, subimos na tabela
            else if (tabela[i - 1][j] >= tabela[i][j - 1]) {
                i--;
            }   
            // Se veio da esquerda, recuamos a coluna
            else {
                j--;
            }
        }


        // IMPRESSÃO DOS ATIVOS NA ORDEM CORRETA
        for (int idx = 0; idx < K; idx++) {
            System.out.print(resultado[idx]);
            if (idx < K - 1) {
                System.out.print(" ");
            }
        }
        System.err.println("");
        // for (int i = 0; i < M; i++) {
        //     for (int j = 0; j < N; j++) {
        //         if (corretoraA[i].equals(corretoraB[j])) {
                    
                    
        //         }
        //     }
        // }

        scanner.close();

    }
}