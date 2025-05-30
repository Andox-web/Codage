package mg.ando.codage.huffman.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileEncodeResponse {
    private final byte[] encodedFile;
    private final byte[] alphabetFile;
}