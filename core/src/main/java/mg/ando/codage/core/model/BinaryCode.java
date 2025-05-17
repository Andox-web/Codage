package mg.ando.codage.core.model;

import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class BinaryCode {
    
    @Pattern(regexp = "[01]+", message = "Le code binaire ne doit contenir que des 0 et 1")
    private String value;

    public BinaryCode(String value) {
        this.value = value;
    }
    
    public int length() {
        return value.length();
    }
}