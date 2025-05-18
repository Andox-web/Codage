package mg.ando.codage.sardinas.service;

import jakarta.validation.Valid;
import mg.ando.codage.core.model.Alphabet;

public interface SardinasService {
    public boolean checkUniquelyDecodable(@Valid Alphabet alphabet);
}
