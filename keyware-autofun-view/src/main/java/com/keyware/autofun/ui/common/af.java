package com.keyware.autofun.ui.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class af {


    private static WebDriver driver;
    private static Map<String, Object> vars;

    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//加载谷歌驱动
        driver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");//加载火狐驱动
//        driver = new FirefoxDriver();
        vars = new HashMap<String, Object>();
    }

    public static void main(String[] args) {
        driver.get("https://www.baidu.com/");
        driver.manage().window().setSize(new Dimension(983, 697));
        driver.findElement(By.id("kw")).click();
        driver.findElement(By.id("kw")).sendKeys("腾讯");
        driver.findElement(By.id("kw")).sendKeys(Keys.ENTER);
    }

    /**
     * 关闭驱动
     */
    public void close() {
        driver.quit();
    }

    /**
     * 打开浏览器,并调整分辨率
     */
    public static void openUrl(String url) {
        driver.get(url);
        driver.manage().window().setSize(new Dimension(983, 697));
    }

    /**
     * 输入搜索关键字
     */
    public static void searchValue(String value) {
        driver.findElement(By.id("kw")).click();
        driver.findElement(By.id("kw")).sendKeys(value);
        driver.findElement(By.id("kw")).sendKeys(Keys.ENTER);
    }


}


