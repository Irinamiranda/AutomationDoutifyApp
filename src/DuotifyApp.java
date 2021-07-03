import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class DuotifyApp {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kmira\\Downloads\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");

        String currentTitle = driver.getTitle();
        Assert.assertTrue(currentTitle.equals("Welcome to Duotify!"));

        String userName = generateUserName();
        String firstName = generateName();

        String lastName = generateName();

        String email = generateEmail();
        String password = generateUserName();


        driver.findElement(By.id("hideLogin")).click();
        driver.findElementById("username").sendKeys(userName + Keys.ENTER);

        driver.findElementById("firstName").sendKeys(firstName + Keys.ENTER);
        driver.findElementById("lastName").sendKeys(lastName + Keys.ENTER);

        driver.findElementById("email").sendKeys(email + Keys.ENTER);
        driver.findElementById("email2").sendKeys(email + Keys.ENTER);

        driver.findElementById("password").sendKeys(password + Keys.ENTER);
        driver.findElementById("password2").sendKeys(password );

        driver.findElementByName("registerButton").click();

        //Verify that we are on the register page
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));
        Thread.sleep(1000);

        //In the left navigation bar, verify that your username (first+lastname) is the combination of the same first and last name that you used when signing up.
        WebElement nameFirstAndLast = driver.findElementById("nameFirstAndLast");
        String name = nameFirstAndLast.getText();
        Assert.assertTrue(name.equals(firstName + " " + lastName));
        nameFirstAndLast.click();
        Thread.sleep(2000);

        //      verify the username on the main window is correct and then click logout.
        String userInfo = driver.findElementByTagName("h1").getText();
        Assert.assertTrue(userInfo.equals(firstName + " " + lastName));

        List<WebElement> list = driver.findElementsByCssSelector(".buttonItems button");
        list.get(1).click();
        Thread.sleep(500);

        //Verify that you are logged out by verifying the URL is:
        //http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php
        String logoutURP = driver.getCurrentUrl();
        Assert.assertTrue(logoutURP.equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php"));


        //Login with valid credentials
        driver.findElementById("loginUsername").sendKeys(userName);
        driver.findElementById("loginPassword").sendKeys(password);
//        ((ChromeDriver) driver).findElementByName("loginButton").click();
        driver.findElementByCssSelector("#loginForm button").click();
        Thread.sleep(500);

//
//      Verify successful login by verifying that the home page contains the text "You Might Also Like".
        String option = driver.findElementByTagName("h1").getText();
        Assert.assertTrue(option.equals("You Might Also Like"));

        // -------------------------------------------------------------

        WebElement nameFirstAndLast1 = driver.findElementById("nameFirstAndLast");
        nameFirstAndLast1.click();
        Thread.sleep(500);

        List<WebElement> list1 = driver.findElementsByCssSelector(".buttonItems button");
        list1.get(1).click();
        Thread.sleep(500);

        //Verify that you are logged out by verifying the URL is:
        //http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php
        logoutURP = driver.getCurrentUrl();
        Assert.assertTrue(logoutURP.equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php"));

//
    }
    public static String generateUserName(){
//
        String s = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPGTSTIVWXYZ";
        String username = "";
        for (int i = 0; i < 8; i++) {
            username += s.charAt((int) (Math.random() * s.length()));}
        return username;
    }
    public static String generateEmail(){
        String s = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPGTSTIVWXYZ";
        String email = "";
        for (int i = 0; i < 8; i++) {
            email += s.charAt((int) (Math.random() * s.length()));}
        return email + "@gmail.com";
    }
    public static String generateName(){
//
        String s = "abcdefghijklmnopqrstuvwxyz";
        String username = "";
        for (int i = 0; i < 8; i++) {
            username += s.charAt((int) (Math.random() * s.length()));}

        return username.substring(0, 1).toUpperCase() + username.substring(1);
    }

}
