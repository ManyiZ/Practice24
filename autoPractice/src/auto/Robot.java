package auto;

public class Robot {
    Component part;

    public Robot(){
        part = new Component(0);
    }

    public void update(){
        part.update();
    }

    public void displayData(){
        System.out.println(part.getData());
    }
}
