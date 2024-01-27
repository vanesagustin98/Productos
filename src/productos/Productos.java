/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package productos;

/**
 *
 * @author ANTHONY
 */
public class Productos implements Cloneable{
    private String nombre;
    private int cantidad;
    private int valor_unitario;

    public Productos(String nombre, int cantidad, int valor_unitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor_unitario = valor_unitario;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public int getQuantity() {
        return cantidad;
    }

    public void setQuantity(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUnitPrice() {
        return valor_unitario;
    }

    public void setUnitPrice(int valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public Object[] getObject(){
        return new Object[]{this.nombre, this.cantidad, this.valor_unitario, this.valor_unitario > 100000? "SQLite" : "MySQL"};
    }
     
    @Override
    public String toString() {
        return "Producto{" + "Nombre=" + this.nombre + ", Cantidad=" + this.cantidad + ", Precio por unidad=" + this.valor_unitario + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Productos other = (Productos) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (this.valor_unitario != other.valor_unitario) {
            return false;
        }
        return this.nombre.equalsIgnoreCase(other.nombre);
    }
    
    
}
