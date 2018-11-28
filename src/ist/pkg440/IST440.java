/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import ist.pkg440.requests.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author andre
 */
public class IST440 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginController controller = new LoginController();
        testDecipher();
    }
    
    private static void testDecipher() {
        DecipherImageProccess process = new DecipherImageProccess(TranslationLanguage.PORTUGUES_BR, new DecipherImageProccessDelegate() {
            @Override
            public void finishedStep(DecipherImageProccessStep step) {
                switch (step) {
                    case OCR:
                        // Update Progress 1/3
                        System.out.println("Finished OCR");
                        break;
                    case DECIPHER:
                        // Update Progress 2/3
                        System.out.println("Finished DECIPHER");
                        break;
                    case TRANSLATE:
                        // Update Progress 3/3
                        System.out.println("Finished TRANSLATION");
                        break;
                }
            }

            @Override
            public void success(String decipheredMessage) {
                System.out.println("Success! Message deciphered to: " + decipheredMessage);
            }

            @Override
            public void error(String errorMessage) {
                System.out.println("There was an error: " + errorMessage);
            }
        });
        process.decipher(image());
    }

    private static byte[] image() {
        try {
            BufferedImage img = ImageIO.read(new File("dpljr.png"));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", stream);
            return stream.toByteArray();
        } catch (IOException ex) {
            return null;
        }
    }

}
