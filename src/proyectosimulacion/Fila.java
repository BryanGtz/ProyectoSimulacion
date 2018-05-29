/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Fila extends JPanel{
    
    Queue<Bus> fila;
    
    public Fila(int ancho, int alto){
        fila = new LinkedList();
        setLayout(null);
        setSize(ancho,alto);
    }
    
    @Override
    public void paintComponent(Graphics g){
        int x = getWidth();//coordenada x auxiliar para pintar la fila de autobuses
        if(!fila.isEmpty()){
            for(Bus b: fila){
                x-=b.getWidth();
                b.setLocation(x, b.getY());
                add(b);
            }
        }
//        else{
//            g.drawString("La fila esta vacia", 0, 10);
//System.out.println("La fila esta vacia");
//        }
    }
}
