package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Autor;
import br.com.igorv.libraryapi.model.GeneroLivro;
import br.com.igorv.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("26743-9812");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Mister");
        livro.setDataPublicacao(LocalDate.of(200,1,2));

        Autor autor = autorRepository.findById(Long.parseLong("2")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);

    }


    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("5757-7567");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("IceCream");
        livro.setDataPublicacao(LocalDate.of(203,2,2));

        Autor autor = new Autor();

        autor.setNome("Bof");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1991, 8,23));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);

    }


    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("5757-7567");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("IceCream");
        livro.setDataPublicacao(LocalDate.of(203,2,2));

        Autor autor = new Autor();

        autor.setNome("Tig");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1991, 8,23));

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void atualizarAutorDoLivro() {
        Long id = Long.parseLong("5");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        Long idAutor = Long.parseLong("2");
        Autor jose = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(jose);

        repository.save(livroParaAtualizar);


    }

    @Test
    void deletar() {
        Long id = Long.parseLong("5");

        //Livro livroParaAtualizar = repository.findById(id).orElse(null);
        repository.deleteById(id); // deleta diretamente pelo id;
    }

    @Test
    void deletarCascade() {
        Long id = Long.parseLong("4");
        var livro = repository.findById(id).orElseThrow();
        repository.delete(livro); // espera um objeto para deletar por isso busca o objeto antes com findById
    }

    @Test
    @Transactional // Abrindo transação para poder carregar os dados a mais que preciso, uma das estrata
    void buscarLivroTeste() {
        Long id = Long.parseLong("1");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: "); // Se tirar o getAutor e getNome ele não tras o Autor não faz outro select ele só tras o Livro
        System.out.println(livro.getAutor().getNome());
    }

}