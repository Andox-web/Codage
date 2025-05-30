package mg.ando.codage.steganographie.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageUtils {

    // Convertir un fichier image en BufferedImage
    public static BufferedImage fileToBufferedImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    // Convertir un BufferedImage en tableau de bytes (format PNG)
    public static byte[] bufferedImageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    // Convertir un tableau de bytes en BufferedImage
    public static BufferedImage bytesToBufferedImage(byte[] imageBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(bais);
    }

    // Convertir un fichier en tableau de bytes
    public static byte[] fileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    // Convertir un tableau de bytes en fichier
    public static void bytesToFile(byte[] data, File outFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            fos.write(data);
        }
    }

    // Calculer le niveau de gris d'un pixel (R, G, B)
    public static int calculateGrayscale(int[] channels) {
        return (299 * channels[1] + 587 * channels[2] + 114 * channels[3]) / 1000;
    }

    // Mettre à jour les canaux R, G, B pour correspondre à un niveau de gris cible
    public static int[] updateChannels(int[] channels, int targetGrayscale) {
        // int currentGrayscale = calculateGrayscale(channels);

        // if (currentGrayscale == 0) {
            channels[1] = targetGrayscale;
            channels[2] = targetGrayscale;
            channels[3] = targetGrayscale;
            return channels;
        // }

        // double factor = (double) targetGrayscale / currentGrayscale;
        // channels[1] = clamp((int) Math.round(channels[1] * factor));
        // channels[2] = clamp((int) Math.round(channels[2] * factor));
        // channels[3] = clamp((int) Math.round(channels[3] * factor));
        // if (targetGrayscale != calculateGrayscale(channels)) {
        //     throw new IllegalStateException("Erreur de mise à jour des canaux : " + targetGrayscale + " != " + calculateGrayscale(channels));
        // }
        // return channels;
    }
    
    public static BufferedImage createImageFromMapAutoSize(Map<Integer, Integer> dataMap, int requiredSize) {
        int maxIndex = Math.max(
            requiredSize,
            dataMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1
        );
        int width = (int) Math.ceil(Math.sqrt(maxIndex));
        int height = (int) Math.ceil((double) maxIndex / width);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                int color;

                if (index >= requiredSize) {
                    // Si on dépasse la taille requise, on arrête le traitement
                    continue;
                }

                Integer value = dataMap.get(index);

                if (value != null) {
                    color = value;
                } else {
                    int gray = random.nextInt(256);
                    color = (gray << 16) | (gray << 8) | gray;
                }

                image.setRGB(x, y, color);
            }
        }
        
        return image;
    }
  
    public static void saveAsPng(BufferedImage image, String filePath) throws IOException {
        File outputFile = new File(filePath);
        boolean success = ImageIO.write(image, "PNG", outputFile);
        
        if (!success) {
            throw new IOException("Échec de l'écriture au format PNG");
        }
    }
}
