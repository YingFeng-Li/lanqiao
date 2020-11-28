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
	int leftFlag,rightFlag,upFlag,downFlag;

	Image currentImg;
	Image heroImage[][]=new Image[4][3];//
/*	Image heroLeftImage[]=new Image[3];
	Image heroRightImage[]=new Image[3];
	Image heroUpImage[]=new Image[3];
	Image heroDownImage[]=new Image[3];
*/
	public MainCanvas(){
		try
		{

			
			//左
			for(int i=0;i<heroImage.length;i++){
				for(int j=0;j<heroImage[i].length;j++){
				heroImage[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}

			//右
	/*		for(int i=0;i<heroRightImage.length;i++){
				heroRightImage[i]=Image.createImage("/sayo"+i+"6.png");
			}

			//上
			for(int i=0;i<heroUpImage.length;i++){
				heroUpImage[i]=Image.createImage("/sayo"+i+"4.png");
			}
			
			//下
			for(int i=0;i<heroDownImage.length;i++){
				heroDownImage[i]=Image.createImage("/sayo"+i+"0.png");
			}*/


			//给变量赋初值
			leftFlag=1;
			rightFlag=1;
			upFlag=1;
			downFlag=1;
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

public void keyPressed(int keyCode){

	int action=getGameAction(keyCode);
	
	if(action==LEFT){
		if(leftFlag==1){
			currentImg=heroImage[0][0];
			leftFlag++;
		}
		else if(leftFlag==2){
			currentImg=heroImage[0][2];
			leftFlag=1;
		}
		System.out.println("left");
		x--;
		repaint();
		}

	else if(action==UP){
		if(upFlag==1){
			currentImg=heroImage[1][0];
			upFlag++;
		}
		else if(upFlag==2){
			currentImg=heroImage[1][2];
			upFlag=1;
		}
		System.out.println("up");
		y--;
		repaint();
		}

	else if(action==DOWN){
		if(downFlag==1){
			currentImg=heroImage[2][0];
			downFlag++;
		}
		else if(downFlag==2){
			currentImg=heroImage[2][2];
			downFlag=1;
		}
		System.out.println("down");
		y++;
		repaint();
		}

	else if(action==RIGHT){
		if(rightFlag==1){
			currentImg=heroImage[3][0];
			rightFlag++;
		}
		else if(rightFlag==2){
			currentImg=heroImage[3][2];
			rightFlag=1;
		}
		x++;
		System.out.println("right");
		repaint();
		}
	}
}

