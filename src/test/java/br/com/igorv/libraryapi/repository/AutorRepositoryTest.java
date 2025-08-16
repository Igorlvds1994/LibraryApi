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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();

        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1994, 10,20));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo : " + autorSalvo);

    }

    @Test
    @Transactional // Se não colocar Transactional(a transação fica aberta) Da erro LazyInitializartion por que por padrão esta Lazy
    public void atualizarTest() { // Quando é LAZY preciso dizer que quero trazer ou coloca transactional

        Long id = Long.parseLong("1");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960,1,30));

             repository.save(autorEncontrado);



        }

    }

    @Test
    @Transactional
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }
    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletarPorIdTest() {
        Long id = Long.parseLong("10");
        repository.deleteById(id);

    }

    @Test
    public void deletarPorObjTest() {
        Long id = Long.parseLong("1");
        var jose = repository.findById(id).get();
        repository.delete(jose);

    }

    @Test
    void salvarAutorComLivrosTest() {

        Autor autor = new Autor();
        autor.setNome("Rile");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1992, 03,12));

        Livro livro = new Livro();
        livro.setIsbn("1297-3671");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Sambari Love");
        livro.setDataPublicacao(LocalDate.of(2000,4,20));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("4567-3671");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setTitulo("Hearts");
        livro2.setDataPublicacao(LocalDate.of(2003,4,20));
        livro2.setAutor(autor);


        autor.setLivros( new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    // @Transactional
    void listarLivrosAutor() {

        Long id = Long.parseLong("23");
        var autor = repository.findById(id).get();
        // Buscar os livros do autor carregando apropriadamente sem o @Transactional
        // Forma correta é essa buscar os livros só quando eu quiser e nao usar EAGER
        // Sempre trabalhar com LAZY

         List<Livro> livrosLista = livroRepository.findByAutor(autor);
         autor.setLivros(livrosLista);

         autor.getLivros().forEach(System.out::println);

    }




}
