package model;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class AbstractPage {
  public static final String BASE_URL = "http://localhost:4200/";
   WebDriver driver;
  Logger log;
  private static final String ARRAY_ENG_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final String NUMBERS = "1234567890";
  private static final String ARRAY_RUS_ALPHABET = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя";

  AbstractPage(WebDriver driver, Logger log) {
    this.driver = driver;
    this.log = log;
    PageFactory.initElements(driver,this);
  }

  void openUrl(String url) {
    driver.get(url);
  }

  private String generateString(Random random, String characters, int length) {
    char[] text = new char[length];
    for (int i = 0; i < length; i++) {
      text[i] = characters.charAt(random.nextInt(characters.length()));
    }
    return new String(text);
  }

  public  String createTextStringRusEng(int lengthText) {
    return generateString(new Random(), ARRAY_ENG_ALPHABET+ARRAY_RUS_ALPHABET, lengthText);
  }
  private String createTextStringEng(int lengthText) {
    return generateString(new Random(), ARRAY_ENG_ALPHABET, lengthText);
  }
  public String createNumString(int lengthText) {
    return generateString(new Random(), NUMBERS, lengthText);
  }

  public  String createMixedString(int lengthText) {
    return generateString(new Random(), NUMBERS + ARRAY_ENG_ALPHABET + ARRAY_RUS_ALPHABET, lengthText);
  }

  public String createEmailRandom(int lengthEmail) {
    return createTextStringEng(lengthEmail)+"@gmail.com";
  }

  public WebElement find(WebElement webElement) {
    waitForVisibilityOf(webElement, 5);
    return webElement;
  }
  public WebElement findXpath(String locator) {
    waitForVisibilityOf(driver.findElement(By.xpath(locator)), 5);
    return driver.findElement(By.xpath(locator));
  }
  public void click(WebElement webElement) {
    waitForVisibilityOf(webElement, 5);
    find(webElement).click();
  }
  /**
   * Click on element with given locator when its visible
   */
  protected String getAttribute(WebElement webElement, String attribute) {
    waitForVisibilityOf(webElement, 5);
    return find(webElement).getAttribute(attribute);
  }

  /**
   * Type given text into element with given locator
   */
  public void type(String text, WebElement webElement) {
    waitForVisibilityOf(webElement, 5);
    find(webElement).clear();
    webElement.sendKeys(text);
  }
  public boolean visibleElement(WebElement webElement) {
    waitForVisibilityOf(webElement, 5);
    return  webElement.isDisplayed();
  }

  /**
   * Get URL of current page from browser
   */
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  /**
   * Get title of current page
   */
  public String getCurrentPageTitle() {
    return driver.getTitle();
  }

  /**
   * Get source of current page
   */
  public String getCurrentPageSource() {
    return driver.getPageSource();
  }

  /**
   * Wait for specific ExpectedCondition for the given amount of time in seconds
   */
  private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
//    timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
//    WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
//    wait.until(condition);
  }

  /**
   * Wait for given number of seconds for element with given locator to be visible
   * on the page
   */
  protected void waitForVisibilityOf(WebElement webElement, Integer... timeOutInSeconds) {
    int attempts = 0;
    while (attempts < 2) {
      try {
        waitFor(ExpectedConditions.visibilityOf(webElement),
                (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
        break;
      } catch (StaleElementReferenceException e) {
      }
      attempts++;
    }
  }

  /**
   * Wait for alert present and then switch to it
   */
//  protected Alert switchToAlert() {
//    WebDriverWait wait = new WebDriverWait(driver, 5);
//    wait.until(ExpectedConditions.alertIsPresent());
//    return driver.switchTo().alert();
//  }

  public void switchToWindowWithTitle(String expectedTitle) {
    // Switching to new window
    String firstWindow = driver.getWindowHandle();

    Set<String> allWindows = driver.getWindowHandles();
    Iterator<String> windowsIterator = allWindows.iterator();

    while (windowsIterator.hasNext()) {
      String windowHandle = windowsIterator.next().toString();
      if (!windowHandle.equals(firstWindow)) {
        driver.switchTo().window(windowHandle);
        if (getCurrentPageTitle().equals(expectedTitle)) {
          break;
        }
      }
    }
  }

  /**
   * Switch to iFrame using it's locator
   */
  protected void switchToFrame(WebElement frameLocator) {
    driver.switchTo().frame(find(frameLocator));
  }

  /**
   * Press Key on locator
   */
  protected void pressKey(WebElement locator, Keys key) {
    find(locator).sendKeys(key);
  }

  /**
   * Press Key using Actions class
   */
  public void pressKeyWithActions(Keys key) {
    log.info("Pressing " + key.name() + " using Actions class");
    Actions action = new Actions(driver);
    action.sendKeys(key).build().perform();
  }

  /**
   * Perform scroll to the bottom
   */
  public void scrollToBottom() {
    log.info("Scrolling to the bottom of the page");
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
  }

  /**
   * Drag 'from' element to 'to' element
   */
  protected void performDragAndDrop(WebElement from, WebElement to) {
    // Actions action = new Actions(driver);
    // action.dragAndDrop(find(from), find(to)).build().perform();

    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript(
            "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                    + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
                    + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                    + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
                    + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                    + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                    + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                    + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
                    + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                    + "var dragStartEvent =createEvent('dragstart');\n"
                    + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
                    + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                    + "var dragEndEvent = createEvent('dragend');\n"
                    + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                    + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                    + "simulateHTML5DragAndDrop(source,destination);",
            find(from), find(to));
  }

  /**
   * Perform mouse hover over element
   */
  protected void hoverOverElement(WebElement element) {
    Actions action = new Actions(driver);
    action.moveToElement(element).build().perform();
  }

}

