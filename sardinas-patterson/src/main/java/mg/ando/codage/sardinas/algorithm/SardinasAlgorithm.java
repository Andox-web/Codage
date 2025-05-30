package mg.ando.codage.sardinas.algorithm;

import mg.ando.codage.core.model.Alphabet;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class SardinasAlgorithm {
    public SardinasAlgorithm(Alphabet alphabet) {
        this.L0 = new HashSet<>();
        alphabet.getCharacters().forEach(c -> L0.add(c.getCode().getValue()));
    }
    
    private final Set<String> L0;
    public SardinasAlgorithm(Set<String> L0) {
        this.L0 = L0;
    }

    /**
     * Check if the given alphabet is uniquely decodable.
     *
     * @param the alphabet to check
     * 
     * @return true if the alphabet is uniquely decodable, false otherwise
     */
    public boolean isUniquelyDecodable() {
        Set<Set<String>> seen = new HashSet<>();
        Set<String> previousSet = L0;
        Set<String> currentSet = new HashSet<>();

        while (true) {
            currentSet = generateNextSet(previousSet, L0);

            if (verifyNonCode(currentSet, L0)) {
                return false;
            }

            if (currentSet.isEmpty() || seen.contains(currentSet)) {
                break;
            }
            seen.add(currentSet);
            previousSet = currentSet;
        }
        return true;
    }

    private Set<String> generateNextSet(Set<String> Ci, Set<String> L0) {
        Set<String> nextSet = new HashSet<>();
        
        for (String u : Ci) {
            for (String v : L0) {
                if (u.startsWith(v) && !u.equals(v)) {
                    String suffix = u.substring(v.length());
                    nextSet.add(suffix);
                }
                if (v.startsWith(u) && !v.equals(u)) {
                    String suffix = v.substring(u.length());
                    nextSet.add(suffix);
                }
            }
        }
        return nextSet;
    }

    private boolean verifyNonCode(Set<String> set, Set<String> L0) {
        if (set.contains("")) {
            return true;
        }
        return set.stream().anyMatch(L0::contains);
    }
}