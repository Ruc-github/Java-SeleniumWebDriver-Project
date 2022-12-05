import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.assertEquals;
public class browserTest {

    WebDriver driver = null;
    WebElement addEleButton = null;

    @BeforeTest
    public void setupTest() {

        String projectPath = System.getProperty("user.dir");
        System.out.println("project path:" + projectPath);
        System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromeDriver/chromedriver");
        driver = new ChromeDriver();

    }

    @Test
    public void urlTest() {


        //open the URL using Chrome Browser
        driver.get("https:/the-internet.herokuapp.com");
        //get the header class element
        WebElement headingClass = driver.findElement(By.className("heading"));
        //get the text of the header class
        String headerText = headingClass.getText();
        //verify the url navigation to the expected page.
        assertEquals("Welcome to the-internet", headerText);

    }

    @Test(dataProvider = "testdata")
    public void addElementTest(int elementNumber) {

        //open the URL using Chrome Browser
        driver.get("https:/the-internet.herokuapp.com");
        //get the header class element
        driver.findElement(By.linkText("Add/Remove Elements")).click();

        WebElement headerElement = driver.findElement(By.xpath("//h3[contains(text(),'Add/Remove Elements')]"));
        String headerText = headerElement.getText();
        System.out.println("headerText: " + headerText);

        //verify the url navigation to the expected page.
        assertEquals("Add/Remove Elements", headerText);

        //add Elements
        addEleButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
        System.out.println("addEleButton" + addEleButton);
        addElements(elementNumber);

        List<WebElement> cbutton = driver.findElements(By.xpath("//button[contains(text(),'Delete')]"));
        int totalNewElementFoundonPage = cbutton.size() - 1;
        System.out.println("total Newly added Elements on the page: " + totalNewElementFoundonPage);
        assertEquals(elementNumber, totalNewElementFoundonPage);

    }

    @DataProvider (name = "testdata")
    public Object[][] tData() {
        return new Object[][]{{1},{2},{3},{4},{5}};
    }

    @AfterTest
    public void teardownTest() {

        //Close the browser
        driver.close();
        driver.quit();

        System.out.println("tests are completed successfully");


    }

    //Function to add the n number of elements
    public void addElements(int elementNumber) {

        for (int i = 0; i <= elementNumber; i++) {
            addEleButton.click();
        }

    }

}





