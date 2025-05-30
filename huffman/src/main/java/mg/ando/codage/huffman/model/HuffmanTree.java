package mg.ando.codage.huffman.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.Data;
import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.model.BinaryCode;
import mg.ando.codage.core.model.Character;

@Data
public class HuffmanTree {

    private HuffmanNode root;
    private final List<HuffmanNode> frequencyMap;
    private final Alphabet alphabet;
    public HuffmanTree(String reference) {
        this.frequencyMap=getFrequencyMap(reference);
        this.root = buildTree();
        this.alphabet= new Alphabet();
        generateCodes(root, "", this.alphabet);
    }

    private List<HuffmanNode> getFrequencyMap(String reference) {
        List<HuffmanNode> frequencyMap = new ArrayList<>();
        Map<java.lang.Character, Integer> frequencyCharMap = new HashMap<>();
        // Calcul des fréquences
        for (char c : reference.toCharArray()) {
            frequencyCharMap.put(c, frequencyCharMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<java.lang.Character, Integer> entry : frequencyCharMap.entrySet()) {
            frequencyMap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
    
        return frequencyMap;
    }

    private HuffmanNode buildTree() {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        // Création des feuilles initiales
        queue.addAll(frequencyMap);

        // Construction de l'arbre
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            
            HuffmanNode parent = new HuffmanNode(
                '\0', 
                left.getFrequency() + right.getFrequency(), 
                left, 
                right
            );
            
            queue.add(parent);
        }

        return queue.poll();
    }

    private void generateCodes(HuffmanNode node, String code, Alphabet alphabet) {
        if (node.isLeaf()) {
            // Met à jour le caractère avec le code binaire généré
            alphabet.addCharacter(new Character(node.getCharacter(), new BinaryCode(code)));
        } else {
            generateCodes(node.getLeft(), code + "0", alphabet);
            generateCodes(node.getRight(), code + "1", alphabet);
        }
    }

    public Alphabet getEncodedAlphabet() {
        return this.alphabet;
    }
}