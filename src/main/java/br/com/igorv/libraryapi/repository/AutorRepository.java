package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
