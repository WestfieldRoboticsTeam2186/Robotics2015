import java.util.logging.Level;
import java.util.logging.Logger;

import org.opencv.core.*;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.io.IOException;
public class TableListener extends Frame implements Runnable {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	Frame f = new Frame();
	BufferedImage image;
	byte[] b;
	
	int[] imgData;
	private Thread mThread;
	
	AxisCamera axis = new AxisCamera("http://10.21.86.20", "320x240");
	
	VideoCapture vcap;
	
	Mat img;
	public TableListener(){
		setTitle("Frame");
		setSize(400, 400);
		setVisible(true);
		this.addWindowListener(new WindowListener(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		init();
	}
	public void init(){
		
	}
	public void start(){
		if(mThread == null){
			mThread = new Thread(this);
			mThread.start();
		}
	}
	
	public void stop(){
		if(mThread != null){
			mThread.stop();
			mThread = null;
		}
	}
	public static void main(String[] args){
		TableListener win = new TableListener();
		win.start();
	}
	
	public void convert(Mat m){
		Mat m2 = new Mat();
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if(m.channels() > 1){
			Imgproc.cvtColor(m,  m2, Imgproc.COLOR_BGR2RGB);
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int blem = m.channels()*m.cols()*m.rows();
		if(b == null || b.length != blem){
			b = new byte[blem];
		}
		m2.get(0, 0, b);
		image = new BufferedImage(m.cols(), m.rows(), type);
		image.getRaster().setDataElements(0, 0, m.cols(), m.rows(), b);
	}
	
	public Mat convertToMat(int[] array){
		Mat convertedImg= new Mat();
		convertedImg.create(320, 240, CvType.CV_8UC3);
		convertedImg.put(0, 0, array);
		return convertedImg;
	}
	
	public Mat convertToMat(BufferedImage img){
		Mat retMat = new Mat();
		retMat.create(img.getWidth(), img.getHeight(), CvType.CV_8UC3);
		byte[] b = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
		retMat.put(0, 0, b);
		return retMat;
	}
	
	public synchronized void update(Graphics g){
		g.drawImage(image, 0, 0, (ImageObserver) this);
	}
	
	public void run(){
		while(true){
			try {
				image = axis.grab();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			repaint();
			
			try {
				Thread.sleep(50);
			} catch(InterruptedException e){}
		}
	}
}
