package mg.ando.codage.huffman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class HuffmanNode implements Comparable<HuffmanNode> {
    private char character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;
    
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}