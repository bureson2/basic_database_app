package cz.cvut.fel.pjv.jpa30;


import cz.cvut.fel.pjv.jpa30.FXMLControllers.FXMLControllers;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){
        launch();          
}

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLControllers.MenuPage.getPage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Best Tea");
        stage.setResizable(false);
        stage.show();
    }
}
