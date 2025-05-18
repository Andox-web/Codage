package mg.ando.codage.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.model.BinaryCode;
import mg.ando.codage.core.model.Character;
import mg.ando.codage.sardinas.service.SardinasService;

@SpringBootApplication
@ComponentScan(basePackages = {"mg.ando.codage"})
public class ConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SardinasService codeService) {
        return args -> {
            // Création d'un alphabet codé en dur
            Alphabet testAlphabet = createTestAlphabet();
            
            // Exécution du test
            boolean result = codeService.checkUniquelyDecodable(testAlphabet);
            
            // Affichage des résultats
            printResults(testAlphabet, result);
            System.exit(0);
        };
    }

    private Alphabet createTestAlphabet() {
        Alphabet alphabet = new Alphabet();
        alphabet.setName("Alphabet de test");

        // Ajout des caractères directement dans le code
        alphabet.addCharacter(new Character('A', new BinaryCode("0")));
        alphabet.addCharacter(new Character('B', new BinaryCode("10")));
        alphabet.addCharacter(new Character('C', new BinaryCode("11")));
        alphabet.addCharacter(new Character('D', new BinaryCode("110")));

        return alphabet;
    }

    private void printResults(Alphabet alphabet, boolean isUniquelyDecodable) {
        System.out.println("=== Test d'unicité de décodage ===");
        System.out.println("Alphabet testé : " + alphabet.getName());
        
        System.out.println("\nCaractères :");
        alphabet.getCharacters().forEach(c -> 
            System.out.println("• " + c.getValue() + " → " + c.getCode().getValue()));
        
        System.out.println("\nRésultat :");
        System.out.println(isUniquelyDecodable ? 
            "✅ Le code est uniquement déchiffrable" : 
            "❌ Le code contient des ambiguïtés");
    }
}