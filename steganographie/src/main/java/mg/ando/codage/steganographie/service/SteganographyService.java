package mg.ando.codage.steganographie.service;

import java.awt.image.BufferedImage;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.sequence.Sequence;

public interface SteganographyService {
    public int encode(BufferedImage img, String message, Alphabet alphabet, Sequence seq);
    public BufferedImage encode(String message, Alphabet alphabet, Sequence seq);
    public String decode(BufferedImage img, Alphabet alphabet, Sequence seq);
}
