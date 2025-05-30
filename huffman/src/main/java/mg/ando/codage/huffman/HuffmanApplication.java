package mg.ando.codage.huffman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mg.ando.codage")
public class HuffmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuffmanApplication.class, args);
    }
}
