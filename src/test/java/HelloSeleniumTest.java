import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HelloSeleniumTest {
    //class variable
    WebDriver driver;

    //runs before every test
    @Before
    public void setUP(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    //runs after every test
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginTest() {
        //define how long to wait before failing.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //set up the url that will be tested
        driver.get("https://www.saucedemo.com/");
        //enter username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //enter password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click log in
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Test
    public void purchaseItem(){
        //define how long to wait before failing.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //set up the url that will be tested
        driver.get("https://www.saucedemo.com/");

        //******************* LOG IN ********************\\
        //enter a username
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        //enter a password
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        //click login
        driver.findElement(By.cssSelector(".btn_action")).click();

        //******************* PLACE ITEM IN CART AND CHECK OUT ********************\\
        //click the item
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //go to shopping cart
        driver.findElement(By.cssSelector("#shopping_cart_container")).click();
        //check out button
        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        //******************* FILL IN DETAILS AND PLACE ORDER ********************\\
        //enter first name
        driver.findElement(By.id("first-name")).sendKeys("first name");
        //enter last name
        driver.findElement(By.id("last-name")).sendKeys("last name");
        //enter post code
        driver.findElement(By.id("postal-code")).sendKeys("zip");
        //click continue
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();
        //click to finish
        driver.findElement(By.id("finish")).click();

        //******************* CHECK FINAL PAGE DISPLAYS WHAT IT SHOULD ********************\\
        Assert.assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
    }

    @Test
    public void dropDownTest(){
        //set up the url that will be tested
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //click the dropdown menu
        WebElement dropDownMenu= driver.findElement(By.id("dropdown"));
        dropDownMenu.click();
        //define the two different options from the dropdown box
        WebElement option1= driver.findElement(By.cssSelector("option[value='1']"));
        WebElement option2= driver.findElement(By.cssSelector("option[value='2']"));
        //click option 1
        option1.click();
        //assertions
        Assert.assertTrue(option1.isSelected());
        Assert.assertFalse(option2.isSelected());
    }

    @Test
    public void hoverTest(){
        //url being tested - starting point
        driver.get("https://the-internet.herokuapp.com/hovers");
        //set up actions so you can hover
        Actions actions = new Actions(driver);
        //define the first image
        WebElement firstImage=driver.findElement(By.className("figure"));
        //move to the first image
        actions.moveToElement(firstImage).perform();
        //find the element that displays the user text
        WebElement user1Text=driver.findElement(By.xpath("//*[contains(text(), 'name: user1')]"));
        //assertions
        Assert.assertTrue("User 1 appears here",user1Text.isDisplayed());
    }

    @Test
    public void rightClickTest(){
        //url being tested - starting point
        driver.get("https://the-internet.herokuapp.com/context_menu");
        //set up actions so you can right-click
        Actions actions = new Actions(driver);
        //find the box to right-click
        WebElement box=driver.findElement(By.id("hot-spot"));
        //right-click the box
        actions.contextClick(box).perform();
        //move to the alert box pop up
        driver.switchTo().alert().accept();
    }

    @Test
    public void keyPressesTest(){
        //url being tested - starting point
        driver.get("https://the-internet.herokuapp.com/key_presses");
        //set up actions so you can keypress
        Actions actions = new Actions(driver);
        //find and click the input box
        WebElement input = driver.findElement(By.id("target"));
        input.click();
        //input a key
        actions.sendKeys(Keys.NUMPAD5).pause(100).perform();
        //find the output element
        WebElement output = driver.findElement(By.id("result"));
        Assert.assertEquals("clicked num pad 5", "You entered: NUMPAD5", output.getText());
    }

    @Test
    public void getCSSValue(){
        //url being tested - starting point
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        //find the clickable icon element
        WebElement clickableIcon = driver.findElement(By.linkText("Clickable Icon"));
        //get the attribute from the element
        String link = clickableIcon.getAttribute("href");
        //assert the link is correct
        Assert.assertEquals("https://ultimateqa.com/link-success/",link);
        //assert the type of padding
        Assert.assertEquals("Expected padding-box", "padding-box", clickableIcon.getCssValue("background-origin"));
    }
}
