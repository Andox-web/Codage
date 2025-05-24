package mg.ando.codage.huffman.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.util.BitConverter;
import mg.ando.codage.core.util.ObjectSerializer;
import mg.ando.codage.huffman.model.EncodeResponse;
import mg.ando.codage.huffman.model.FileEncodeResponse;
import mg.ando.codage.huffman.model.HuffmanTree;

@Service
public class HuffmanServiceImpl implements HuffmanService {

    @Override
    public EncodeResponse encode(String input) {
        HuffmanTree tree = new HuffmanTree(input);
        Alphabet alphabet = tree.getEncodedAlphabet();
        
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            encoded.append(alphabet.getBinaryCodeByChar(c));
        }
        return new EncodeResponse(alphabet, encoded.toString());
    }

    @Override
    public String encode(String input, Alphabet alphabet) {
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            encoded.append(alphabet.getBinaryCodeByChar(c));
        }
        return encoded.toString();
    }

    
    @Override
    public String decode(String encodedText, Alphabet alphabet) {
        HuffmanDecoder decoder = new HuffmanDecoder(alphabet);
        return decoder.decode(encodedText);
    }

    @Override
    public FileEncodeResponse encodeFile(byte[] file) throws IOException {
        String input = new String(file);
        
        EncodeResponse encodeResponse = encode(input);
        Alphabet alphabet = encodeResponse.getAlphabet();
        String encodedText = encodeResponse.getEncodedString();
        byte[] encodedBytes = BitConverter.toByteArray(encodedText);
        byte[] alphabetBytes = ObjectSerializer.serialize(alphabet);
        
        FileEncodeResponse fileEncodeResponse = new FileEncodeResponse(encodedBytes, alphabetBytes);
        return fileEncodeResponse;
    } 

    @Override
    public byte[] decodeFile(byte[] file, byte[] alphabetFile)  throws ClassNotFoundException, IOException {
        String encodedText = BitConverter.fromByteArray(file);
        Alphabet alphabet = (Alphabet) ObjectSerializer.deserialize(alphabetFile);
        String decodedText = decode(encodedText, alphabet);
        
        return decodedText.getBytes();
    } 
}