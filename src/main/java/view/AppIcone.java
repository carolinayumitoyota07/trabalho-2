package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public final class AppIcone {

    private AppIcone() {
    }

    public static Image criarImagem() {
        int tamanho = 64;
        BufferedImage imagem = new BufferedImage(
            tamanho,
            tamanho,
            BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D grafico = imagem.createGraphics();
        grafico.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        Color verdeEscuro = new Color(24, 67, 48);
        Color verdeMedio = new Color(49, 104, 73);
        Color ambar = new Color(214, 161, 45);
        Color claro = new Color(244, 241, 220);

        grafico.setColor(verdeEscuro);
        grafico.fillOval(4, 4, 56, 56);

        grafico.setColor(ambar);
        grafico.setStroke(new BasicStroke(3));
        grafico.drawOval(7, 7, 50, 50);

        grafico.setColor(verdeMedio);
        grafico.fillArc(13, 12, 38, 38, 205, 130);

        grafico.setColor(claro);
        grafico.setFont(new Font("SansSerif", Font.BOLD, 18));
        grafico.drawString("SG", 18, 31);
        grafico.drawString("AP", 17, 48);

        grafico.setColor(ambar);
        grafico.fillOval(43, 16, 5, 5);
        grafico.fillOval(48, 22, 5, 5);
        grafico.fillOval(43, 28, 5, 5);

        grafico.dispose();
        return imagem;
    }
}
