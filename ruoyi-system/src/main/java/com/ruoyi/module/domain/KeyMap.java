package com.ruoyi.module.domain;

/**
 * 岗位表 sys_post
 *
 * @author ruoyi
 */
public class KeyMap {

    private String title;
    private String introduce;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "KeyMap{" +
                "title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
