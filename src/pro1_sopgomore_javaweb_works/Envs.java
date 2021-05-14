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
	CFmid[] mids;
	CFside[] top;
	CFside[] left;
	CFside[] rig;
	CFside[] bottom;
	CFcorner ul,ur,dl,dr;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane cf=new GridPane();
		CourtField(cf);
		
		Scene scene = new Scene(cf,1324,800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
	    launch(args);
	}
	void CourtField(GridPane cf)
	{
		int PosDetect=0;
		cf.setAlignment(Pos.TOP_CENTER);
		ul=new CFcorner(0);
		mids=new CFmid[234];
		top=new CFside[18];
		left=new CFside[13];
		rig=new CFside[13];
		bottom=new CFside[18];
		ur=new CFcorner(1);
		dl=new CFcorner(3);
		dr=new CFcorner(2);
		cf.add(ul,0,0);
		while(PosDetect<13)
		{	
			left[PosDetect]=new CFside(0);
			cf.add(left[PosDetect],0,PosDetect+1);
			PosDetect++;
		}
		cf.add(dl,0,14);
		PosDetect=0;
		for(int i=0;i<18;i++)
			for(int j=0;j<15;j++)
			{
				switch(j)
				{
				case 0:top[i]=new CFside(1);
						cf.add(top[i], i+1, j);
					break;
				case 14:
					bottom[i]=new CFside(3);
					cf.add(bottom[i], i+1, j);
					break;
				default:
					mids[i*(j-1)]=new CFmid();
					cf.add(mids[i*(j-1)], i+1, j);
					break;
				
				}
			}
		cf.add(ur, 19, 0);
		while(PosDetect<13)
		{
			rig[PosDetect]=new CFside(2);
			cf.add(rig[PosDetect], 19, PosDetect+1);
			PosDetect++;
		}
		cf.add(dr, 19, 14);
	}
}
