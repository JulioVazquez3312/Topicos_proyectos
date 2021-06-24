package sample.views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.models.ProductosDAO;
import javafx.scene.image.ImageView;

public class FormProductos extends Stage {
    private TableView<ProductosDAO> tableVProductos;
    private Button btnAdd;
    private VBox vBox;
    private Scene scene;
    private ProductosDAO objproDAO;

    public FormProductos() {
        objproDAO = new ProductosDAO();
        CrearUI();
        this.setTitle("Gestion de Productos");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        vBox = new VBox();
        tableVProductos = new TableView<>();
        btnAdd = new Button("Agragar una Cancion");
        btnAdd.setOnAction(event -> {
            new FrmProducto(tableVProductos,null);
        });
        vBox.getChildren().addAll(tableVProductos,btnAdd);
        CreaTabla();
        scene = new Scene(vBox, 880,450);
        scene.getStylesheets().add(getClass().getResource("../assets/estilos/estilo.css").toExternalForm());
    }

    private void CreaTabla() {
        TableColumn<ProductosDAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));

        TableColumn<ProductosDAO,String> tbcNombreProducto = new TableColumn<>("Nombre del producto");
        tbcNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));

        TableColumn<ProductosDAO, Integer> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ProductosDAO, Integer> tbcExistencia = new TableColumn<>("Existencia");
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        TableColumn<ProductosDAO,String> tbcDisponible = new TableColumn<>("Disponible");
        tbcDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        TableColumn<ProductosDAO, String> tbcMarca = new TableColumn<>("Marca");
        tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<ProductosDAO, String> tbcModelo = new TableColumn<>("Modelo");
        tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<ProductosDAO, String> tbcCategorias = new TableColumn<>("Categorias");
        tbcCategorias.setCellValueFactory(new PropertyValueFactory<>("categorias"));

        TableColumn<ProductosDAO, ImageView> tbcImagen = new TableColumn<>("Imagen");
        tbcImagen.setCellValueFactory(new PropertyValueFactory<>("imageView"));

        TableColumn<ProductosDAO, String> tblEdit = new TableColumn<>("Editar");
        tblEdit.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> param) {
                return new CellCustom(1);
            }
        });
        TableColumn<ProductosDAO, String> tblDelet = new TableColumn<>("Borrar");
        tblDelet.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO,String>>() {
            @Override
            public TableCell<ProductosDAO,String> call(TableColumn<ProductosDAO, String> param) {
                return new CellCustom(2);
            }
        });
        tableVProductos.getColumns().addAll(tbcIdProducto,tbcNombreProducto,tbcPrecio,tbcExistencia,tbcDisponible,
                tbcMarca, tbcModelo, tbcCategorias, tbcImagen,tblEdit,tblDelet);
        tableVProductos.setItems(objproDAO.SELECT());
    }
}
