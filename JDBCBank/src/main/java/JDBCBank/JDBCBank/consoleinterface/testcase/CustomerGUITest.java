package JDBCBank.JDBCBank.consoleinterface.testcase;

import JDBCBank.JDBCBank.consoleinterface.CustomerGUI;
import junit.framework.TestCase;

public class CustomerGUITest extends TestCase {
	CustomerGUI cgui = new CustomerGUI();
	boolean value = cgui.checkString("this is a string");
	
	//void assertEquals(true, value);
}
