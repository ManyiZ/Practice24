package open_cv;

import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class Stream {

    // https://docs.opencv.org/4.x/dd/d43/tutorial_py_video_display.html
    VideoCapture cam;
    BufferedImage lastFrame;
    StopWatch timer;

    public Stream() {
        timer = new StopWatch();
        timer.start();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cam = new VideoCapture(0);
        openCamera();
    }

    public void openCamera() {
        if (!isOpened()) {
            System.out.println("cam not found");
        } else
            System.out.println("cam opened in " + timer.getTime(TimeUnit.SECONDS) + " seconds");

        timer.stop();
    }

    public void close(){
        cam.release();
        System.out.println("cam closed");
    }

    public boolean isOpened() {
        return cam.isOpened();
    }
}
