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
    int num;
    Hora llegada;
    Hora inicioInsp;
    Hora duracionInsp;
    Hora finalInsp;
    int reparacion;
    Hora llegadaFilaRep;
    Hora inicioRep;
    Hora duracionRep;
    Hora salida;
    
    public Bus(Escenario e, int ancho, int alto, int num, Hora llegada){
        setBounds(0,0,ancho,alto);
        this.e = e;
        this.num = num;
        this.llegada = new Hora(llegada);
        int numImagen = (this.num%10)+1;
        try {
            bi = ImageIO.read(new File("bus"+numImagen+".png"));
        } catch (IOException ex) {
            System.out.println("No se encontro el archivo");
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(bi, 0, 0,getWidth(),getHeight(), this);
    }

    @Override
    public String toString() {
        return "Bus{" + "num=" + num + ", llegada=" + llegada + ", inicioInsp=" + inicioInsp + ", duracionInsp=" + duracionInsp + ", finalInsp=" + finalInsp + ", reparacion=" + reparacion + ", llegadaFilaRep=" + llegadaFilaRep + ", inicioRep=" + inicioRep + ", duracionRep=" + duracionRep + ", salida=" + salida + '}';
    }

    
   
}
