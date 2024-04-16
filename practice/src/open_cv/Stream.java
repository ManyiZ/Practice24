package open_cv;

import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Stream {

    VideoCapture cam;
    String dep;
    private ScheduledExecutorService timer;

    BufferedImage lastFrame;

    public Stream() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cam = new VideoCapture(0);
    }

    public void openCamera() {
        if(!isOpened()){
            System.out.println("cam not found");
            return;
        }

    }

    public void close(){
        cam.release();
    }

    public boolean isOpened() {
        return cam.isOpened();
    }
}
