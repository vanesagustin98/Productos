/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package productos;
import java.util.ArrayList;

/**
 *
 * @author ANTHONY
 */

public interface BaseDeDatos {
   public ArrayList<Productos> listaProductos();
   public Productos getNombre(String name);
   public int getCantidad();
   public int getTotal();
   public String agregarProducto(Productos producto);
   public String eliminarProducto(Productos producto);
}