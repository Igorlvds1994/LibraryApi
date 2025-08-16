package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Autor;
import br.com.igorv.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long > {

    // Query Method trazendo os Livros do autor
    // select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);
}
