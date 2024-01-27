/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package productos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexionSQL implements BaseDeDatos {

    private static ConexionSQL instanceSQLite;
    private Connection connection;
    private final String url = "jdbc:sqlite:C:\\Users\\Vanes\\Downloads\\sqlite-tools-win-x64-3450000\\productos.db";

    private ConexionSQL() {
        connect();
    }

    public static ConexionSQL getInstance() {
        if (instanceSQLite == null) {
            instanceSQLite = new ConexionSQL();
        }
        return instanceSQLite;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            System.out.println("Conexión con SQLite exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String agregarProducto(Productos producto) {
        String sql = "INSERT INTO productos (nombre, cantidad, valor_unitario) VALUES (?, ?, ?)";
        String message = "";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, producto.getName());
                preparedStatement.setInt(2, producto.getQuantity());
                preparedStatement.setInt(3, producto.getUnitPrice());
                preparedStatement.executeUpdate();
            }
            message = "Producto agregado exitosamente a SQLite";
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    @Override
    public String eliminarProducto(Productos producto) {
        String sql = "DELETE FROM productos WHERE nombre = ? AND cantidad = ? AND valor_unitario = ?";
        String message = "";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, producto.getName());
                preparedStatement.setInt(2, producto.getQuantity());
                preparedStatement.setInt(3, producto.getUnitPrice());
                preparedStatement.executeUpdate();
            }
            message = "Producto " + producto.getName() + " eliminado de SQLite";
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    @Override
    public ArrayList<Productos> listaProductos() {
        ArrayList<Productos> list = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()) {
                    Productos tmp = new Productos(res.getString("nombre"), res.getInt("cantidad"), res.getInt("valor_unitario"));
                    list.add(tmp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Productos getNombre(String name) {
        String sql = "SELECT * FROM productos WHERE nombre = ? LIMIT 1";
        Productos prd = null;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()) {
                    prd = new Productos(res.getString("nombre"), res.getInt("cantidad"), res.getInt("valor_unitario"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return prd;
    }

    @Override
    public int getCantidad() {
        String sql = "SELECT SUM(cantidad) AS total FROM productos";
        int total = 0;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()) {
                    total = res.getInt("total");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }

    @Override
    public int getTotal() {
        String sql = "SELECT SUM(cantidad * valor_unitario) AS total FROM productos";
        int total = 0;
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()) {
                    total = res.getInt("total");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
}
