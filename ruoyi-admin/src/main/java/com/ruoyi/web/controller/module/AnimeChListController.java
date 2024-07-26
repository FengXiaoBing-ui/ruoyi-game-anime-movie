package com.ruoyi.web.controller.module;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.module.domain.AnimeCh;
import com.ruoyi.module.service.IAnimeChService;
import org.apache.commons.compress.utils.Lists;
import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlVideo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/module/AnimeChListController")
public class AnimeChListController extends BaseController {

//    public WebDriver webdriver;

    @Autowired
    private IAnimeChService dataService;

    @Anonymous
    @GetMapping(value = "add")
    public AjaxResult getInfo() throws Exception {
        getGameInfo();
        return success();
    }

    @Anonymous
    @GetMapping(value = "getVideo")
    public AjaxResult getVideo(String url) throws Exception {
        return success(getVideoInfo(url));
    }

    private String getVideoInfo(String url) throws Exception {
        String pageUrl = url;
        String rUrl = "";
        Document movieEle = Jsoup.connect(pageUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Element scriptElement = movieEle.select("div.myui-player div.container div.row div.myui-player__item div.col-md-wide-75 div.myui-player__box div.embed-responsive script").first();
        String scriptText = scriptElement.html().trim();
        if (scriptText != null) {
            String cleanedJson = scriptText.replaceFirst("var player_aaaa=", "");
            JSONObject jsonObject = JSONObject.parseObject(cleanedJson);
            String baseUrl = "https://danmu.yhdmjx.com/m3u8.php?url=";
            String url2 = baseUrl + jsonObject.get("url");
            try {
                // 创建WebClient实例，并设置使用的浏览器类型（这里以Chrome为例）
                WebClient webClient = new WebClient(BrowserVersion.CHROME);

//                ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
//                proxyConfig.setProxyHost("127.0.0.1");
//                proxyConfig.setProxyPort(7890);
//                webClient.getOptions().setProxyConfig(proxyConfig);

                webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36");

                // 配置WebClient选项
                webClient.getOptions().setJavaScriptEnabled(true); // 启用JavaScript
                webClient.getOptions().setCssEnabled(false); // 禁用CSS，加快解析速度
                webClient.getOptions().setThrowExceptionOnScriptError(false); // 忽略JavaScript错误
                webClient.getOptions().setRedirectEnabled(true); // 启用重定向
                webClient.getOptions().setTimeout(10000); // 设置超时时间


                // 加载页面
                System.out.println("url2--------------------" + url2);
                HtmlPage page = webClient.getPage(url2);


                // 等待页面加载完成，如果页面有异步请求，可以增加等待时间
                Thread.sleep(1000); // 简单示例，实际使用中可能需要更复杂的等待逻辑
                HtmlVideo videoElement = page.getFirstByXPath("//video[@id='lelevideo']");

                HtmlVideo videoElement1 = getHtmlVideo(page, videoElement);

                rUrl = videoElement1.getAttribute("src");
                // 关闭WebClient，释放资源
                webClient.close();

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
            System.out.println(rUrl);

            return rUrl;
        } else {
            return "";
        }
    }


    private HtmlVideo getHtmlVideo(HtmlPage page, HtmlVideo videoElement) {
        if (videoElement == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HtmlVideo htmlVideo = page.getFirstByXPath("//video[@id='lelevideo']");
            getHtmlVideo(page, htmlVideo);
        }
        return videoElement;
    }


    public void main(String[] args) throws Exception {
        getGameInfo();
    }

    private void getGameInfo() throws Exception {
        List<String> list = Lists.newArrayList();

        String pageUrl = "https://www.857yhw.com/type/guochandongman.html";
        Document pageDoc = Jsoup.connect(pageUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Elements pages = pageDoc.select("ul.myui-page li a");
        String urlTotal = pages.last().attr("href");
        int dashIndex = urlTotal.indexOf("-");
        int number = 1;
        if (dashIndex != -1) {
            // 从"-"后一位开始截取字符串，直到字符串末尾
            String numberPart = urlTotal.substring(dashIndex + 1);

            // 去除后缀".html"
            String numberOnly = numberPart.replace(".html", "");

            number = Integer.parseInt(numberOnly) + 1;
        }
        System.out.println("国漫一共有---" + number + "页,每页有40条");
        for (int i = 1; i < 3; i++) {
            String url = "https://www.857yhw.com/type/guochandongman-" + i + ".html";
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
            Elements movies = doc.select("div.myui-panel div.myui-panel-box div.myui-panel_bd ul.myui-vodlist li");
            for (int j = 0; j < movies.size(); j++) {
                Element movie = movies.get(j);
                String tt = movie.select("div.myui-vodlist__box div.myui-vodlist__detail h4 a").text();
                list.add(movie.select("div.myui-vodlist__box a").attr("href"));
            }
            System.out.println("每页有" + list.size() + "条");
            for (int item = 0; item < list.size(); item++) {
                System.out.println("第" + i + "页的第" + (item + 1) + "个动漫");
                List<String> playArr = Lists.newArrayList();
                Document docDetails = Jsoup.connect("https://www.857yhw.com/" + list.get(item)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                Elements shortItem = docDetails.select("div.col-md-wide-7");
                Elements playlist = docDetails.select("div#playlist1 ul.myui-content__list li");
                for (int playI = 0; playI < playlist.size(); playI++) {
                    Element elePl = playlist.get(playI);
                    playArr.add(elePl.select("a").attr("href"));
                }
                StringBuilder line1 = new StringBuilder();

                for (int playHref = 0; playHref < playArr.size(); playHref++) {
                    line1.append("https://www.857yhw.com/" + playArr.get(playHref) + ";");
                }
                String arrDetail =
                        "{\"ele\":\"div.myui-panel div.myui-panel-box div.col-xs-1 div.myui-content__thumb a img\",\"type\":\"data-original\",\"key\":\"coverImg\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.myui-panel div.myui-panel-box div.col-xs-1 div.myui-content__detail h1.title\",\"type\":\"text\",\"key\":\"title\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.myui-panel div.myui-panel-box div.col-xs-1 div.myui-content__detail p.data\",\"type\":\"text\",\"key\":\"alias\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.myui-panel div.myui-panel-box div.col-xs-1 div.myui-content__detail a\",\"type\":\"text\",\"key\":\"animeType\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +


                                "{\"ele\":\"div.myui-panel div.myui-panel-box div.myui-panel_bd div.col-pd span.data\",\"type\":\"text\",\"key\":\"animeDesc\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-,";
                String[] splitDetail = arrDetail.split("-,");

                List<String> arr = extracted(shortItem, splitDetail, line1);

                Map<String, String> map = new LinkedHashMap<>();

                for (String c : arr) {
                    String[] arr2 = c.split("-------");
                    map.put(arr2[0], arr2[1]);
                }
                AnimeCh data = new AnimeCh();
                data.setCoverImg(map.get("coverImg"));
                data.setTitle(map.get("title"));
                data.setAlias(map.get("alias"));
                data.setAnimeType(map.get("animeType"));
                data.setAnimeCategory(map.get("animeCategory"));
                data.setAnimeArea(map.get("animeArea"));
                data.setAnimeYear(map.get("animeYear"));
                data.setUpdateState(map.get("updateState"));
                data.setAnimeDesc(map.get("animeDesc"));
                data.setAnimeVideo(map.get("animeVideo"));
                data.setStandbyVideo(map.get("standbyVideo"));


                List<AnimeCh> dataList = dataService.selectDataByTitle(data.getTitle());
                if (dataList.isEmpty()) {
                    dataService.insertAnimeCh(data);
                } else {
                    data.setId(dataList.get(0).getId());
                    dataService.updateAnimeCh(data);
                }
            }
            list.clear();
        }
    }

    private static List<String> extracted(Elements movies, String[] eleArr, StringBuilder line1) throws Exception {
        String keyValue = "";
        String alias = "", animeType = "", updateState = "";
        String animeCategory = "", animeArea = "", animeYear = "";
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < movies.size(); i++) {
            Element movie = movies.get(i);
            for (int j = 0; j < eleArr.length; j++) {
                String s = eleArr[j];
                JSONObject jsonObject = JSON.parseObject(s);
                Object type = jsonObject.get("type");
                Object ele = jsonObject.get("ele");
                Object eleIndex = jsonObject.get("eleIndex");
                Object ownText = jsonObject.get("ownText");
                Object keyName = jsonObject.get("key");
                if (type.toString().equals("text")) {
                    if (ownText.toString().equals("1")) {
                        keyValue = movie.select(ele.toString()).get(Integer.parseInt(eleIndex.toString())).ownText();
                    } else {
                        if (eleIndex.toString().equals("-1")) {
                            keyValue = movie.select(ele.toString()).text();
                        } else {
                            keyValue = movie.select(ele.toString()).get(Integer.parseInt(eleIndex.toString())).text();
                        }
                    }
                    if (keyName.toString().equals("animeType")) {
                        String[] aType = keyValue.split(" ");
                        animeCategory = aType[0];
                        animeArea = aType[1];
                        animeYear = aType[2];
                    }
                    if (keyName.toString().equals("alias")) {
                        String regex = "别名：|类型：|更新：|简介：";

                        String[] parts = keyValue.split(regex);
                        String[] result = new String[parts.length];
                        for (int p = 0; p < parts.length; p++) {
                            if (p == 0) {
                                result[p] = parts[p].replaceFirst("别名：", "");
                            } else if (p == parts.length - 1) {
                                result[p] = parts[p].startsWith("：“") ? parts[p].substring(2) : parts[p];
                            } else {
                                // 其他部分直接使用
                                result[p] = parts[p];
                            }
                        }
                        alias = result[1];
                        animeType = result[2];
                        updateState = result[3];
                    }
                } else {
                    keyValue = movie.select(ele.toString()).attr(type.toString());
                    if (type.toString().equals("data-original")) {
                        keyValue = keyValue.replace("https://image.baidu.com/search/down?url=", "");
                    }
                }
                if (!keyName.toString().equals("alias")) {
                    list.add(keyName + "-------" + keyValue);
                }
            }
        }
        list.add("alias-------" + alias);
        list.add("animeType-------" + animeType);
        list.add("updateState-------" + updateState);
        list.add("animeCategory-------" + animeCategory);
        list.add("animeArea-------" + animeArea);
        list.add("animeYear-------" + animeYear);
        list.add("animeYear-------" + animeYear);
        list.add("animeVideo-------" + line1);
        return list;
    }
}
