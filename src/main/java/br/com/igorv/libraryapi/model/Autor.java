package br.com.igorv.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Getter
@Setter
@ToString(exclude = "livros")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "autor_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento",nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade" , length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY) // Poderia colocar cascade = CacasdeType.ALL para quando persistir um Autor persistir os livros junto
    private List<Livro> livros;    // Não precisaria do salveAll().
                                   // Por padrao esta FetchType.LAZY) para ToMany (para muitos)
                                  // Se colocar TechType.EAGER ele vai trazer com Autor todos seus livros é capaz que limita a aplicação e pesa

    @Deprecated
    public Autor() {

    }

    public Autor(Long id, String nome, String nacionalidade, LocalDate dataNascimento, List<Livro> livros) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.livros = livros;
    }
}
