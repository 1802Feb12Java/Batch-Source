import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {


// do your thing

// optionally, reset System.in to its original

    @Test
    void integer_input()
    {
        System.out.println("hey");
        Input reader=new Input();
        System.out.println("hey");
        String user=reader.string();
        System.out.println("hey");
        System.out.println(user);

    }
}