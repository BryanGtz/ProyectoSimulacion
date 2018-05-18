/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Bus extends JPanel {
    
    BufferedImage bi = null;
    Escenario e;
    Hora horaLlegada;
    
    public Bus(Escenario e, int ancho, int alto){
        setBounds(0,0,ancho,alto);
        this.e = e;
        horaLlegada = new Hora();
    }
    
    @Override
    public void paintComponent(Graphics g){
        try {
            bi = ImageIO.read(new File("bus.png"));
        } catch (IOException ex) {
            System.out.println("No se encontro el archivo");
        }
        g.drawImage(bi, 0, 0,getWidth(),getHeight(), this);
    }
    
}
