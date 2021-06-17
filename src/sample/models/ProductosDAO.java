package sample.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductosDAO {

    private int id_producto;
    private String nombre_producto;
    private int precio;
    private int existencia;
    private String disponible;
    private String marca;
    private String modelo;
    private String categorias;
    private String imagen_producto;
    byte b[];

    public int getId_producto() {
        return id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getImagen_producto() {
        return imagen_producto;
    }

    public void setImagen_producto(String imagen_producto) {
        this.imagen_producto = imagen_producto;
    }

    public void INSERT(){
        try {

            String query = "INSERT INTO tbl_productos (nombre_producto, precio, existencia, disponible, marca, modelo, categorias, imagen_producto)" +
                    "VALUES('"+nombre_producto+"',"+precio+","+existencia+",'"+disponible+"', '"+marca+"', '"+modelo+"', '"+categorias+"', '"+imagen_producto+"');";

            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }  //MANDA DATOS

    public void UPDATE(){
        try {
            String query = "UPDATE tbl_productos SET nombre_producto = '"+nombre_producto+"', precio = "+precio+", " +
                    "existencia = "+existencia+", disponible = '"+disponible+"', marca = '"+marca+"', modelo = '"+
                    modelo+"', categorias = '"+categorias+"', imagen_producto = '"+imagen_producto+"' WHERE id_producto = "+id_producto;

            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }  //MANDA DATOS

    public void DELETE(){
        try {

            String query = "DELETE FROM tbl_productos WHERE id_producto = "+ id_producto+";";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }  //ACTUALIZA LA BD

    public ObservableList<ProductosDAO> SELECT(){
        ObservableList<ProductosDAO> listC = FXCollections.observableArrayList();

        try {

            ProductosDAO objc;

            String query = "SELECT * FROM tbl_productos ORDER BY nombre_producto ASC;";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()){
                objc = new ProductosDAO();
                objc.id_producto = res.getInt("id_producto");
                objc.nombre_producto = res.getString("nombre_producto");
                objc.precio = res.getInt("precio");
                objc.existencia = res.getInt("existencia");
                objc.disponible = res.getString("disponible");
                objc.marca = res.getString("marca");
                objc.modelo = res.getString("modelo");
                objc.categorias = res.getString("categorias");
                objc.imagen_producto = res.getString("imagen_producto");
                listC.add(objc);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  listC;
    }  //TRAE DATOS


}
