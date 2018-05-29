/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class EstacionReparacion extends JPanel{
    boolean libre;
    Hora horaSalida;
    Bus bus;
    VariableAleatoria va = new VariableAleatoria();
    
    public EstacionReparacion(int ancho, int alto){
        setSize(ancho,alto);
        setLayout(null);
        libre = true;
        horaSalida = new Hora();
    }
    
    public void addBus(Bus b, Hora horaInicio){
        if(libre){
            if(b!=null){
                bus = b;
                libre = false;
                double exp = va.Uniforme(126, 270);
                Hora duracionServicio = new Hora(exp);
                System.out.println("Duracion del servicio: "+duracionServicio);
                horaSalida = duracionServicio.mas(horaInicio);
                System.out.println("Hora de salida: "+horaSalida);
            }
        }
    }
    
    public void liberar(){
        bus = null;
        libre = true;
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(bus!=null){
            bus.setLocation(0, 0);
            add(bus);
        }
        else{
            removeAll();
        }
    }
}
