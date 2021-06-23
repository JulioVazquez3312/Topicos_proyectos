package sample.views;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.ProductosDAO;

import java.util.Optional;

public class CellCustom extends TableCell<ProductosDAO, String> {

    private Button btnCelda;
    private ProductosDAO objproDAO;

    public CellCustom(int opc) {

        if (opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objproDAO = CellCustom.this.getTableView().getItems().get(CellCustom.this.getIndex());
                new FrmProducto(CellCustom.this.getTableView(), objproDAO);
            });
        } else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText("Confirmacion");
                alert.setContentText("¿Quiere usted borrar esta cancion?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    objproDAO = CellCustom.this.getTableView().getItems().get(CellCustom.this.getIndex());
                    objproDAO.DELETE();

                    CellCustom.this.getTableView().setItems(objproDAO.SELECT());
                    CellCustom.this.getTableView().refresh();
                }
            });
        }
    }
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            setGraphic(btnCelda);
        }
    }
}
