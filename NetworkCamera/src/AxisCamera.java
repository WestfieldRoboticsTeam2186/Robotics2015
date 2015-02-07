import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.*;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Class which encapsulates access to the robot's ethernet camera.
 *
 * @author sid
 */
public class AxisCamera
{
    protected String host;
    protected String res = "320x240";
    protected String grabPage = "/axis-cgi/jpg/image.cgi?resolution=";
    protected String streamPage = "/mjpg/video.mjpg";

    protected BufferedImage lastImg;
    protected byte[] lastImgArr;

    public AxisCamera(String h)
    {
        host = h;
        grabPage += res;
    }

    public AxisCamera(String h, String r)
    {
        host = h;
        grabPage += r;

        lastImg = null;
        lastImgArr = null;
    }

    public AxisCamera(String h, String gp, String r, String sp)
    {
        host = h;
        grabPage = gp + r;
        streamPage = sp;

        lastImg = null;
        lastImgArr = null;
    }

    public BufferedImage grab() throws IOException
    {
        return lastImg = ImageIO.read(new URL(host + grabPage));
    }

    public Mat grabAsMat() throws IOException
    {
        BufferedImage img = grab();
        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        byte[] data = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
        
        
        Mat mat = new Mat(img.getWidth(), img.getHeight(), CvType.CV_8UC3);
        mat.put(0, 0, data);
        

        lastImg = img;
        lastImgArr = data;

        return mat;
    }

    public InputStream openMjpgStream() throws IOException
    {
        return new URL(host + streamPage).openStream();
    }
}