public class App {
    
    // FIBO-REC (Recursivo)
    public static int fiboRec(int n) {
        if (n <= 1) {
            return n;
        } else {
            int a = fiboRec(n - 1);
            int b = fiboRec(n - 2);
            return a + b;
        }
    }

    // FIBO (Iterativo)
    public static int fibo(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    // MEMOIZED-FIBO
    public static int memoizedFibo(int n) {
        int[] f = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            f[i] = -1;
        }
        return lookupFibo(f, n);
    }

    public static int lookupFibo(int[] f, int n) {
        if (f[n] >= 0) {
            return f[n];
        }
        if (n <= 1) {
            f[n] = n;
        } else {
            f[n] = lookupFibo(f, n - 1) + lookupFibo(f, n - 2);
        }
        return f[n];
    }

    // Contagem de iterações para Fibo Iterativo
    public static int countIterationsFibo(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        int iterations = 0;
        for (int i = 2; i <= n; i++) {
            iterations++;
            f[i] = f[i - 1] + f[i - 2];
        }
        return iterations;
    }

    // Contagem de iterações para Memoized Fibo
    public static int countIterationsMemoizedFibo(int n) {
        int[] f = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            f[i] = -1;
        }
        return lookupFiboAndCount(f, n);
    }

    private static int lookupFiboAndCount(int[] f, int n) {
        if (f[n] >= 0) {
            return f[n];
        }
        if (n <= 1) {
            f[n] = n;
        } else {
            f[n] = lookupFiboAndCount(f, n - 1) + lookupFiboAndCount(f, n - 2);
        }
        return f[n];
    }

    class Item {
        int valor;
        int peso;

        public Item(int valor, int peso) {
            this.valor = valor;
            this.peso = peso;
        }
    }

    public static int knapSackRecursivo(int c, int peso[], int valor[], int n, int[] iteracoes) {
        iteracoes[0]++;
        if (n == 0 || c == 0) {
            return 0;
        }
        if (peso[n - 1] > c) {
            return knapSackRecursivo(c, peso, valor, n - 1, iteracoes);
        } else {
            return Math.max(valor[n - 1] + knapSackRecursivo(c - peso[n - 1], peso, valor, n - 1, iteracoes), knapSackRecursivo(c, peso, valor, n - 1, iteracoes));
        }
    }

    public static int knapSackDinamico(int c, int peso[], int valor[], int n, int[] iteracoes) {
        int[][] maxTab = new int[n + 1][c + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= c; w++) {
                iteracoes[0]++;
                if (i == 0 || w == 0) {
                    maxTab[i][w] = 0;
                } else if (peso[i - 1] <= w) {
                    maxTab[i][w] = Math.max(valor[i - 1] + maxTab[i - 1][w - peso[i - 1]], maxTab[i - 1][w]);
                } else {
                    maxTab[i][w] = maxTab[i - 1][w];
                }
            }
        }
        return maxTab[n][c];
    }

    public static int editDistanceRec(String s1, String s2, int i, int j, int[] memo, int[] iterationsRec) {
        iterationsRec[0]++;

        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }

        int index = i * (s2.length() + 1) + j;

        if (memo[index] != -1) {
            return memo[index];
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            memo[index] = editDistanceRec(s1, s2, i - 1, j - 1, memo, iterationsRec);
        } else {
            int insert = editDistanceRec(s1, s2, i, j - 1, memo, iterationsRec);
            int remove = editDistanceRec(s1, s2, i - 1, j, memo, iterationsRec);
            int replace = editDistanceRec(s1, s2, i - 1, j - 1, memo, iterationsRec);

            memo[index] = Math.min(Math.min(insert, remove), replace) + 1;
        }

        return memo[index];
    }

    public static int editDistanceDinamico(String s1, String s2, int[] iterationsDP) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
                iterationsDP[0]++;
            }
        }
        return dp[m][n];
    }

    public static String getStringSummary(String s) {
        return s.length() > 30 ? s.substring(0, 30) + "..." : s; // Exibe os primeiros 50 caracteres, ou a string inteira se for menor
    }

    public static void main(String[] args) {
        // int[] testValues = {4, 8, 16, 32, 128, 1000, 10000};

        // System.out.println("------------------------------------------------------------------------------------------------------------");
        // System.out.printf("%-15s%-20s%-20s%-20s%-20s\n", "Fibonacci", "Value", "Result", "Iterations", "Execution Time");
        // System.out.println("------------------------------------------------------------------------------------------------------------");
        // for (int n : testValues) {
        //     // Fibo Iterativo
        //     long startTime = System.nanoTime();
        //     int resultFibo = fibo(n);
        //     long endTime = System.nanoTime();
        //     long durationFibo = endTime - startTime;
        //     int iterationsFibo = countIterationsFibo(n);
        //     System.out.printf("%-15s%-20d%-20d%-20d%-20d ns\n", "Fibo Iterativo", n, resultFibo, iterationsFibo, durationFibo);

        //     // Memoized Fibo
        //     startTime = System.nanoTime();
        //     int resultMemoizedFibo = memoizedFibo(n);
        //     endTime = System.nanoTime();
        //     long durationMemoizedFibo = endTime - startTime;
        //     int iterationsMemoizedFibo = countIterationsMemoizedFibo(n);
        //     System.out.printf("%-15s%-20d%-20d%-20d%-20d ns\n", "Memoized Fibo", n, resultMemoizedFibo, iterationsMemoizedFibo, durationMemoizedFibo);

        //     // Fibo Recursivo
        //     if (n <= 32) {
        //         startTime = System.nanoTime();
        //         int resultFiboRec = fiboRec(n);
        //         endTime = System.nanoTime();
        //         long durationFiboRec = endTime - startTime;
        //         System.out.printf("%-15s%-20d%-20d%-20s%-20d ns\n", "Fibo Recursivo", n, resultFiboRec, "-", durationFiboRec);
        //     }
        //     System.out.println("------------------------------------------------------------------------------------------------------------");
        // }
        // System.out.println("------------------------------------------------------------------------------------------------------------");
        
        // int valor[] = {60, 60, 100, 120};
        // int peso[] = {10, 10, 20, 30};
        // int c = 50;
        // int n = valor.length;

        // int[] iteracoesRecursivo = {0};
        // int[] iteracoesDinamico = {0};

        // long startTimeRecursivo = System.nanoTime();
        // int resultadoRecursivo = knapSackRecursivo(c, peso, valor, n, iteracoesRecursivo);
        // long endTimeRecursivo = System.nanoTime();
        // long tempoExecucaoRecursivo = endTimeRecursivo - startTimeRecursivo;

        // long startTimeDinamico = System.nanoTime();
        // int resultadoDinamico = knapSackDinamico(c, peso, valor, n, iteracoesDinamico);
        // long endTimeDinamico = System.nanoTime();
        // long tempoExecucaoDinamico = endTimeDinamico - startTimeDinamico;

        // System.out.println("----------------------------------------------------------------------------------------");
        // System.out.printf("%-20s%-20s%-20s%-20s\n", "Algoritmo", "Valor Máximo", "Iterações", "Tempo de Execução (ns)");
        // System.out.println("----------------------------------------------------------------------------------------");

        // System.out.printf("%-20s%-20d%-20d%-20d\n", "Knapsack Recursivo", resultadoRecursivo, iteracoesRecursivo[0], tempoExecucaoRecursivo);
        // System.out.printf("%-20s%-20d%-20d%-20d\n", "Knapsack Dinâmico", resultadoDinamico, iteracoesDinamico[0], tempoExecucaoDinamico);

        // System.out.println("----------------------------------------------------------------------------------------");
    
        // String s1 = "Casablanca";
        // String s2 = "Portentoso";

        // int[] memo = new int[(s1.length() + 1) * (s2.length() + 1)];
        // for (int i = 0; i < memo.length; i++) {
        //     memo[i] = -1;
        // }

        // int[] iterationsRec = new int[1];
        // iterationsRec[0] = 0;

        // int resultRec = editDistanceRec(s1, s2, s1.length(), s2.length(), memo, iterationsRec);
        // System.out.println("Distância de Edição (Recursiva com Memoização) entre '" + s1 + "' e '" + s2 + "': " + resultRec);
        // System.out.println("Número de iterações (Recursivo com Memoização): " + iterationsRec[0]);

        // int[] iterationsDP = new int[1];
        // iterationsDP[0] = 0;

        // int resultDP = editDistanceDinamico(s1, s2, iterationsDP);
        // System.out.println("Distância de Edição (Programação Dinâmica) entre '" + s1 + "' e '" + s2 + "': " + resultDP);
        // System.out.println("Número de iterações (Programação Dinâmica): " + iterationsDP[0]);

        // String s3 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to simplify the build processes in the Jakarta Turbine project. There were several projects, each with their own Ant build files, that were all slightly different. JARs were checked into CVS. We wanted a standard way to build the projects, a clear definition of what the project consisted of, an easy way to publish project information and a way to share JARs across several projects. The result is a tool that can now be used for building and managing any Java-based project. We hope that we have created something that will make the day-to-day work of Java developers easier and generally help with the comprehension of any Java-based project.";
        // String s4 = "This post is not about deep learning. But it could be might as well. This is the power of kernels. They are universally applicable in any machine learning algorithm. Why you might ask? I am going to try to answer this question in this article. Go to the profile of Marin Vlastelica Pogančić Marin Vlastelica Pogančić Jun";

        // memo = new int[(s3.length() + 1) * (s4.length() + 1)];
        // for (int i = 0; i < memo.length; i++) {
        //     memo[i] = -1;
        // }

        // iterationsRec[0] = 0;

        // int resultRec2 = editDistanceRec(s3, s4, s3.length(), s4.length(), memo, iterationsRec);
        // System.out.println("Distância de Edição (Recursiva com Memoização) entre s3 e s4: " + resultRec2);
        // System.out.println("Número de iterações (Recursivo com Memoização): " + iterationsRec[0]);

        // iterationsDP[0] = 0;

        // int resultDP2 = editDistanceDinamico(s3, s4, iterationsDP);
        // System.out.println("Distância de Edição (Programação Dinâmica) entre s3 e s4: " + resultDP2);
        // System.out.println("Número de iterações (Programação Dinâmica): " + iterationsDP[0]);

        String s1 = "Casablanca";
        String s2 = "Portentoso";
        String s3 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to simplify the build processes in the Jakarta Turbine project. There were several projects, each with their own Ant build files, that were all slightly different. JARs were checked into CVS. We wanted a standard way to build the projects, a clear definition of what the project consisted of, an easy way to publish project information and a way to share JARs across several projects. The result is a tool that can now be used for building and managing any Java-based project. We hope that we have created something that will make the day-to-day work of Java developers easier and generally help with the comprehension of any Java-based project.";
        String s4 = "This post is not about deep learning. But it could be might as well. This is the power of kernels. They are universally applicable in any machine learning algorithm. Why you might ask? I am going to try to answer this question in this article. Go to the profile of Marin Vlastelica Pogančić Marin Vlastelica Pogančić Jun";

        // Memoization - Distância de edição Recursiva
        int[] memo = new int[(s1.length() + 1) * (s2.length() + 1)];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        int[] iterationsRec = new int[1];
        iterationsRec[0] = 0;
        int resultRec = editDistanceRec(s1, s2, s1.length(), s2.length(), memo, iterationsRec);

        // Programação Dinâmica
        int[] iterationsDP = new int[1];
        iterationsDP[0] = 0;
        int resultDP = editDistanceDinamico(s1, s2, iterationsDP);

        // Distância de Edição Recursiva
        memo = new int[(s3.length() + 1) * (s4.length() + 1)];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        iterationsRec[0] = 0;
        int resultRec2 = editDistanceRec(s3, s4, s3.length(), s4.length(), memo, iterationsRec);

        // Programação Dinâmica
        iterationsDP[0] = 0;
        int resultDP2 = editDistanceDinamico(s3, s4, iterationsDP);

        // Print Table Header
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-20s%-20s%-20s\n", "Method", "Distance", "Iterations", "String Pair");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Print Recursivo + Memoization Results
        System.out.printf("%-30s%-20d%-20d%-20s\n", "Recursivo com Memoização", resultRec, iterationsRec[0], getStringSummary(s1 + " & " + s2));
        System.out.printf("%-30s%-20d%-20d%-20s\n", "Recursivo com Memoização", resultRec2, iterationsRec[0], getStringSummary(s3 + " & " + s4));

        // Print Programação Dinâmica Results
        System.out.printf("%-30s%-20d%-20d%-20s\n", "Programação Dinâmica", resultDP, iterationsDP[0], getStringSummary(s1 + " & " + s2));
        System.out.printf("%-30s%-20d%-20d%-20s\n", "Programação Dinâmica", resultDP2, iterationsDP[0], getStringSummary(s3 + " & " + s4));

        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
}