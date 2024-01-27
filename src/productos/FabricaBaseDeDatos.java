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
public class FabricaBaseDeDatos {
    private static final ArrayList<BaseDeDatos> db = new ArrayList<>();
    
    public static ArrayList<BaseDeDatos> getAllDbs(){
        db.add(ConexionSQL.getInstance());
        db.add(ConexionMysql.getInstance());
        return db;
    }
    
    public static BaseDeDatos fabrica(Productos producto){
        if (producto.getUnitPrice() > 100000) {
            return ConexionSQL.getInstance();
        }
        return ConexionMysql.getInstance();
    };
    
    
    public static BaseDeDatos getDb(String db){
        return switch (db.toLowerCase()){
            case "mysql" -> ConexionMysql.getInstance();
            case "sqlite" -> ConexionSQL.getInstance();
            default -> ConexionMysql.getInstance();
        };
    }
}
