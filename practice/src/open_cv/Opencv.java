package open_cv;

import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class Opencv implements ActionListener {
    public static void main(String[] args){
        File f = new File("libs/face_detection_yunet_2023mar.onnx");
        System.out.println(f.isFile());
        Opencv cvRunner = new Opencv();
//        int counter = 0;


        while (cvRunner.getTime() < 60) {
            cvRunner.update();
//            counter++;
        }

        System.out.println("cam timed out");
        cvRunner.close();

    }
    JWindow window;
    JFrame frame;
    JPanel panel;

    Stream camera;
    BufferedImage img;
    StopWatch timer;

    public Opencv(){
        window = new JWindow();
        frame = new JFrame();
        panel = new JPanel();

        camera = new Stream();
        timer = new StopWatch();
        timer.start();

        init();
    }



    public void update(){
        updateImg();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.getContentPane().remove(0);
        frame.pack();
    }

    public int getTime(){
        return (int) timer.getTime(TimeUnit.SECONDS);
    }

    public void close(){
        camera.close();
    }

    private void updateImg(){
        img = camera.getImage();
    }

    private void init(){
        frame.getContentPane().add(new JLabel(new ImageIcon(camera.getImage())));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String keyPressed = e.getActionCommand();

        if (Integer.parseInt(keyPressed) < 10){
            camera.setFilter(Integer.parseInt(keyPressed));
        }else {
            System.out.println("not a number");
        }

    }
}
