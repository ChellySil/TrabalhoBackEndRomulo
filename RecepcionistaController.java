import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recepcionistas")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaService recepcionistaService;

    @PostMapping("/cadastrar")
    public Recepcionista cadastrarRecepcionista(@RequestBody Recepcionista recepcionista) {
        return recepcionistaService.cadastrarRecepcionista(recepcionista);
    }

    @PostMapping("/login")
    public Recepcionista login(@RequestParam String ra, @RequestParam String senha) {
        return recepcionistaService.login(ra, senha);
    }
}
