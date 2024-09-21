package proxy.static1;

public class RealImage implements Image {
    @Override
    public void display() {
        System.out.println("real image");
    }
}
