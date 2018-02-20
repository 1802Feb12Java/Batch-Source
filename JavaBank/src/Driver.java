import java.io.*;

public class Driver
{
    public static void main(String args[]) {

        try {


                FileInputStream fis = new FileInputStream("file.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                BankApp app = (BankApp) ois.readObject();
                ois.close();


            System.out.println(app.x);
            app.run();
            app.x = app.x + 10;

            FileOutputStream fos = new FileOutputStream("file.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(app);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
