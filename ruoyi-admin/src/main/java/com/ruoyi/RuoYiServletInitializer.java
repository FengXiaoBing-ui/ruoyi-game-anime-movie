package com.ruoyi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * web容器中进行部署
 * 
 * @author ruoyi
 */
public class RuoYiServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(RuoYiApplication.class);
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("111111");
                    Thread.sleep(10000);
                    System.out.println("1-------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("22222");
                    Thread.sleep(10000);
                    System.out.println("2-------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("3333333");
                    Thread.sleep(10000);
                    System.out.println("3-------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();
        new Thread(runnable2).start();

    }
}
