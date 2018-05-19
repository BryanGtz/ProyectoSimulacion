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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Ventana extends JFrame {
    
    JButton inicio_pausa = new JButton("Empezar");
    JButton reiniciar = new JButton("Reiniciar");
    JPanel botones = new JPanel();
    JLabel hora = new JLabel();
    Escenario escenario = new Escenario();
    //Reloj reloj = new Reloj(escenario);
    
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
                Thread t = new Thread(escenario);
                t.start();
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
//        Thread t = new Thread(reloj);
//        t.start();
//        botones.add(reloj);
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
