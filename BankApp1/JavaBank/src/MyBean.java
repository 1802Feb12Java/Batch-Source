import java.io.Serializable;

public class MyBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String one;
    private String two;

    public MyBean() {
    }

    public MyBean(String one, String two) {
        this.one = one;
        this.two = two;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

}