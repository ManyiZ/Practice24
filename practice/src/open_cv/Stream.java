package open_cv;

import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import static org.opencv.highgui.HighGui.imshow;

public class Stream {

    // https://docs.opencv.org/4.x/dd/d43/tutorial_py_video_display.html
    VideoCapture cam;
    StopWatch timer;
    Mat frame;

    //for some reason it takes forever to load the camera - around 17 seconds on my pc
    public Stream() {
        timer = new StopWatch();
        timer.start();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cam = new VideoCapture(0);

        frame = new Mat();
        openCamera();
    }

    public void displayFrame(){
        isFrameValid();
        imshow("frame", frame);
    }

    public Mat getFrame(){
        isFrameValid();
        return frame;
    }

    private boolean isFrameValid(){
        boolean checkFrame = cam.read(frame);
        if (!checkFrame){
            System.out.println("Error reading frame");
            close();
        }
        return checkFrame;
    }


    // checks for a valid camera connection
    public void openCamera() {
        if (!isOpened()) {
            System.out.println("cam not found");
        } else
            System.out.println("cam opened in " + timer.getTime(TimeUnit.SECONDS) + " seconds");

        timer.stop();
    }

    // shuts off the camera
    public void close(){
        cam.release();
        System.out.println("cam closed");
    }

    public boolean isOpened() {
        return cam.isOpened();
    }
}
