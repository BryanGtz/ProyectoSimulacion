/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Fila extends JPanel{
    
    Queue<Bus> fila;
    Hora sumaEsperas = new Hora();
    Escenario e;
    int longMax = 0;
    
    public Fila(int ancho, int alto, Escenario e){
        fila = new LinkedList();
        this.e = e;
        setLayout(null);
        setSize(ancho,alto);
    }
    
    /**
     * @param b bus que llega a la fila
    */
    
    public void entrada(Bus b){
        fila.offer(b);//se añade el autobus a la fila
    }
    
    /**
     * @param ei Estacion a donde va a ser mandado el bus
     * <code>null</code> si va a salir del sistema
     * 
    */
    
    public void salida(Estacion ei, Hora salida){
        Hora salidaBus = new Hora(salida);
        Bus bus = fila.poll();
        if(ei!=null){
            ei.addBus(bus,salida);
            if(ei instanceof EstacionInspeccion){
                bus.inicioInsp = new Hora(salida);
            }
            else if(ei instanceof EstacionReparacion){
                bus.inicioRep = new Hora(salida);
            }
        }
        
        Hora esperaBus = salidaBus.menos(bus.llegada);
        sumaEsperas = sumaEsperas.mas(esperaBus);
    }
    
    @Override
    public synchronized void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = getWidth();//coordenada x auxiliar para pintar la fila de autobuses
        for(Bus b: fila){
            x-=b.getWidth();
            b.setLocation(x, b.getY());
            add(b);
        }
        if(fila.isEmpty()){
            removeAll();
        }
        if(fila.size()>longMax){
            longMax = fila.size();
        }
    }
}
