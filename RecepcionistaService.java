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
        // Verifica se já existe um recepcionista com o mesmo RA ou email
        if (recepcionistaRepository.findByRa(recepcionista.getRa()).isPresent() ||
            recepcionistaRepository.findByEmail(recepcionista.getEmail()).isPresent()) {
            throw new RuntimeException("RA ou email já cadastrados");
        }

        // Criptografa a senha antes de salvar
        recepcionista.setSenha(passwordEncoder.encode(recepcionista.getSenha()));

        // Salva o recepcionista no banco de dados
        return recepcionistaRepository.save(recepcionista);
    }

    public Recepcionista login(String ra, String senha) {
        // Busca o recepcionista pelo RA
        Recepcionista recepcionista = recepcionistaRepository.findByRa(ra)
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado"));

        // Verifica se a senha está correta
        if (!passwordEncoder.matches(senha, recepcionista.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return recepcionista;
    }
}
