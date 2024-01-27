/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;
import java.util.LinkedList;
/**
 *
 * @author Vanes
 */
public class Observable implements IObservable{
    IObserver observer;
    LinkedList<IObserver> subscriptors;

    public Observable(IObserver o) {
        observer=o;
        subscriptors=new LinkedList<>();
    }   
    
    
    
    @Override
    public void notify_All(String msj) {
        for (IObserver subscriptor : subscriptors) {
            subscriptor.notify_(msj);
        }
    }

    @Override
    public void addObserver(IObserver o) {
        subscriptors.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        subscriptors.remove(o);
    }
    
}
