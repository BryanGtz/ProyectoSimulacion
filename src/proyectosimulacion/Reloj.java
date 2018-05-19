/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Reloj extends JPanel implements Runnable{
    
    Escenario e;
    Hora hora;
    JLabel reloj;
    
    public Reloj(Escenario es){
        e=es;
        hora = new Hora();
        reloj = new JLabel(hora.toString());
        add(reloj);
    }

    @Override
    public void run() {
        while (this.hora.menorQue(new Hora(9000))) {
            hora.mas(1);
            reloj.setText(hora.toString());
        }
    }
    
}
