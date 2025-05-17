package mg.ando.codage.core.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Alphabet {
    
    @NotBlank
    private String name;
    
    @NotNull
    private Set<Character> characters = new HashSet<>();
    
    public void addCharacter(Character character) {
        validateUniqueCode(character);
        characters.add(character);
    }
    
    public Set<Character> getCharacters() {
        return Collections.unmodifiableSet(characters);
    }
    
    private void validateUniqueCode(Character newChar) {
        boolean codeExists = characters.stream()
            .anyMatch(c -> c.getCode().equals(newChar.getCode()));
        
        if (codeExists) {
            throw new IllegalArgumentException(
                "Le code binaire '" + newChar.getCode().getValue() + 
                "' est déjà utilisé");
        }
    }
    
    public Character getCharacterByCode(String binaryCode) {
        return characters.stream()
            .filter(c -> c.getCode().getValue().equals(binaryCode))
            .findFirst()
            .orElse(null);
    }
}