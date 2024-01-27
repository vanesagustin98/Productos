/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

/**
 *
 * @author Vanes
 */
public interface IObservable {
    
    void notify_All(String msj);
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    
}
