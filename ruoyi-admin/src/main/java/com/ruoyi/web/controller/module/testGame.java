package com.ruoyi.web.controller.module;


import org.apache.commons.compress.utils.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.List;

public class testGame {
    public static void main(String[] args) throws Exception {
        getInfoDetails("鬼谷八荒");
    }
    public static List<String> getInfoDetails(String name) throws Exception{
        String proxyHost = "127.0.0.1";
        int proxyPort = 7890;
        String url = name;
        if (name.length()>70 || name.contains("%")){
            url = name.replace(" ","");
        }
        System.out.println("https://store.steampowered.com/search/?term="+URLEncoder.encode(url)+"&ndl=1");
        Document gameDoc = Jsoup.connect("https://store.steampowered.com/search/?term="+URLEncoder.encode(url)+"&ndl=1").proxy(proxyHost,proxyPort).timeout(100000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        List<String> list = Lists.newArrayList();
        Elements docLen = gameDoc.select("div#search_resultsRows a");
        if(docLen.size()<=0){
            return list;
        };
        String gameDetailUrl = gameDoc.select("div#search_resultsRows a").get(0).attr("href");
        Elements gameTitle = gameDoc.select("div#search_resultsRows span.title");
        boolean flag = false;
        for (int i = 0; i < gameTitle.size() && !flag; i++) {
            if(calculateSimilarity(name,gameTitle.get(i).text()) >= 90){
                flag = true;
            }
        }
        if(!flag){
            return list;
        }
        Thread.sleep(500);
        Document gameDetailDoc = Jsoup.connect(gameDetailUrl).proxy(proxyHost,proxyPort).timeout(100000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").header("accept-language","zh-CN,zh;q=0.9,en;q=0.8,eo;q=0.7").cookie("cookie","wants_mature_content=1;Steam_Language=schinese;browserid=3494315867708484772; sessionid=0889b05865171bd651544e96; timezoneOffset=28800,0; birthtime=599587201; lastagecheckage=1-January-1989").data().get();
        String title = gameDetailDoc.select("div#appHubAppName").text();
        String desc = gameDetailDoc.select("div.game_description_snippet").text();
        Elements playerVideo = gameDetailDoc.select("div#highlight_player_area div.highlight_movie");
        Elements playerImage = gameDetailDoc.select("div#highlight_player_area div.highlight_screenshot");

        Elements devAndPub = gameDetailDoc.select("div.glance_ctn div#appHeaderGridContainer div.grid_content");
        if(devAndPub.size()<=0){
            return list;
        }
        String developers = gameDetailDoc.select("div.glance_ctn div#appHeaderGridContainer div.grid_content").get(0).select("a").text();

        String publisher = devAndPub.size()<=1?"":gameDetailDoc.select("div.glance_ctn div#appHeaderGridContainer div.grid_content").get(1).select("a").text();

        StringBuilder webmSource = new StringBuilder();
        StringBuilder webmHdSource = new StringBuilder();
        StringBuilder mp4Source = new StringBuilder();
        StringBuilder mp4HdSource = new StringBuilder();
        StringBuilder poster = new StringBuilder();
        StringBuilder image = new StringBuilder();
        for (int i = 0; i < playerVideo.size(); i++) {
            webmSource.append(playerVideo.get(i).attr("data-webm-source")+";");
            webmHdSource.append(playerVideo.get(i).attr("data-webm-hd-source")+";");
            mp4Source.append(playerVideo.get(i).attr("data-mp4-source")+";");
            mp4HdSource.append(playerVideo.get(i).attr("data-mp4-hd-source")+";");
            poster.append(playerVideo.get(i).attr("data-poster")+";");
        }

        for (int i = 0; i < playerImage.size(); i++) {
            image.append(playerImage.get(i).select("div.screenshot_holder a").attr("href")+";");
        }


        list.add("zhTitle-------"+title);
        list.add("desc-------"+desc);
        list.add("releaseDate-------"+gameDetailDoc.select("div.release_date div.date").text());
        list.add("gameType-------"+gameDetailDoc.select("div#genresAndManufacturer span a").text());
        list.add("gameIntroduce-------"+gameDetailDoc.select("div#game_area_description").html());
        list.add("minSpec-------"+gameDetailDoc.select("div.game_area_sys_req_leftCol").html());
        list.add("minSpec2-------"+gameDetailDoc.select("div.game_area_sys_req_full").html());
        list.add("recommendSpec-------"+gameDetailDoc.select("div.game_area_sys_req_rightCol").html());

        list.add("developers-------"+developers);
        list.add("publisher-------"+publisher);
        list.add("tagList-------"+gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div.glance_tags a").text());
        list.add("steamLatelyMixed-------"+gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div#userReviews_responsive div.summary span.mixed").text());
        Elements len = gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div#userReviews_responsive div.summary span.responsive_reviewdesc_short");

        list.add("steamLatelyReviewDesc-------"+(len.size()>0?gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div#userReviews_responsive div.summary span.responsive_reviewdesc_short").get(0).text():""));
        list.add("steamAllPositive-------"+gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div#userReviews_responsive div.summary span.positive").text());
        list.add("steamAllReviewDesc-------"+(len.size()>1?gameDetailDoc.select("div.glance_ctn div#glanceCtnResponsiveRight div#userReviews_responsive div.summary span.responsive_reviewdesc_short").get(1).text():""));

        list.add("webmSource-------"+webmSource);
        list.add("webmHdSource-------"+webmHdSource);
        list.add("mp4Source-------"+mp4Source);
        list.add("mp4HdSource-------"+mp4HdSource);
        list.add("poster-------"+poster);
        list.add("image-------"+image);
        return list;
    }

    public static double calculateSimilarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());
        double similarity = (maxLength - distance) / (double) maxLength * 100;
        return similarity;
    }

    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
