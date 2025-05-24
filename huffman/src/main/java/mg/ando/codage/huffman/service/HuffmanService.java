package mg.ando.codage.huffman.service;

import java.io.IOException;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.huffman.model.EncodeResponse;
import mg.ando.codage.huffman.model.FileEncodeResponse;

public interface HuffmanService {
    public String encode(String input, Alphabet alphabet);
    public String decode(String input,Alphabet alphabet);
    public EncodeResponse encode(String input);
    public FileEncodeResponse encodeFile(byte[] file) throws IOException;
    public byte[] decodeFile(byte[] file, byte[] alphabetFile) throws ClassNotFoundException, IOException;
}
