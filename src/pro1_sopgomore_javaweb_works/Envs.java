package pro1_sopgomore_javaweb_works;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Envs extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane Background=new BorderPane();
		Scene scene = new Scene(Background,1024,768);
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
	    launch(args);
	}
}
