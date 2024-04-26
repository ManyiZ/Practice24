package open_cv;

import org.opencv.core.Core;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import uwcse.io.Input;

import javax.imageio.ImageIO;

import static org.opencv.highgui.HighGui.*;

public class Stream {

    // https://docs.opencv.org/4.x/dd/d43/tutorial_py_video_display.html
    private VideoCapture cam;
    private StopWatch timer;
    private Mat frame;

    private int filter;
    public static int maxMode;

    final String winName = "frame";


    //for some reason it takes forever to load the camera - around 17 seconds on my pc
    public Stream() {
        timer = new StopWatch();
        timer.start();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cam = new VideoCapture(0);

        frame = new Mat();
        namedWindow(winName);
        openCamera();
    }

//    public void displayFrame(){
//        isFrameValid();
//        destroyAllWindows();
//        imshow(winName, frame);
//        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);
//        waitKey();
//    }

    public Mat getFrame(){
        isFrameValid();
        return frame;
    }

    // returns an image for JFrame
    public BufferedImage getImage(){

        Mat img = getFrame(); // updates img
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, matOfByte);
        byte[] imgBytes = matOfByte.toArray();

        BufferedImage bufferedImage;
        InputStream in = new ByteArrayInputStream(imgBytes);
        try {
            bufferedImage = ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bufferedImage;

    }

    public int getFilter(){
        return filter % maxMode;
    }


    private void isFrameValid(){
        frame = new Mat();
        boolean checkFrame = cam.read(frame);
        if (!checkFrame){
            System.out.println("Error reading frame");
            close();
        }
        CameraMode c = new CameraMode();
        c.filter(frame, 2);
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
        destroyAllWindows();
        System.out.println("cam closed");
    }

    public boolean isOpened() {
        return cam.isOpened();
    }

    public void setFilter(int filter){
        this.filter = filter;
    }

}
