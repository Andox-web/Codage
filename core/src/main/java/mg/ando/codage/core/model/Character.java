package mg.ando.codage.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Character {
    
    private final char value;
    private final BinaryCode code;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return value == character.value && 
               code.equals(character.code);
    }

    @Override
    public int hashCode() {
        return 31 * value + code.hashCode();
    }

    public Character(char character, String code) {
        this.value=character;
        this.code=new BinaryCode(code);
    }

    @Override
    public String toString() {
        return value+"";
    }
}