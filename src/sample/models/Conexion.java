package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user = "topicos2021";
    private static String pwd = "123456789";
    private static String db = "musicadb";

    public static Connection conexion;

    public synchronized static void getConexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://"+server+":3306/"+db,user,pwd);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
