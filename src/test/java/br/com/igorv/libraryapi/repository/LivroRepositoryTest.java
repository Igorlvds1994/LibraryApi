package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Autor;
import br.com.igorv.libraryapi.model.GeneroLivro;
import br.com.igorv.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}