package mg.ando.codage.core.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    public BinaryCode getBinaryCodeByChar(char value) {
        return characters.stream()
            .filter(c -> c.getValue() == value)
            .map(c -> c.getCode())
            .findFirst()
            .orElse(null);
    }
    public void printInfo() {
        System.out.println("Alphabet: " + name);
        System.out.println("Caractères:");
        Map<Integer, Integer> longueurOccurrences = new HashMap<>();

        for (Character character : characters) {
            String codeValue = character.getCode().getValue();
            int longueur = codeValue.length();

            // Affichage du caractère
            System.out.println("  - " + character + ": " + codeValue);

            // Comptage des longueurs
            longueurOccurrences.put(longueur, longueurOccurrences.getOrDefault(longueur, 0) + 1);
        }

        System.out.println("Nombre de caractères: " + characters.size());

        // Affichage du résumé des longueurs
        System.out.print("Longueur des codes: ");
        for (Map.Entry<Integer, Integer> entry : longueurOccurrences.entrySet()) {
            System.out.print(entry.getKey() + " " + entry.getValue() + ", ");
        }
        System.out.println(); 
    }

}