import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Test
{
    public static void main(String[] args)
    {
      String filename = args[0];
      String ext = filename.substring(filename.length()-3);
      String newfile = filename.substring(0, filename.length()-4)+"-steg."+ext;
      System.out.println(newfile);

    	BufferedImage image = null;
        try {

            image = ImageIO.read(new File(filename));

            ImageIO.write(image, "gif",new File("C:\\out.gif"));
            ImageIO.write(image, "png",new File("C:\\out.png"));
            ImageIO.write(image, ext ,new File(newfile));

        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");
    }
}
