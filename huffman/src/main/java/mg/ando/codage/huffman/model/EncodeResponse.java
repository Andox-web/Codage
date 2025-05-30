package mg.ando.codage.huffman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mg.ando.codage.core.model.Alphabet;

@Data
@AllArgsConstructor
public class EncodeResponse {
    private Alphabet alphabet;
    private String encodedString;
}
