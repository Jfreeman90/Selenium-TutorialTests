import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Class for elements and details for - https://www.saucedemo.com/
public class LoginPage {
    //private driver for this class
    private WebDriver driver;

    //return the username element
    private WebElement getUserNameElement(){
        return driver.findElement(By.id("user-name"));
    }

    //return the password element
    private WebElement getPasswordElement(){
        return driver.findElement(By.id("password"));
    }

    //method to log in the user for this particular page
    private void loginMethod(String username, String password){
        getUserNameElement().sendKeys(username);
        getPasswordElement().sendKeys(password);
        driver.findElement(By.className("btn_action")).submit();
    }
}
