package controller;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

class BrowserDriverFactory {
  private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
  private String browser;
  private Logger log;

  BrowserDriverFactory(String browser, Logger log) {
    this.browser = browser.toLowerCase();
    this.log = log;
  }
  WebDriver createDriver(){
    log.info("Create driver "+ browser);
    if ("chrome".equals(browser)) {
      driver.set(new ChromeDriver());

    } else if ("firefox".equals(browser)) {
      driver.set(new FirefoxDriver());

    }
    else if ("chromeHeadless".equals(browser)) {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.addArguments("--headless");
      driver.set(new ChromeDriver(chromeOptions));

    }else {
      driver.set(new ChromeDriver());
    }
    return driver.get();
  }
  WebDriver createChromeWithProfile(String profile) {
    log.info("Starting chrome driver with profile: " + profile);
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/" + profile);

    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    driver.set(new ChromeDriver(chromeOptions));
    return driver.get();
  }
}
