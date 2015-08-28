package center.rodrigo.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
 
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Write {
    
    private Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap;
    private QRCodeWriter qrCodeWriter;
    private BitMatrix byteMatrix;
    private BufferedImage image;
    private Graphics2D graphics;
    private int tamanho = 500;
    
    public Write() {
        this.hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        this.qrCodeWriter = new QRCodeWriter();
    }
    
    public void criarQRCode(String texto, String path, String extensao) {
        
        File arquivo = new File(path);
        
        try {
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            byteMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, tamanho, tamanho, hintMap);
            
            int CrunchifyWidth = byteMatrix.getWidth();
            image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
 
            graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
 
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            ImageIO.write(image, extensao, arquivo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}