package br.com.guilherme.tesch.metodos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;




public class Livros {
    private String titulo;
    private String autor;
    private int paginas;
    private double preco;
    private char categoria;
    private static final String ARQUIVO = "livros.txt";

    public Livros(String titulo, String autor, int paginas, double preco, char categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.preco = preco;
        this.categoria = Character.toUpperCase(categoria);
    }

    public char getCategoria() {
        return categoria;
    }

    public static String getDescricaoCategoria(char categoria) {
        return switch (categoria) {
            case 'F' -> "Ficção";
            case 'N' -> "Não-ficção";
            case 'T' -> "Tecnologia";
            case 'A' -> "Aventura";
            default -> "Categoria inválida";
        };
    }

    @Override
    public String toString() {
        return String.format("""
                Título: %s
                  Autor: %s
                  Páginas: %d
                  Preço: %.2f
                """, titulo, autor, paginas, preco);
    }

    public String toFileFormat() {
        return titulo + ";" + autor + ";" + paginas + ";" + preco + ";" + categoria;
    }

    public static Livros fromFileFormat(String linha) {
        String[] dados = linha.split(";");
        return new Livros(
                dados[0],
                dados[1],
                Integer.parseInt(dados[2]),
                Double.parseDouble(dados[3]),
                dados[4].charAt(0));
    }

    public static void salvarLivro(Livros livro) {
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(ARQUIVO, true), 
                StandardCharsets.UTF_8
                )
        )) {
            writer.write(livro.toFileFormat());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    public static void listarLivrosPorCategoria(char categoria) {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Nenhum livro cadastrado ainda.");
            return;
        }
        System.out.println("\nLivros da categoria " + Livros.getDescricaoCategoria(categoria) + ":");
        boolean encontrou = false;

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(ARQUIVO),
                StandardCharsets.UTF_8
                )
        )) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Livros livro = Livros.fromFileFormat(linha);
                if (livro.getCategoria() == categoria) {
                    System.out.println("- " + livro);
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("Nenhum livro encontrado nessa categoria.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler livros: " + e.getMessage());
        }

    }
}
