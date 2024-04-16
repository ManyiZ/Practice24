package open_cv;

import javax.swing.*;

public class Opencv {
    JWindow window;
    JFrame frame;

    Stream camera;

    public Opencv(Stream c){
//        window = new JWindow();
//        frame = new JFrame();

        camera = c;
    }


    public static void main(String[] args){
        Stream s = new Stream();
        Opencv window = new Opencv(s);


        s.close();
    }
}
