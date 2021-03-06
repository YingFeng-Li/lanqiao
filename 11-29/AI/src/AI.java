import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;

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
class MainCanvas extends Canvas	 implements Runnable
{
	int HeroX,HeroY;
	int BossX,BossY;
	int Flag;
	int time;

	Image currentImg;
	Image BossImg;
	Image heroImg[][]=new Image[4][3];

	Thread thread;//线程
	Random rd=new Random();//系统时间作为种子seed
	public MainCanvas(){
		try{
			//向下
			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}
			System.out.println("heroImg.length:"+heroImg.length);
			System.out.println("heroImg[1].length:"+heroImg[1].length);
			//赋初值
			time=0;
			Flag=1;

			BossX=0;
			BossY=0;
		    HeroX=120;
			HeroY=100;
			BossImg=Image.createImage("/zuzu000.png");
			currentImg=heroImg[0][1];

			thread=new Thread(this);
			thread.start();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){
			int rdNumber=rd.nextInt(10);

			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}

			if(rdNumber%10000==0){//有三分之一的概率追主角
			if(BossX<HeroX){
				BossX++;
			}else{
				BossX--;
			}

			if(BossY<HeroY){
				BossY++;
			}else{
				BossY--;
			}
			}
			repaint();
		}	
	} 

	public void paint(Graphics g){
		g.setColor(250,200,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,HeroX,HeroY,0);
		g.drawImage(BossImg,BossX,BossY,0);
	}

//重复
	public void keyRepeated(int keyCode){
		int action=getGameAction(keyCode);
		if(action==DOWN){
			currentImg=heroImg[0][2];	
			System.out.println("芜湖");
			HeroY++;
			repaint();
		}
		if(action==LEFT){
			currentImg=heroImg[1][2];
			HeroX--;
			repaint();
		}
		if(action==UP){
			currentImg=heroImg[2][2];
			HeroY--;
			repaint();
		}
		if(action==RIGHT){
			currentImg=heroImg[3][2];
			HeroX++;
			repaint();
		}

	}

//释放
	public void keyReleased(int keyCode){
		int action=getGameAction(keyCode);
		if(action==DOWN){
			//延迟
			delay();
			currentImg=heroImg[0][1];	
			repaint();
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


//方法
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
		//延迟
		delay();
		repaint();	
	}

//延迟
	public void delay(){
	try{
			Thread.sleep(time);
			System.out.println("延迟"+time+"ms");
		}catch(Exception e){}	
	}

//按键
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		//下
		if(action==DOWN){
			changImgAndDirect(0);
			HeroY=HeroY+2;
			System.out.println("down");
		}
		
		//左
		else if(action==LEFT){
			changImgAndDirect(1);
			HeroX=HeroX-2;
			System.out.println("left");
		}

		//上
		if(action==UP){
			changImgAndDirect(2);
			HeroY=HeroY-2;
			System.out.println("up");
		}

		//右
		if(action==RIGHT){
			changImgAndDirect(3);
			HeroX=HeroX+2;
			System.out.println("right");
		}
	}
}