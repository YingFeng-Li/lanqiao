import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas
{	
	//坐标与移动判断的定义
	int x,y;
	int Flag;

	Image currentImg;
	Image heroImage[][]=new Image[4][3];//

	public MainCanvas(){
		try
		{

			
			//左
			for(int i=0;i<heroImage.length;i++){
				for(int j=0;j<heroImage[i].length;j++){
				heroImage[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}

			//给变量赋初值
			Flag=1;
			currentImg=heroImage[0][1];

			x=120;
			y=120;


		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	
	
	
	}
	public void paint(Graphics g){
		g.setColor(100,0,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}

public void changeImgAndMove(int direction){
	if(Flag==1){
		currentImg=heroImage[direction][0];
		Flag++;
	}
	else if(Flag==2){
		currentImg=heroImage[direction][2];
		Flag=1;
	}
	repaint();
	}

public void again(){
	for(int g=0;g<3;g++){
		changeImgAndMove(0);
		System.out.println("向左");
		}
	}

public void keyPressed(int keyCode){

	int action=getGameAction(keyCode);
	
	if(action==LEFT){
		again();
		System.out.println("left");
		x--;
		}

	if(action==UP){
		changeImgAndMove(1);
		System.out.println("up");
		y--;
		}

	if(action==DOWN){
		changeImgAndMove(2);
		System.out.println("down");
		y++;
		}

	if(action==RIGHT){
		changeImgAndMove(3);
		x++;
		System.out.println("right");
		}
	}
}

