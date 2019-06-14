package controller;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    } else {
      driver.set(new ChromeDriver());
    }
    return driver.get();
  }
}
