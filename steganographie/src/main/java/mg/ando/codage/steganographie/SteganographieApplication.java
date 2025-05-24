package mg.ando.codage.steganographie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan(basePackages = "mg.ando.codage.steganographie"),
        @ComponentScan(basePackages = "mg.ando.codage.huffman.service"),
})
public class SteganographieApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteganographieApplication.class, args);
    }
}
