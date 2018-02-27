import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.bankingappPT2.menus.Menus;

public class testMenus {

	
	
	//@Test
	public void mainMenuTest() {
		Menus.mainMenu();
	}
	
	@Test
	public void clearTest() {
		Menus.mainMenu();
		Menus.mainMenu();
		
		Menus.ip();
	}
	
	
	
}



