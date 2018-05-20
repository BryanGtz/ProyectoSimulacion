/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosimulacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Equipo 6
 */
public class Ventana extends JFrame {
    
    JButton inicio_pausa = new JButton("Empezar");
    JButton reiniciar = new JButton("Reiniciar");
    JPanel botones = new JPanel();
    Reloj r = new Reloj();
    Escenario escenario = new Escenario(r);
    Thread t;
    Thread t2;
    
    public Ventana(){
        buildLayout();
        config();
        inicio_pausa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(escenario.iniciar){
                    escenario.iniciar = false;
                    inicio_pausa.setText("Reanudar");
                }
                else{
                    escenario.iniciar = true;
                    inicio_pausa.setText("Pausar");
                }
                r.iniciar = !r.iniciar;
                Thread t = new Thread(escenario);
                t.start();
                Thread t2 = new Thread(r);
                t2.start();
            }
        });
        
        reiniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.this.dispose();
                new Ventana().setVisible(true);
            }
        });
    }
    
    private void buildLayout(){
        botones.add(inicio_pausa);
        botones.add(reiniciar);
        add(botones,"North");
        escenario.setBounds(0,0,1000,500);
        add(escenario,BorderLayout.CENTER);
    }
    
    private void config(){
        setSize(1024,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
