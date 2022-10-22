import java.util.Arrays;

public class Espace extends ChaineCar {
    
    private int size;

    public Espace() {
        this(1);
    }

    public Espace(int size) {
        super();
        this.size = size;
    }

    public int len() {
        return size;
    }

    public void setSize(int sz) {
        size = sz;
    }

    public String toString() {
        char[] array = new char[size];
        Arrays.fill(array, ' ');
        return new String(array);
    }
    
}
