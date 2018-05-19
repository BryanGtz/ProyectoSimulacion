/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Fila extends JPanel implements Runnable{
    
    Queue<Bus> fila;
    
    public Fila(){
        fila = new LinkedList();
    }

    @Override
    public void run() {
        
    }
}
