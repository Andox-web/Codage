package mg.ando.codage.core.sequence;

import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sequence implements Iterable<Integer> {
    private final int firstTerm;
    private final int a;
    private final int b;
    private Integer nMax;
    private int mod;
    private final int firstCursor;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int currentTerm = firstTerm;
            private int currentCursor = firstCursor;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (nMax == null) {
                    return true;
                }
                return currentIndex < nMax;
            }

            @Override
            public Integer next() {
                int term = currentTerm;
                currentTerm = (a * currentTerm + b) % mod;
                currentIndex++;
                currentCursor+=term;
                return currentCursor;
            }
        };
    }
    public final void setModFromNmax() {
        int Un = get(nMax);
        int width = (int) Math.ceil(Math.sqrt(Un));
        int height = (int) Math.ceil((double) Un / width);

        int requiredSize = width * height;
        this.mod = requiredSize;
    }
    public int get(int index) {
        if (index < 0 || index >= nMax) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int term = firstTerm;
        int currentCursor =  firstCursor;
        for (int i = 0; i < index; i++) {
            term = (a * term + b) % mod;
            currentCursor+=term;
        }
        return currentCursor;
    }
    @Override
    public String toString() {
        return "Sequence{" +
                "firstTerm=" + firstTerm +
                ", a=" + a +
                ", b=" + b +
                ", nMax=" + nMax +
                ", mod=" + mod +
                '}';
    }
}
