package mg.ando.codage.sardinas.service;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.sardinas.algorithm.SardinasAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Service
@Validated
public class SardinasServiceImpl implements SardinasService {
    public boolean checkUniquelyDecodable(@Valid Alphabet alphabet) {

        return new SardinasAlgorithm(alphabet).isUniquelyDecodable();
    }
}