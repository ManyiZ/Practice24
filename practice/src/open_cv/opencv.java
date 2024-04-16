package open_cv;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class opencv {
    JWindow window;
    JFrame frame;

    Stream camera;

    public opencv(){
        window = new JWindow();
        frame = new JFrame();

        camera = new Stream();
    }


    public static void main(String[] args){
        opencv cam = new opencv();

    }
}
