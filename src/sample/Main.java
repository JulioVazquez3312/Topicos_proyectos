package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Conexion;
import sample.views.*;

public class Main extends Application {

    private VBox vBox;
    private MenuBar mnbarPrincipal;
    private Menu menComp1, menComp2, menCerrar;
    private MenuItem meItCRUD, meItBaNa, meItSalir;
    private Scene escena;

    @Override
    public void start(Stage primaryStage) throws Exception{
        CrearUI();

        primaryStage.setTitle("Proyectos");
        primaryStage.setScene(escena);
        primaryStage.setMaximized(true);
        primaryStage.show();

        Conexion.getConexion();
    }

    private void CrearUI() {

        vBox = new VBox();

        mnbarPrincipal = new MenuBar();
        menComp1 = new Menu("Batalla naval");
        menComp2 = new Menu("CRUD");
        menCerrar = new Menu("Cerrar");
        mnbarPrincipal.getMenus().addAll(menComp1, menComp2, menCerrar);

        meItBaNa = new MenuItem("Batalla naval");
        meItBaNa.setOnAction(event -> opcionesMenu(1));

        meItCRUD = new MenuItem("Productos, Base de datos");
        meItCRUD.setOnAction(event -> opcionesMenu(2));

        menComp1.getItems().addAll(meItBaNa);

        menComp2.getItems().addAll(meItCRUD);

        meItSalir = new MenuItem("Salir");
        meItSalir.setOnAction( event -> { System.exit(0);});

        menCerrar.getItems().add(meItSalir);

        vBox.getChildren().add(mnbarPrincipal);

        escena = new Scene(vBox, 300,100);
        escena.getStylesheets().add(getClass().getResource("estilos/estilo2.css").toExternalForm());
    }

    private void opcionesMenu(int opc) {
        switch (opc){
            case 1: new BatNav(); break;
            case 2: new FormProductos(); break;

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
