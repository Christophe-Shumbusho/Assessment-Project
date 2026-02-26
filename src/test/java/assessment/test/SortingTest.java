package assessment.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTest {
    protected WebDriver driver;
    private WebDriverWait wait;
    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    @Test
    public void validateSortingFunctionality(){
        WebElement sortDropdown = driver.findElement(By.xpath("//select[@aria-label='sort']"));
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name (A - Z)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h5[data-test='product-name']")));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h5[data-test='product-name']")));
        List<WebElement> nameElements = driver.findElements(By.cssSelector("[data-test='product-name']"));
        List<String> productNamesBefore = nameElements.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Product names" + productNamesBefore);


         List<String> sortedNamesAfter = new ArrayList<>(productNamesBefore);
         Collections.sort(sortedNamesAfter);
        System.out.println("Sorted name" + sortedNamesAfter);
        Assert.assertNotEquals(productNamesBefore, sortedNamesAfter, "Sorting does not change!");

    }
    @AfterClass
    public void teardown() {
        //Quit browser
        driver.quit();
    }
}
