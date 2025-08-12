package br.com.igorv.libraryapi.repository;

import br.com.igorv.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long > {
}
