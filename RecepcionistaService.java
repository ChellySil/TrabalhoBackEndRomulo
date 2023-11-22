import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Recepcionista cadastrarRecepcionista(Recepcionista recepcionista) {
        if (recepcionistaRepository.findByRa(recepcionista.getRa()).isPresent() ||
            recepcionistaRepository.findByEmail(recepcionista.getEmail()).isPresent()) {
            throw new RuntimeException("RA ou email já cadastrados");
        }

        recepcionista.setSenha(passwordEncoder.encode(recepcionista.getSenha()));

        return recepcionistaRepository.save(recepcionista);
    }

    public Recepcionista login(String ra, String senha) {
        Recepcionista recepcionista = recepcionistaRepository.findByRa(ra)
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado"));

        if (!passwordEncoder.matches(senha, recepcionista.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return recepcionista;
    }
}
