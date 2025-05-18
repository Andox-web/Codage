package mg.ando.codage.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.model.BinaryCode;
import mg.ando.codage.core.model.Character;
import mg.ando.codage.huffman.model.EncodeResponse;
import mg.ando.codage.huffman.service.HuffmanService;
import mg.ando.codage.sardinas.service.SardinasService;

@SpringBootApplication
@ComponentScan(basePackages = {"mg.ando.codage"})
public class ConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SardinasService sardinasService, HuffmanService huffmanService) {
        return args -> {
            // Test d'unicité de décodage (Sardinas)
            Alphabet testAlphabet = createTestAlphabet();
            boolean sardinasResult = sardinasService.checkUniquelyDecodable(testAlphabet);
            printSardinasResults(testAlphabet, sardinasResult);

            // Test d'encodage/décodage Huffman
            testHuffmanEncodingDecoding(huffmanService);

            System.exit(0);
        };
    }

    // Crée un alphabet de test avec un code potentiellement ambigu
    private Alphabet createTestAlphabet() {
        Alphabet alphabet = new Alphabet();
        alphabet.setName("Alphabet de test");

        alphabet.addCharacter(new Character('A', new BinaryCode("0")));
        alphabet.addCharacter(new Character('B', new BinaryCode("10")));
        alphabet.addCharacter(new Character('C', new BinaryCode("11")));
        alphabet.addCharacter(new Character('D', new BinaryCode("110"))); // Code ambigu

        return alphabet;
    }

    // Test complet du cycle Huffman
    private void testHuffmanEncodingDecoding(HuffmanService huffmanService) {
        System.out.println("\n=== Test Huffman (Encodage/Décodage) ===");
        String originalText = "BACAD"; // Texte avec caractères du testAlphabet
        
        try {
            // 1. Encodage
            EncodeResponse encodeResponse = huffmanService.encode(originalText);
            String encodedString = encodeResponse.getEncodedString(); // Encodage en bytes
            Alphabet huffmanAlphabet = encodeResponse.getAlphabet(); // Alphabet généré
            huffmanAlphabet.printInfo(); // Affichage de l'alphabet

            // 2. Décodage
            String decodedText = huffmanService.decode(encodedString, huffmanAlphabet);

            // Affichage des résultats
            System.out.println("Texte original : " + originalText);
            System.out.println("Bits générés : " + encodedString);
            System.out.println("Texte reconstruit : " + decodedText);
            
            // Vérification finale
            if (originalText.equals(decodedText)) {
                System.out.println("✅ Succès : Décodage fidèle");
            } else {
                System.out.println("❌ Échec : Décodage corrompu");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Erreur pendant le test : " + e.getMessage());
        }
    }

    private void printSardinasResults(Alphabet alphabet, boolean result) {
        System.out.println("=== Résultats Sardinas-Patterson ===");
        System.out.println("Alphabet : " + alphabet.getName());
        alphabet.getCharacters().forEach(c -> 
            System.out.println("→ " + c.getValue() + " : " + c.getCode().getValue()));
        System.out.println("\nDécodage unique ? " + (result ? "OUI ✅" : "NON ❌"));
    }
}