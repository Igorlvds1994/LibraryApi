package br.com.igorv.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "livro")
@Data // Contem a @Getter @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsContructor entre outras
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "autor_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero" , length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;
    //private BigDecimal preco; mais preciso

    @ManyToOne  //muitos livros para um autor
    @JoinColumn(name = "autor_id")
    private Autor autor;


}
