package br.com.guilherme.tesch.principal;

import br.com.guilherme.tesch.metodos.Livros;

import java.io.*;
import java.util.Scanner;

public class Biblioteca {
    private static final String ARQUIVO = "livros.txt";
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        char opcao;

        do {
            System.out.println("Cadastrar novo livro? (S/N)");
            opcao = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (opcao == 'S'){
                System.out.print("Título: ");
                String titulo = scanner.nextLine();

                System.out.print("Autor: ");
                String autor = scanner.nextLine();

                System.out.print("Páginas: ");
                int paginas = scanner.nextInt();

                System.out.print("Preço: ");
                double preco = scanner.nextDouble();

                System.out.print("Categoria (F - Ficção, N - Não-ficção, T - Tecnoliga, A - Aventura): ");
                char categoria = scanner.next().toUpperCase().charAt(0);

                if ("FNTA".indexOf(categoria) != -1){
                    Livros livro = new Livros(titulo,autor,paginas,preco,categoria);
                    salvarLivro(livro);
                    System.out.println("Livro salvo com sucesso.");
                }else {
                    System.out.println("Categoria inválida! Livro não cadastrado.");
                }
            }
        }while(opcao == 'S');
        System.out.print("\nDeseja ver livros de qual categoria? (F/N/T/A)\n");
        char categoriaConsulta = scanner.next().toUpperCase().charAt(0);
        listarLivrosPorCategoria(categoriaConsulta);

        scanner.close();
    }
    private static void salvarLivro(Livros livro){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO,true))){
            writer.write(livro.toFileFormat());
            writer.newLine();
        }catch (IOException e){
            System.out.println("Erro ao salvar livro: "+ e.getMessage());
        }
    }


    private static void listarLivrosPorCategoria(char categoria){
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()){
            System.out.println("Nenhum livro cadastrado ainda.");
            return;
        }
        System.out.println("\nLivros da categoria " + Livros.getDescricaoCategoria(categoria)+ ":");
        boolean encontrou = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))){
            String linha;
            while ((linha = reader.readLine()) != null){
                Livros livro = Livros.fromFileFormat(linha);
                if (livro.getCategoria() == categoria){
                    System.out.println("- " + livro);
                    encontrou = true;
                }
            }
            if (!encontrou){
                System.out.println("Nenhum livro encontrado nessa categoria.");
            }
        }catch (IOException e){
            System.out.println("Erro ao ler livros: "+ e.getMessage());
        }

    }

}
