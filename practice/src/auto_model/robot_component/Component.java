package auto_model.robot_component;

public class Component {
    private double data;

    public Component(double initialData){
        data = initialData;
    }

    public void update(){
        data++;
    }

    public double getData(){
        return data;
    }
}
