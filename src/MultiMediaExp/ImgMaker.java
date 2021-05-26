package MultiMediaExp;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImgMaker extends Application{
	
	//fx组件部
	//imprt: 导入
	//exprt: 导出
	//paint: 画笔
	//Vflip/Hflip:翻转
	//NoiseClr: 去噪
	//GertScale: 灰度转换
	Stage sg;
	VBox ToolBox,ScrBar,paintBox;
	HBox RGBStore,FLIP,PORT;
	Button quit,imprt,exprt,paint,Vflip,Hflip,NoiseClr,backup,Gradient,invert;
	Hyperlink link = new Hyperlink("https://github.com/d10sama");

	TextField R,G,B;
	Label Caption_gs;
	ScrollBar GreyScale;
	BorderPane bp;
	//图像处理部
    private final Desktop desktop = Desktop.getDesktop();
    double ValueR,ValueG,ValueB;
	File f;
	private VBox imgContainer;
	private ImageView iv;
    private Image i;
    private WritableImage wi,wibk;
    private FileChooser fc;
	public static void main(String[] args)
	{
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Initiate(primaryStage);
		ImageLoader(primaryStage);
		Components();
	}
	void Initiate(Stage ps)
	{
		this.f=null;
		try {
			this.iv=new ImageView();
		}
		catch(Exception e) {System.out.println("垃圾java居然文件不能为空");}
		this.sg=ps;
		this.bp=new BorderPane();
		Label title=new Label("图像处理程序                  ");
		title.setFont(new Font("Bell MT",40));
		HBox TitleContainer=new HBox();
		this.imgContainer=new VBox();
		this.imgContainer.getChildren().add(iv);
		this.quit=new Button("quit");
		this.paint=new Button("铅笔绘图");
		this.backup=new Button("恢复原始图像");
		this.backup.setDisable(true);
		this.paint.setDisable(true);
		this.imprt=new Button("导入");
		this.exprt=new Button("导出");
		this.exprt.setDisable(true);
		this.FLIP=new HBox(5);
		this.Vflip=new Button("纵向翻转");
		this.Vflip.setDisable(true);
		this.Hflip=new Button("横向翻转");
		this.Hflip.setDisable(true);
		this.NoiseClr=new Button("卷积去噪");
		this.NoiseClr.setDisable(true);
		this.Caption_gs=new Label("对比度增强");
		this.GreyScale=new ScrollBar();
		this.Gradient=new Button("梯度转换");
		this.Gradient.setDisable(true);
		this.invert=new Button("Invert");
		this.invert.setDisable(true);
		
		this.ToolBox=new VBox();
		this.ScrBar=new VBox(10);
		this.PORT=new HBox(5);
		
		this.RGBStore=new HBox(0);
		this.paintBox=new VBox(5);
		this.R=new TextField();
		this.G=new TextField();
		this.B=new TextField();
		this.RGBStore.getChildren().addAll(new Label("R"),R,new Label("G"),G,new Label("B"),B);
		this.R.setPrefSize(50,20);
		this.G.setPrefSize(50,20);
		this.B.setPrefSize(50,20);
		this.paintBox.getChildren().addAll(this.paint,this.RGBStore);
		
		ScrBar.getChildren().addAll(Caption_gs, GreyScale);
		ScrBar.setBorder(new Border(new BorderStroke(Color.BROWN,Color.BROWN,Color.BROWN,Color.BROWN
				,BorderStrokeStyle.DASHED,BorderStrokeStyle.DASHED,
				BorderStrokeStyle.DASHED,BorderStrokeStyle.DASHED,
				null,
				new BorderWidths(2, 2, 2, 2, false, false, false, false)
				,new Insets(10))));
		this.FLIP.getChildren().addAll(this.Hflip,this.Vflip);
		this.PORT.getChildren().addAll(this.imprt,this.exprt);
		this.ToolBox.getChildren().addAll(new Label("工具箱"),this.PORT,
										this.invert,this.FLIP
										,NoiseClr,this.paintBox,this.ScrBar,
										this.Gradient,backup,quit,this.link);
		ToolBox.setAlignment(Pos.CENTER_LEFT);
		ToolBox.setPadding(new Insets(10));
		ToolBox.setSpacing(30);
		ToolBox.setBorder(new Border(new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK,Color.BLACK,
														BorderStrokeStyle.DOTTED,BorderStrokeStyle.DOTTED,
														BorderStrokeStyle.DOTTED,BorderStrokeStyle.DOTTED,
														null,
														new BorderWidths(5, 5, 5, 5, false, false, false, false)
														,new Insets(10))));
		title.setAlignment(Pos.CENTER);
		TitleContainer.setBackground(new Background(new BackgroundFill(Color.WHEAT,null,null)));
		TitleContainer.getChildren().add(title);
		TitleContainer.setAlignment(Pos.CENTER);
		this.ToolBox.setBackground(new Background(new BackgroundFill(Color.WHEAT,null,null)));
		this.imgContainer.setAlignment(Pos.TOP_LEFT);
		this.imgContainer.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		this.imgContainer.setBorder(new Border(new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK,Color.BLACK,
				BorderStrokeStyle.DOTTED,BorderStrokeStyle.DOTTED,
				BorderStrokeStyle.DOTTED,BorderStrokeStyle.DOTTED,
				null,
				new BorderWidths(5, 5, 5, 5, false, false, false, false)
				,new Insets(10))));
		this.bp.setTop(TitleContainer);
		this.bp.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		this.bp.setRight(ToolBox);
		this.bp.setCenter(imgContainer);
		Scene s=new Scene(bp,1024,768);
		ps.setScene(s);
		ps.setTitle("图像处理");
		ps.show();
	}
	void ImageLoader(Stage ps)
	{
		this.fc= new FileChooser();
		this.imprt.setOnMouseClicked(e->{
			this.backup.setDisable(false);
			this.paint.setDisable(false);
			this.exprt.setDisable(false);
			this.Vflip.setDisable(false);
			this.Hflip.setDisable(false);
			this.NoiseClr.setDisable(false);
			this.Gradient.setDisable(false);
			this.invert.setDisable(false);
			
			
			this.imgContainer.getChildren().remove(0);
			f=fc.showOpenDialog(this.sg);
			try {
				i=new Image(new FileInputStream(f));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			iv=new ImageView(i);
			iv.setPreserveRatio(true);
			if(i.getHeight()>600||i.getWidth()>600)
				iv.setFitHeight(600);
			this.imgContainer.setAlignment(Pos.CENTER);
			this.imgContainer.getChildren().add(iv);
			wi = new WritableImage((int)i.getWidth(),(int)i.getHeight());
			
		});
	}
	void Components()
	{
		
		this.quit.setOnMouseClicked(e->{
			sg.close();
		});
		
		this.exprt.setOnMouseClicked(e->{
			this.fc.getExtensionFilters().add(new ExtensionFilter("图片文件", "*.png","*.jpg", "*.bmp", "*.gif"));
			File file = fc.showSaveDialog(sg.getOwner());
			if (file != null) {
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", file);
				} catch (IOException e1) {
					this.MessageStage("保存时出错啦，程序将恢复为默认值");
				}
				iv=new ImageView();
			}
		});
		
		this.invert.setOnMouseClicked(e->{
			this.pixWithImage(3);
		});
		//恢复备份
		this.backup.setOnMouseClicked(e->{
			PixelReader pr = i.getPixelReader();
			wi= new WritableImage((int)i.getWidth(),(int)i.getHeight());
	        PixelWriter pw = wi.getPixelWriter();
	        for(int y = 0; y < i.getHeight()-1; y++){
	        	for(int x = 0; x < i.getWidth()-1; x++){
	        		pw.setColor(x, y, pr.getColor(x, y));
	        	}
	        }iv.setImage(wi);
		});
		//画笔部分
		this.paint.setOnMouseClicked(e->{
			try {
			this.ValueR=Double.parseDouble(this.R.getText().toString());
			this.ValueG=Double.parseDouble(this.G.getText().toString());
			this.ValueB=Double.parseDouble(this.B.getText().toString());
			}
			catch(Exception exp) {
				this.ValueR=255;
				this.ValueG=255;
				this.ValueB=255;
			}
			PixelReader pr = iv.getImage().getPixelReader();
	        PixelWriter pw = wi.getPixelWriter();
	        for(int y = 0; y < i.getHeight()-1; y++){
	        	for(int x = 0; x < i.getWidth()-1; x++){
	        		pw.setColor(x, y, pr.getColor(x, y));
	        	}
	        }
	        Color c=new Color(this.ValueR/255,this.ValueG/255,this.ValueB/255,1);
			iv.setOnMouseDragged(e3->
			{
		        int dx=(int)e3.getX();
		        int dy=(int)e3.getY();
		        pw.setColor(dx-1, dy-1, c);
		        pw.setColor(dx-1, dy, c);
		        pw.setColor(dx-1, dy+1, c);
		        pw.setColor(dx, dy-1, c);
		        pw.setColor(dx, dy, c);
		        pw.setColor(dx, dy+1, c);
		        pw.setColor(dx+1, dy-1, c);
		        pw.setColor(dx+1, dy, c);
		        pw.setColor(dx+1, dy+1, c);
			});
			iv.setOnMouseReleased(e4->{
				iv.setImage(wi);
			});
		});
		
		
		
		
		//翻转
		this.Vflip.setOnMouseClicked(e->{
			flip(false);
		});
		this.Hflip.setOnMouseClicked(e->{
			flip(true);
		});
		//卷积去噪
		this.NoiseClr.setOnMouseClicked(e->{
			Convolution();
		});
		//滚动条
		this.GreyScale.setMin(0.0);
		this.GreyScale.setValue(0.5);
		this.GreyScale.setMax(1.0);
		this.GreyScale.valueProperty().addListener(e->
		{
			//System.out.println(GreyScale.getValue());
			PixelReader pr = wi.getPixelReader();
	        PixelWriter pw = wi.getPixelWriter();
			 for(int y = 0; y < i.getHeight()-1; y++){
		        	for(int x = 0; x < i.getWidth()-1; x++){
		        		Color c= pr.getColor(x, y);
		        		if(GreyScale.getValue()>0.5)
		        			pw.setColor(x, y,c.deriveColor(0, 1.0, 1/GreyScale.getValue(), 1.0));
		        		else
		        			pw.setColor(x, y,c.deriveColor(0, 1.0, GreyScale.getValue(), 1.0));
		        	}
			 }iv.setImage(wi);
			 
		});
		this.Gradient.setOnMouseClicked(e->{
			this.MessageStage("说人话就是边缘强化");
			PixelReader pr = iv.getImage().getPixelReader();
	        PixelWriter pw = wi.getPixelWriter(); 
	        for(int y = 0; y < i.getHeight()-2; y++){
	        	for(int x = 0; x < i.getWidth()-2; x++){
	        		double gr=Math.abs(pr.getColor(x+1, y).getRed()-pr.getColor(x, y).getRed())
	        				+Math.abs(pr.getColor(x,y+1).getRed()-pr.getColor(x, y).getRed())
	        				+pr.getColor(x, y).getRed();
	        		
	        		double gg=Math.abs(pr.getColor(x+1, y).getGreen()-pr.getColor(x, y).getGreen())
	        				+Math.abs(pr.getColor(x,y+1).getGreen()-pr.getColor(x, y).getGreen())
	        				+pr.getColor(x, y).getGreen();
	        		
	        		double gb=Math.abs(pr.getColor(x+1, y).getBlue()-pr.getColor(x, y).getBlue())
	        				+Math.abs(pr.getColor(x,y+1).getBlue()-pr.getColor(x, y).getBlue())
	        				+pr.getColor(x, y).getBlue();
	        				
	        		pw.setColor(x, y, new Color(cReturn(gr),cReturn(gg),cReturn(gb),1));
	        	}
	        }
	        iv.setImage(wi);
		});
	}
	double cReturn(Double value)
	{
		return value<=1?value:1;
	}
	void Convolution()
	{
		double[][] conv= {{1/9,1/9,1/9},{1/9,1/9,1/9},{1/9,1/9,1/9}};
		PixelReader pr = iv.getImage().getPixelReader();
        PixelWriter pw = wi.getPixelWriter();
        for(int y = 1; y < i.getHeight()-2; y++){
        	for(int x = 1; x < i.getWidth()-2; x++){
        		Color c1= pr.getColor(x-1,y-1);
        		Color c2= pr.getColor(x,y-1);
        		Color c3= pr.getColor(x+1,y-1);
        		Color c4= pr.getColor(x-1,y);
        		Color c5= pr.getColor(x,y);
        		Color c6= pr.getColor(x+1,y);
        		Color c7= pr.getColor(x-1,y+1);
        		Color c8= pr.getColor(x,y+1);
        		Color c9= pr.getColor(x+1,y+1);
        		double ravg,gavg,bavg;
        		ravg=(c1.getRed()+c2.getRed()+c3.getRed()+
        				c4.getRed()+c5.getRed()+c6.getRed()+
        				c7.getRed()+c8.getRed()+c9.getRed())/9;
        		gavg=(c1.getGreen()+c2.getGreen()+c3.getGreen()+
        				c4.getGreen()+c5.getGreen()+c6.getGreen()+
        				c7.getGreen()+c8.getGreen()+c9.getGreen())/9;
        		bavg=(c1.getBlue()+c2.getBlue()+c3.getBlue()+
        				c4.getBlue()+c5.getBlue()+c6.getBlue()+
        				c7.getBlue()+c8.getBlue()+c9.getBlue())/9;
        		pw.setColor(x, y,new Color(ravg,gavg,bavg,1.0));
        	}
	 }iv.setImage(wi);
	}
	private void flip(boolean isVirtical)
	{
		PixelReader pr = iv.getImage().getPixelReader();
		wi = new WritableImage((int)i.getWidth(),(int)i.getHeight());
        PixelWriter pw = wi.getPixelWriter();
		 for(int y = 0; y < i.getHeight()-1; y++){
	        	for(int x = 0; x < i.getWidth()-1; x++){
	        		Color c= pr.getColor(x, y);
	        		int flipx=(isVirtical?(int)(i.getWidth()-x)-1:x);
	        		int flipy=(isVirtical?y:(int)(i.getHeight()-y)-1);
	        		pw.setColor(flipx, flipy,c);
	        	}
		 }iv.setImage(wi);
	}
	//0变亮，1变黑，2灰度转换，3颜色反转，4饱和加，5饱和减少
	private void pixWithImage(int type){
		PixelReader pixelReader = iv.getImage().getPixelReader();
        PixelWriter pixelWriter = wi.getPixelWriter();
        for(int y = 0; y < i.getHeight(); y++){
        	for(int x = 0; x < i.getWidth(); x++){
        		Color color = pixelReader.getColor(x, y);
        		switch (type) {
				case 0:
					color = color.brighter();
					break;
				case 1:
					color = color.darker();
					break;
				case 2:
					color = color.grayscale();
					break;
				case 3:
					color = color.invert();
					break;
				case 4:
					color = color.saturate();
					break;
				case 5:
					color = color.desaturate();
					break;
				default:
					break;
				}
        		pixelWriter.setColor(x, y, color);
        	}
        }
        iv.setImage(wi);
	}
	void MessageStage(String s)
	{
		Label tmpl=new Label(s);
		GridPane tmpp=new GridPane();
		Scene tmpc=new Scene(tmpp,500,300);
		Stage tmps=new Stage();
		tmps.setScene(tmpc);
		tmpp.add(tmpl, 0, 0);
		tmps.show();
	}
}
