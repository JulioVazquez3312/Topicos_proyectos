package sample.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.sql.PreparedStatement;
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
    private File file;

    private ImageView imageView;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void INSERT(){
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            PreparedStatement query = Conexion.conexion.prepareStatement("INSERT INTO tbl_productos (nombre_producto, precio, existencia, disponible, marca, modelo, categorias, imagen_producto)" +
                            "VALUES(?,?,?,?,?,?,?,?);" );
/*
            String query = "INSERT INTO tbl_productos (nombre_producto, precio, existencia, disponible, marca, modelo, categorias, imagen_producto)" +
                    "VALUES('"+nombre_producto+"',"+precio+","+existencia+",'"+disponible+"', '"+marca+"', '"+modelo+"', '"+categorias+"', '"+imagen_producto+"');";
 */
            query.setString(1,nombre_producto);
            query.setInt(2,precio);
            query.setInt(3,existencia);
            query.setString(4,disponible);
            query.setString(5,marca);
            query.setString(6,modelo);
            query.setString(7,categorias);
            query.setBinaryStream(8,fileInputStream, (int) file.length());
            query.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }  //MANDA DATOS

    public void UPDATE(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            PreparedStatement query = Conexion.conexion.prepareStatement("UPDATE tbl_productos SET nombre_producto = ?, precio = ?, " +
                    "existencia = ?, disponible = ?, marca = ?, modelo = ?, categorias = ?, imagen_producto =?  WHERE id_producto = "+id_producto);

            query.setString(1,nombre_producto);
            query.setInt(2,precio);
            query.setInt(3,existencia);
            query.setString(4,disponible);
            query.setString(5,marca);
            query.setString(6,modelo);
            query.setString(7,categorias);
            query.setBinaryStream(8,fileInputStream, (int) file.length());
            query.executeUpdate();



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
        ObservableList<ProductosDAO> list = FXCollections.observableArrayList();

        try {
            ProductosDAO obj;

            String query = "SELECT *  FROM tbl_productos ORDER BY nombre_producto ASC;";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()){
                obj = new ProductosDAO();
                obj.id_producto = res.getInt("id_producto");
                obj.nombre_producto = res.getString("nombre_producto");
                obj.precio = res.getInt("precio");
                obj.existencia = res.getInt("existencia");
                obj.disponible = res.getString("disponible");
                obj.marca = res.getString("marca");
                obj.modelo = res.getString("modelo");
               obj.categorias = res.getString("categorias");

                InputStream img = res.getBinaryStream("imagen_producto");
                OutputStream outputStream = new FileOutputStream(new File("photo.jpg"));
                byte [] content = new byte[1024];
                int size = 0;
                while ((size = img.read(content)) != -1){
                    outputStream.write(content, 0, size);
                }
                outputStream.close();
                img.close();

                Image image = new Image("file:photo.jpg", 150, 150,true,true);

                ImageView imgView = new ImageView(image);
                imgView.setFitHeight(150);
                imgView.setFitWidth(150);
                imgView.setPreserveRatio(true);

                obj.imageView = imgView;

                list.add(obj);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  list;
    }  //TRAE DATOS
}
