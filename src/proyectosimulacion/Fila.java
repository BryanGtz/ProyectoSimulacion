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
    Hora sumaEsperas = new Hora();
    Hora horaLlegadaBus = new Hora();
    Escenario e;
    
    public Fila(int ancho, int alto, Escenario e){
        fila = new LinkedList();
        this.e = e;
        setLayout(null);
        setSize(ancho,alto);
    }
    
    /**
     * @param numBus numero del bus
     * @param mediaExpLlegada media exponencial en que llegan los buses
     * @param llegada hora en que llego el bus a la fila
     * @return Hora en que debe llegar el siguiente bus
    */
    
    public Hora llegada(int numBus, int mediaExpLlegada, Hora llegada){
        Bus b = new Bus(e, 100, 50, numBus);//se hace llegar al bus
        horaLlegadaBus = new Hora(llegada);
        fila.offer(b);//se añade el autobus a la fila
        double exp = e.va.Exponencial(mediaExpLlegada);
        return new Hora(exp);
    }
    
    public void salida(EstacionInspeccion ei, Hora salida){
        Hora salidaBus = new Hora(salida);
        ei.addBus(fila.poll(),salida);
        Hora esperaBus = salidaBus.menos(horaLlegadaBus);
        sumaEsperas = sumaEsperas.mas(esperaBus);
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
        }else{
            removeAll();
        }
    }
}
