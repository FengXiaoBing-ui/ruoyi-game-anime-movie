package com.ruoyi.web.controller.module;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
    public static void main(String[] args) throws Exception {
        getInfoDetails();
    }

    private static void getInfoDetails() throws Exception{
        Document movieEle = Jsoup.connect("https://search.douban.com/movie/subject_search?search_text=凡人修仙传").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Element scriptElement = movieEle.select("script").get(6);
        // 等待页面加载完成，如果页面有异步请求，可以增加等待时间
        String objStr = scriptElement.toString().split("window.__DATA__ = ")[1].split("window.__USER__")[0].split(";")[0];
        JSONObject jsonObject = JSONObject.parseObject(objStr);
        Object items = jsonObject.get("items");
        JSONArray objects = JSON.parseArray(items.toString());
        Object urlOjb = objects.get(0);
        JSONObject jsonObject1 = JSONObject.parseObject(urlOjb.toString());
        Object url = jsonObject1.get("url");
        Document movieDetailEle = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Elements info = movieDetailEle.select("div#info");
        String desc = movieDetailEle.select("div.article div.related-info div.indent span").get(0).text();
        String interest = movieDetailEle.select("div#interest_sectl div.rating_wrap strong.rating_num").text();
        String ratingPeople = movieDetailEle.select("div#interest_sectl div.rating_wrap a.rating_people").text().split("人评价")[0];

        System.out.println("导演---"+info.select("span").get(0).select("span.attrs a").text());
        System.out.println("编剧---"+info.select("span").get(3).select("span.attrs a").text());
        System.out.println("主演---"+info.select("span").get(6).select("span.attrs a").text());
        System.out.println("类型---"+info.select("[property~=v:genre]").text());
        System.out.println("官方网站---"+info.select("[rel~=nofollow]").text());
        System.out.println("首播---"+info.select("[property~=v:initialReleaseDate]").text());
        System.out.println("单集片长---"+strHandle(info.text(),"单集片长","又名"));
        System.out.println("又名:---"+strHandle(info.text(),"又名","IMDb"));
        System.out.println("评分:---"+interest);
        System.out.println("评分人数:---"+ratingPeople);
        System.out.println("简介:---"+desc);
    }
    private static String strHandle(String str, String startStr, String endStr) throws Exception{
        int start = str.indexOf(startStr);
        int end = str.indexOf(endStr);
        if (start != -1 && end != -1) {
            String content = str.substring(start + startStr.length(), end);
            return content;
        }
        return "";
    }
}
