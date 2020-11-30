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
	int x,y;
	int Flag;

	Image currentImg;
	Image heroImg[][]=new Image[4][3];

	public MainCanvas(){
		try{
			//ÏòÏÂ
			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}

			//¸³³õÖµ
			Flag=1;
			x=120;
			y=100;
			currentImg=heroImg[0][1];
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(250,200,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}

//ÖØ¸´
	public void keyRepeated(int keyCode){
		int action=getGameAction(keyCode);
		if(action==DOWN){
			currentImg=heroImg[0][2];	
			System.out.println("Îßºş");
			y++;
			repaint();
		}
		if(action==LEFT){
			currentImg=heroImg[1][2];
			x--;
			repaint();
		}
		if(action==UP){
			currentImg=heroImg[2][2];
			y--;
			repaint();
		}
		if(action==RIGHT){
			currentImg=heroImg[3][2];
			x++;
			repaint();
		}

	}

//ÊÍ·Å
	public void keyReleased(int keyCode){
		int action=getGameAction(keyCode);
		if(action==DOWN){
		//ÑÓ³Ù
		try{
			Thread.sleep(1000);
			System.out.println("ÑÓ³Ù");
		}catch(Exception e){}

			currentImg=heroImg[0][1];	
			System.out.println("Îßºş");
			repaint();
			System.out.println("heroImg.length:"+heroImg.length);
			System.out.println("heroImg[1].length:"+heroImg[1].length);
		}
		if(action==LEFT){
			delay();
			currentImg=heroImg[1][1];	
			repaint();
		}
		if(action==UP){
			delay();
			currentImg=heroImg[2][1];
			repaint();
		}
		if(action==RIGHT){
			delay();
			currentImg=heroImg[3][1];
			repaint();
		}

	}

//·½·¨
	public void changImgAndDirect(int direction){

		if(Flag==1){
			currentImg=heroImg[direction][0];
			Flag++;
			System.out.println("Flag1:"+Flag);
		}
		if(Flag==2){
			currentImg=heroImg[direction][2];
			Flag=1;
		}
		//ÑÓ³Ù
		try{
			Thread.sleep(1000);
			System.out.println("ÑÓ³Ù");
		}catch(Exception e){}

		System.out.println("Flag:"+Flag);
		repaint();	
	}

//ÑÓ³Ù
	public void delay(){
	try{
			Thread.sleep(1000);
			System.out.println("ÑÓ³Ù");
		}catch(Exception e){}	
	}

//°´¼ü
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		//ÏÂ
		if(action==DOWN){
			changImgAndDirect(0);
			y=y+2;
			System.out.println("down");
		}
		
		//×ó
		else if(action==LEFT){
			changImgAndDirect(1);
			x=x-2;
			System.out.println("left");
		}

		//ÉÏ
		if(action==UP){
			changImgAndDirect(2);
			y=y-2;
			System.out.println("up");
		}

		//ÓÒ
		if(action==RIGHT){
			changImgAndDirect(3);
			x=x+2;
			System.out.println("right");
		}
	}
}