package mg.ando.codage.sardinas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.sardinas.service.SardinasService;

@RestController
@RequestMapping("/api/sardinas")
public class SardinasController {

    private final SardinasService sardinasService;

    public SardinasController(SardinasService sardinasService) {
        this.sardinasService = sardinasService;
    }

    @PostMapping("/check-if-Code")
    public ResponseEntity<?> checkUniqueDecodability(@RequestBody Alphabet alphabet) {
        try {
            boolean isUniquelyDecodable = sardinasService.checkUniquelyDecodable(alphabet);
            return ResponseEntity.ok(isUniquelyDecodable);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
