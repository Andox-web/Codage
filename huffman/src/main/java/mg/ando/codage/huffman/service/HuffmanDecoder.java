package mg.ando.codage.huffman.service;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.model.Character;

public class HuffmanDecoder {
    
    private final Alphabet alphabet;

    public HuffmanDecoder(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String decode(String encodedText) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();

        for (char bit : encodedText.toCharArray()) {
            currentCode.append(bit);
            Character character = alphabet.getCharacterByCode(currentCode.toString());
            if (character != null) {
                result.append(character);
                currentCode.setLength(0);
            }
        }

        return result.toString();
    }
}