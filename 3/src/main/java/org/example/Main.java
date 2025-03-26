package org.example;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException {
        List<Path> files;
        Path source = Path.of("src/main/java/org/example/tmp/input");
        try (Stream<Path> stream = Files.list(source)){
            files = stream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long time = System.currentTimeMillis();

        Stream<Path> stream = files.parallelStream();

        stream.forEach(path -> {
            try {
                editImage(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Total time taken: " + (System.currentTimeMillis() - time));
    }

    public static void editImage(Path path) throws IOException {
        long totalTime = System.currentTimeMillis();

        BufferedImage image = ImageIO.read(path.toFile());
        String name = String.valueOf(path.getFileName());
        Pair<String, BufferedImage> pair = Pair.of(name, image);

        String[] filename = name.split("\\.");

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        long time = System.currentTimeMillis();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                Color outColor = new Color(red, blue, green);
                int outRgb = outColor.getRGB();
                newImage.setRGB(i, j, outRgb);
            }
        }

        System.out.println(filename[0] + " edit 1: " + Long.toString(System.currentTimeMillis() - time));

        Path output = Path.of("src/main/java/org/example/tmp/output/" + filename[0] + "_edit_1." + filename[1]);
        ImageIO.write(newImage, filename[1], output.toFile());

        time = System.currentTimeMillis();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(image.getWidth() - 1 - i, image.getHeight() - 1 - j);
                newImage.setRGB(i, j, rgb);
            }
        }

        System.out.println(filename[0] + " edit 2: " + Long.toString(System.currentTimeMillis() - time));

        output = Path.of("src/main/java/org/example/tmp/output/" + filename[0] + "_edit_2." + filename[1]);
        ImageIO.write(newImage, filename[1], output.toFile());

        System.out.println(filename[0] + " total edit time: " + Long.toString(System.currentTimeMillis() - totalTime));
    }
}