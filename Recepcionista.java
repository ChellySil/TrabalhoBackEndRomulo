import javax.persistence.*;
import java.util.List;

@Entity
public class Recepcionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String ra;

    @Column(nullable = false)
    private String senha;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "recepcionista", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    // getters e setters
}
