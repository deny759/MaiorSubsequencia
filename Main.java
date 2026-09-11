
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // if(!scanner.hasNext()) {
        //     scanner.close();
        //     return;
        // }

        //Lendo os tamanhos M e N
        System.out.println("Digite o tamanho do 1° vetor: ");
        int M = scanner.nextInt();

        System.out.println("Digite o tamanho do 2° vetor: ");
        int N = scanner.nextInt(); 

        System.out.println("Digite os valores do  1° vetor: ");
        String[] corretoraA = new String[M];
        //Lendo as ações da corretora A
        for (int i = 0; i < M; i++) {
            corretoraA[i] = scanner.next();
        }

        System.out.println("Digite os valores do  2° vetor");
        String[] corretoraB = new String[N];
        //Lendo as ações da corretora B
        for (int i = 0; i < N; i++) {
            corretoraB[i] = scanner.next();
        }

        //Verificando quais ações da corretora A estão na corretora B

        int[][] tabela = new int [M+1][N+1];

        //Percorrer a tabela...

        for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    if(corretoraA[i-1].equals(corretoraB[j-1])){
                        tabela[i][j] = tabela[i-1][j-1] + 1;
                    } else {
                        tabela [i][j] = Math.max(tabela[i-1][j], tabela[i][j-1]);
                    }
                }
        }

        int K = tabela[M][N];
        System.out.println("\n"+K);

        if (K == 0) {
            System.err.println();
            scanner.close();
            return;
        }

        String[] resultado = new String[K];
        int indiceResultado = K - 1;

        int i = M;
        int j = N;

        while (i > 0 && j > 0) {
            if (corretoraA[i-1].equals(corretoraB[j-1])) {
                resultado[indiceResultado] = corretoraA[i-1];
                indiceResultado --;
                i--;
                j--;
            } else if (tabela[i - 1][j] >= tabela[i][j - 1]) {
                i--;
            }   else {
                j--;
            }
        }

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