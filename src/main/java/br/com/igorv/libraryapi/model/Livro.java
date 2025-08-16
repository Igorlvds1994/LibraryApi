package br.com.igorv.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "autor")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_seq")
    @SequenceGenerator(name = "livro_seq", sequenceName = "livro_sequence", allocationSize = 1)
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

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER que tras junto um livro e autor ja vem por padrao quanto a relação é para um
                                       //LAZY é carregamento lento, só vai carregar se eu estiver dentro de uma transação
                                       // (cascade = CascadeType.ALL) faz operações em cascata
    @JoinColumn(name = "autor_id")
    private Autor autor;


}
