package br.com.guilherme.tesch.principal;

import br.com.guilherme.tesch.metodos.Livros;


import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char opcao;
        char continuarConsulta;
        
        do {
            System.out.println("Cadastrar novo livro? (S/N)");
            opcao = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (opcao == 'S') {
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

                if ("FNTA".indexOf(categoria) != -1) {
                    Livros livro = new Livros(titulo, autor, paginas, preco, categoria);
                    Livros.salvarLivro(livro);
                    System.out.println("Livro salvo com sucesso.");
                } else {
                    System.out.println("Categoria inválida! Livro não cadastrado.");
                }
                scanner.nextLine();
            }
        } while (opcao == 'S');
        do{
            System.out.print("\nDeseja ver livros de qual categoria? (F/N/T/A)\n");
            char categoriaConsulta = scanner.next().toUpperCase().charAt(0);
            Livros.listarLivrosPorCategoria(categoriaConsulta);

            System.out.println("Deseja consultar outra categoria? (S/N)");
            continuarConsulta = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();
        } while (continuarConsulta == 'S');

        System.out.println("Programa finalizado.");

        scanner.close();
    }

}
