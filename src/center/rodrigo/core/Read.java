package center.rodrigo.core;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Read {

    private BinaryBitmap binaryBitmap;
    private BufferedImage bImage;
    private Map hintMap;
    private Result resultado;
    private BufferedImageLuminanceSource bils;
            
    public Read() {
        hintMap = new HashMap();
    }
    
    public String lerQRCode(String path) {
        
        try {
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            bImage = ImageIO.read(new FileInputStream(path));
            bils = new BufferedImageLuminanceSource(bImage);
        
            binaryBitmap = new BinaryBitmap(new HybridBinarizer(bils));
            resultado = new MultiFormatReader().decode(binaryBitmap, hintMap);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return resultado.getText();
    }
    
}