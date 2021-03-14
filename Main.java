import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static long movimentos = 0;

    public static void main(String[] args){
        StringBuilder texto = new StringBuilder("[");

        //---Coloque aqui seu documento .txt (Ex: 2, 42, -23, 31) para ordená-lo---
        int[] vetor = leitura("C:\\Users\\Gustavo\\Desktop\\dadosA.txt");

        //---Inicio da contagem---
        long tempoInicial = System.currentTimeMillis();

        //quickSort(vetor, 0, vetor.length-1);
        //mergeSort(vetor.length, vetor);
        //shellSort(vetor);

        //---fim da contagem---
        long tempoFinal = System.currentTimeMillis();

        System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");

        for (int i = 0; i < vetor.length; i++) {
            texto.append(vetor[i]);
            if (i == vetor.length - 1) {
                texto.append("]");
            } else {
                texto.append(", ");
            }
        }
        System.out.println(texto);
        System.out.println("Total de " + movimentos + " movimentos.");
    }

    //leitura do arquivo a ser ordenado
    public static int[] leitura(String arquivo) {

        Path pasta = Paths.get(arquivo);

        try{
            byte[] text = Files.readAllBytes(pasta);

            String[] ler = new String(text).split(", ");
            int[] vetor = new int[ler.length];

            for(int i = 0; i < ler.length; i++) {
                vetor[i] = Integer.parseInt(ler[i]);
            }

            return vetor;
        }

        catch(Exception erro){
            return null;
        }
    }


    //Algoritmos de Ordenacao

    //QuickSort
    private static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);

        }
    }

        //separa maior e menor do pivor
    private static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo) {
                i++;
            }else if (pivo < vetor[f]) {
                f--;
            }else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
                movimentos++;
            }
            movimentos++;

        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }


    //MergeSort
    private static void mergeSort(int tamanho, int[] vetor) {
        int elementos = 1;
        int inicio, meio, fim;

        while (elementos < tamanho) {
            inicio = 0;

            while (inicio + elementos < tamanho) {
                meio = inicio + elementos;
                fim = inicio + 2 * elementos;

                if (fim > tamanho)
                    fim = tamanho;

                intercala(vetor, inicio, meio, fim);
                inicio = fim;
            }

            elementos = elementos * 2;
        }
    }

        //unifica os vetores
    private static void intercala(int[] vetor, int inicio, int meio, int fim) {
        int[] novoVetor = new int[fim - inicio];
        int i = inicio;
        int m = meio;
        int pos = 0;
        while(i < meio && m < fim) {
            if(vetor[i] <= vetor[m]) {
                novoVetor[pos] = vetor[i];
                pos = pos + 1;
                i = i + 1;
            } else {
                novoVetor[pos] = vetor[m];
                pos = pos + 1;
                m = m + 1;
            }
            movimentos++;

        }

        while(i < meio) {
            novoVetor[pos] = vetor[i];
            pos = pos + 1;
            i = i + 1;
            movimentos++;
        }

        while(m < fim) {
            novoVetor[pos] = vetor[m];
            pos = pos + 1;
            m = m + 1;
            movimentos++;
        }

        for(pos = 0, i = inicio; i < fim; i++, pos++) {
            vetor[i] = novoVetor[pos];
        }
    }


    //ShellSort
    static void shellSort(int[] vetor) {
        int n = vetor.length;
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = vetor[i];
                int j;
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap)
                    vetor[j] = vetor[j - gap];
                movimentos++;
                vetor[j] = temp;
            }
        }
    }
}
