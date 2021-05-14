package pro1_sopgomore_javaweb_works;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Envs extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane Field=new GridPane();
		Field.setAlignment(Pos.CENTER);
		Field.setVgap(0);
		Field.setHgap(0);
		Field.setHgap(20);
		Field.setVgap(20);
		CFmid[] b=new CFmid[400];
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
		{	b[i*j]=new CFmid();
			b[i*j].setPrefSize(20, 20);
			b[i*j].setText(String.format("%d", i*j));
			Field.add(b[i*j],i,j);
		}
		
		Scene scene = new Scene(Field,1024,768);
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
	    launch(args);
	}
}
