package mg.ando.codage.core.sequence;

import java.util.Iterator;

import lombok.Data;

@Data
public class Sequence implements Iterable<Integer> {
    private final int firstTerm;
    private final int a;
    private final int b;
    private final int nMax;
    private final int mod;
    
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int currentTerm = firstTerm;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < nMax;
            }

            @Override
            public Integer next() {
                int term = currentTerm;
                currentTerm = (a * currentTerm + b) % mod;
                currentIndex++;
                return term;
            }
        };
    }
    public int get(int index) {
        if (index < 0 || index >= nMax) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int term = firstTerm;
        for (int i = 0; i < index; i++) {
            term = (a * term + b) % mod;
        }
        return term;
    }
}
