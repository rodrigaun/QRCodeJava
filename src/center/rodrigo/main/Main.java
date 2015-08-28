package center.rodrigo.main;

import center.rodrigo.core.Read;
import center.rodrigo.core.Write;

public class Main {

    public static void main(String[] args) {

        Write write = new Write();
        String texto = "Hello World !!";
        write.criarQRCode(texto, "imagem.png", "png");
        System.out.println("QRCode criado com sucesso !\nTexto: " + texto);
        
        Read read = new Read();
        System.out.println("\nLendo QRCode...\nTexto: " + read.lerQRCode("imagem.png"));
    }

}