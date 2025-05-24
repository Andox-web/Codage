package mg.ando.codage.steganographie.service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import mg.ando.codage.core.model.Alphabet;
import mg.ando.codage.core.sequence.Sequence;
import mg.ando.codage.huffman.service.HuffmanService;
import mg.ando.codage.steganographie.util.ImageUtils;

@Service
public class SteganographyServiceImpl implements SteganographyService {

    private final HuffmanService huffmanService;

    public SteganographyServiceImpl(HuffmanService huffmanService) {
        this.huffmanService = huffmanService;
    }

    @Override
    public int encode(BufferedImage img, String message, Alphabet alphabet, Sequence seq) {
        String binary = huffmanService.encode(message, alphabet);
        int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        Iterator<Integer> iterator = seq.iterator();
        for (int i = 0; i < binary.length(); i++) {
            if (!iterator.hasNext()) {
                throw new RuntimeException("Séquence trop courte pour le message");
            }

            int pixelIndex = iterator.next();
            validatePixelIndex(pixelIndex, pixels.length);

            int argb = pixels[pixelIndex];
            int[] channels = extractChannels(argb);
            int currentValue = ImageUtils.calculateGrayscale(channels);

            boolean bit = binary.charAt(i) == '1';
            int newValue = bit ? (currentValue | 1) : (currentValue & ~1);

            // Mise à jour des 3 canaux pour garder le noir et blanc
            channels =  ImageUtils.updateChannels(channels,newValue);
            pixels[pixelIndex] = rebuildARGB(channels);
        }
        img.setRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
        return binary.length();
    }
    
    @Override
    public BufferedImage encode(String message, Alphabet alphabet, Sequence seq) {
        String binary = huffmanService.encode(message, alphabet);
        Iterator<Integer> iterator = seq.iterator();
        Map<Integer, Integer> pixelsMap = new HashMap<>();
        int pixelIndex = -1;
        for (int i = 0; i < binary.length(); i++) {
            if (!iterator.hasNext()) {
                throw new RuntimeException("Séquence trop courte pour le message");
            }

            pixelIndex = iterator.next();

            Integer argb = pixelsMap.getOrDefault(pixelIndex, null);
            int[] channels = extractChannels(argb);
            int currentValue = ImageUtils.calculateGrayscale(channels);

            boolean bit = binary.charAt(i) == '1';
            int newValue = bit ? (currentValue | 1) : (currentValue & ~1);

            channels = ImageUtils.updateChannels(channels, newValue);
            pixelsMap.put(pixelIndex, rebuildARGB(channels));
        }
        
        return ImageUtils.createImageFromMapAutoSize(pixelsMap, pixelIndex + 2);
    }


    @Override
    public String decode(BufferedImage img, Alphabet alphabet, Sequence seq) {
        int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        StringBuilder binary = new StringBuilder();

        for (int pixelIndex : seq) {
            validatePixelIndex(pixelIndex, pixels.length);
            int[] channels = extractChannels(pixels[pixelIndex]);
            int grayscale = ImageUtils.calculateGrayscale(channels); 
            binary.append(grayscale & 1); // Récupère le LSB d'un canal quelconque
        }
        return huffmanService.decode(binary.toString(), alphabet);
    }

    // Méthodes utilitaires inchangées
    private int[] extractChannels(Integer argb) {
        if (argb == null) {
            int randomValue = (int) (Math.random() * 0xFFFFFFFF);
            return new int[] {
                (randomValue >> 24) & 0xFF,
                (randomValue >> 16) & 0xFF,
                (randomValue >> 8) & 0xFF,
                randomValue & 0xFF
            };
        }
        return new int[] {
            (argb >> 24) & 0xFF,
            (argb >> 16) & 0xFF,
            (argb >> 8) & 0xFF,
            argb & 0xFF
        };
    }

    private int rebuildARGB(int[] channels) {
        return (channels[0] << 24) | (channels[1] << 16) | (channels[2] << 8) | channels[3];
    }

    private void validatePixelIndex(int index, int length) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index pixel invalide : " + index+ " (doit être entre 0 et " + (length - 1) + ")");
        }
    }
    public static Integer getOrDefault(List<Integer> list, int index, Integer defaultValue) {
        if (list != null && index >= 0 && index < list.size()) {
            return list.get(index);
        } else {
            return defaultValue;
        }
    }


}