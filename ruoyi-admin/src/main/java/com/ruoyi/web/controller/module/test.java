package com.ruoyi.web.controller.module;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {

    public WebDriver webdriver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\RuoYi-Vue-master\\ruoyi-admin\\src\\drivers\\chromedriver.exe");
        ChromeDriver webdriver = new ChromeDriver();
    }
}
