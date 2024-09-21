package proxy.static1;

public class ProxyImage implements Image {
    @Override
    public void display() {
        System.out.println("proxy before");
        Image image = new RealImage();
        image.display();
        System.out.println("proxy after");
    }
}
