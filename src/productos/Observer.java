/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author Vanes
 */
public class Observer implements IObserver{
    JComponent componente;

    public Observer(JComponent c) {
        this.componente=c;
    }
    

    @Override
    public void notify_(String mensaje) {
        if(componente instanceof JTextField texto){
            texto.setText(mensaje);
        }
        if(componente instanceof JLabel label){
            label.setText(mensaje);
        }

        
    }
            
}
