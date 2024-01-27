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
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMysql implements BaseDeDatos {

    private static ConexionMysql instanceMySQL;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/productos";
    private final String user = "root";
    private final String password = "";

    private ConexionMysql() {
        connect();
    }

    public static ConexionMysql getInstance() {
        if (instanceMySQL == null) {
            instanceMySQL = new ConexionMysql();
        }
        return instanceMySQL;
    }

    public void connect() {
        try {
            Logger.getLogger("com.mysql.cj.jdbc.Driver").setLevel(Level.WARNING);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            Logger.getLogger("com.mysql.cj.jdbc.Driver").addHandler(consoleHandler);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Conexión exitosa a MySQL");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String agregarProducto(Productos producto) {
        String message = "";
        try {
            String query = "INSERT INTO productos (nombre, cantidad, valor_unitario) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, producto.getName());
                preparedStatement.setInt(2, producto.getQuantity());
                preparedStatement.setInt(3, producto.getUnitPrice());
                preparedStatement.executeUpdate();
            }
            message = "Producto agregado exitosamente a MySQL";
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    @Override
    public String eliminarProducto(Productos producto) {
        String message = "";
        try {
            String query = "DELETE FROM productos WHERE nombre = ? AND cantidad = ? AND valor_unitario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, producto.getName());
                preparedStatement.setInt(2, producto.getQuantity());
                preparedStatement.setInt(3, producto.getUnitPrice());
                preparedStatement.executeUpdate();
            }
            message = "Producto " + producto.getName() + " eliminado de MySQL";
        } catch (SQLException e) {
            message = "Error: " + e.getMessage();
        }
        return message;
    }

    @Override
    public ArrayList<Productos> listaProductos() {
        ArrayList<Productos> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM productos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Productos tmp = new Productos(resultSet.getString("nombre"), resultSet.getInt("cantidad"), resultSet.getInt("valor_unitario"));
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
        // Implement this method as needed
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCantidad() {
        int total = 0;
        try {
            String query = "SELECT cantidad FROM productos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    total += resultSet.getInt("cantidad");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    @Override
    public int getTotal() {
        int total = 0;
        try {
            String query = "SELECT cantidad, valor_unitario FROM productos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    total += resultSet.getInt("cantidad") * resultSet.getInt("valor_unitario");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }
}
