/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Reloj extends JPanel implements Runnable{

    Hora hora;
    JLabel reloj;
    boolean iniciar;
    
    public Reloj(){
        hora = new Hora();
        reloj = new JLabel(hora.toString());
        reloj.setFont( new Font("TimesRoman",Font.PLAIN,24));
        add(reloj);
        iniciar = false;
    }

    @Override
    public void run() {
        while (this.hora.menorQue(new Hora(9600))&&iniciar) {
//            hora.mas(1);
//            reloj.setText(hora.toString());
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//
//            }
        }
    }  
}
