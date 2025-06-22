package br.com.guilherme.tesch.metodos;

import javax.swing.*;

public class Livros {
    private String titulo;
    private String autor;
    private int paginas;
    private double preco;
    private char categoria;

    public Livros(String titulo, String autor, int paginas, double preco, char categoria){
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.preco = preco;
        this.categoria = Character.toUpperCase(categoria);
    }

    public char getCategoria() {
        return categoria;
    }

    public static String getDescricaoCategoria(char categoria){
        return switch (categoria){
            case 'F' -> "Ficção";
            case 'N' -> "Não-ficção";
            case 'T' -> "Tecnologia";
            case 'A' -> "Aventura";
            default -> "Categoria inválida";
        };
    }

    @Override
    public String toString(){
        return String.format("""
                Título: %s
                  Autor: %s
                  Páginas: %d
                  Preço: %.2f
                """, titulo,autor,paginas,preco);
    }

    public String toFileFormat(){
        return titulo + ";" + autor +";" + paginas +";"+preco+";"+categoria;
    }

    public static Livros fromFileFormat(String linha){
        String[] dados = linha.split(";");
        return new Livros(
                dados[0],
                dados[1],
                Integer.parseInt(dados[2]),
                Double.parseDouble(dados[3]),
                dados[4].charAt(0)
        );
    }
}
