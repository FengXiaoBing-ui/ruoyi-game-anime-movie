package com.ruoyi.web.controller.module;


import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) throws Exception {
        String url1 = "https://www.henglihuagong.com/zmti/1-1.html";
        Document homeDoc = Jsoup.connect(url1).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Elements homeDocEle = homeDoc.select("ul.stui-vodlist li");
        List<String> list = Lists.newArrayList();
        for (int j = 0; j < homeDocEle.size(); j++) {
            Element homeDocEleLi = homeDocEle.get(j);
            list.add(homeDocEleLi.select("a.stui-vodlist__thumb").attr("href"));
        }
        for (int item = list.size() - 1; item >= 0; item --) {
            List<String> dataList = Lists.newArrayList();
            Document detailDoc = Jsoup.connect("https://www.henglihuagong.com/"+list.get(item)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
            Elements detailDocEle = detailDoc.select("div.stui-pannel");
            String title = detailDoc.select("div.stui-content__detail h1").text();
            String info = detailDoc.select("div.stui-content__detail p.data").get(0).text();
            String type = "",area = "",year="",score="";
            type = info.substring(info.indexOf("类型：") + "类型：".length(), info.indexOf("地区：")).trim();
            area = info.substring(info.indexOf("地区：") + "地区：".length(), info.indexOf("年份：")).trim();
            year = info.substring(info.indexOf("年份：") + "年份：".length(), info.indexOf("评分：")).trim();
            String[] typeArr = info.split(" ");
            score = typeArr[typeArr.length-1].split("：")[1];

            String language = detailDoc.select("div.stui-content__detail p.data").get(1).text().split("：")[1];
            String performer = detailDoc.select("div.stui-content__detail p.data").get(2).text().split("：")[1];
            String director = detailDoc.select("div.stui-content__detail p.data").get(3).text().split("：")[1];
            String edition = detailDoc.select("div.stui-content__detail p.data").get(4).text().split("：")[1];
            String updateMovie = detailDoc.select("div.stui-content__detail p.data").get(7).text().split("：")[1];
            String desc = detailDoc.select("div.stui-content__desc p").get(0).text();
            String desc2 = detailDoc.select("div.stui-content__desc").get(0).ownText();


            String hrefName1 = "";
            String hrefText1 = "";
            StringBuilder linkHref1 = new StringBuilder();
            StringBuilder movieUrl1 = new StringBuilder();

            String hrefName2 = "";
            String hrefText2 = "";
            StringBuilder linkHref2 = new StringBuilder();
            StringBuilder movieUrl2 = new StringBuilder();

            String hrefName3 = "";
            String hrefText3 = "";
            StringBuilder linkHref3 = new StringBuilder();
            StringBuilder movieUrl3 = new StringBuilder();

            for (int panel = 1; panel < detailDocEle.size()-2; panel++) {
                Element aHref = detailDocEle.get(panel);
                Elements aList = aHref.select("ul.stui-content__playlist li");
                if(panel == 1){
                    for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                        linkHref1.append(aList.get(aListItem).select("a").attr("href")+";");
                    }
                    hrefText1 = aHref.select("ul.stui-content__playlist a").text();
                    hrefName1 = aHref.select("div.stui-pannel__head h3").text();
                }
                if(panel == 2){
                    for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                        linkHref2.append(aList.get(aListItem).select("a").attr("href")+";");
                    }
                    hrefText2 = aHref.select("ul.stui-content__playlist a").text();
                    hrefName2 = aHref.select("div.stui-pannel__head h3").text();
                }
                if(panel == 3){
                    for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                        linkHref3.append(aList.get(aListItem).select("a").attr("href")+";");
                    }
                    hrefText3 = aHref.select("ul.stui-content__playlist a").text();
                    hrefName3 = aHref.select("div.stui-pannel__head h3").text();
                }
            }
            if(!StringUtils.isEmpty(linkHref1)){
                String[] arr1 = linkHref1.toString().split(";");
                for (int arrI = 0; arrI < arr1.length; arrI++) {
                    Document videoDoc = Jsoup.connect("https://www.henglihuagong.com/"+arr1[arrI]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                    Element videoDocEle = videoDoc.select("div.stui-player__video script").get(0);
                    // 正则表达式匹配url字段
                    Pattern pattern = Pattern.compile("url\":\\s*\"([^\"]+)");
                    Matcher matcher = pattern.matcher(videoDocEle.html());
                    if (matcher.find()) {
                        // 提取url并打印
                        movieUrl1.append(matcher.group(1).replace("\\","")+";");
                    } else {
                        System.out.println("URL not found.");
                    }
                }
            }
            if(!StringUtils.isEmpty(linkHref2)){
                String[] arr1 = linkHref2.toString().split(";");
                for (int arrI = 0; arrI < arr1.length; arrI++) {
                    Document videoDoc = Jsoup.connect("https://www.henglihuagong.com/"+arr1[arrI]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                    Element videoDocEle = videoDoc.select("div.stui-player__video script").get(0);
                    // 正则表达式匹配url字段
                    Pattern pattern = Pattern.compile("url\":\\s*\"([^\"]+)");
                    Matcher matcher = pattern.matcher(videoDocEle.html());
                    if (matcher.find()) {
                        // 提取url并打印
                        movieUrl2.append(matcher.group(1).replace("\\","")+";");
                    } else {
                        System.out.println("URL not found.");
                    }
                }
            }
            if(!StringUtils.isEmpty(linkHref3)){
                String[] arr1 = linkHref3.toString().split(";");
                for (int arrI = 0; arrI < arr1.length; arrI++) {
                    Document videoDoc = Jsoup.connect("https://www.henglihuagong.com/"+arr1[arrI]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                    Element videoDocEle = videoDoc.select("div.stui-player__video script").get(0);
                    // 正则表达式匹配url字段
                    Pattern pattern = Pattern.compile("url\":\\s*\"([^\"]+)");
                    Matcher matcher = pattern.matcher(videoDocEle.html());
                    if (matcher.find()) {
                        // 提取url并打印
                        movieUrl3.append(matcher.group(1).replace("\\","")+";");
                    } else {
                        System.out.println("URL not found.");
                    }
                }
            }

            dataList.add("title-------"+title);
            dataList.add("type-------"+type);
            dataList.add("area-------"+area);
            dataList.add("year-------"+year);
            dataList.add("score-------"+score);
            dataList.add("language-------"+language);
            dataList.add("performer-------"+performer);
            dataList.add("director-------"+director);
            dataList.add("edition-------"+edition);
            dataList.add("updateMovie-------"+updateMovie);
            dataList.add("desc-------"+desc);
            dataList.add("desc2-------"+desc2);

            dataList.add("hrefName1-------"+hrefName1);
            dataList.add("hrefText1-------"+hrefText1);
            dataList.add("linkHref1-------"+linkHref1);
            dataList.add("movieUrl1-------"+movieUrl1);

            dataList.add("hrefName2-------"+hrefName2);
            dataList.add("hrefText2-------"+hrefText2);
            dataList.add("linkHref2-------"+linkHref2);
            dataList.add("movieUrl2-------"+movieUrl2);

            dataList.add("hrefName3-------"+hrefName3);
            dataList.add("hrefText3-------"+hrefText3);
            dataList.add("linkHref3-------"+linkHref3);
            dataList.add("movieUrl3-------"+movieUrl3);


            System.out.println(dataList);
        }

    }
}
