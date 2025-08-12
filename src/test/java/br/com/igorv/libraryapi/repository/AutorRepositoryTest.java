package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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
    @Transactional
    public void atualizarTest() {

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


}
