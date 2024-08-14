package com.ruoyi.web.controller.module;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.module.domain.Data;
import com.ruoyi.module.domain.DataChild;
import com.ruoyi.module.service.IDataChildService;
import com.ruoyi.module.service.IDataService;
import org.apache.commons.compress.utils.Lists;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/module/DataListController")
public class DataListController extends BaseController {

    @Autowired
    private IDataService dataService;
    @Autowired
    private IDataChildService dataChildService;

    @Anonymous
    @GetMapping(value = "add")
    public AjaxResult getInfo() throws Exception {
        getGameInfo();
        return success();
    }


    public void main(String[] args) throws Exception {
        getGameInfo();
    }

    private void getGameInfo() throws Exception {
        String proxyHost = "127.0.0.1";
        int proxyPort = 7890;
        List<String> list = Lists.newArrayList();

        String pageUrl = "https://byruthub.org";
        Document pageDoc = Jsoup.connect(pageUrl).proxy(proxyHost, proxyPort).timeout(100000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Elements pages = pageDoc.select("div.navigation div.pages a");
        Integer total = Integer.parseInt(pages.last().text());
        System.out.println(total);
        for (int i = 318; i < total; i++) {
            System.out.println("第---"+i+"---页");
            String url = "https://byruthub.org/page/" + i;
            Document doc = Jsoup.connect(url).proxy(proxyHost, proxyPort).timeout(100000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
            Elements movies = doc.select("div#dle-content div.short_item");
            for (int j = 0; j < movies.size(); j++) {
                Element movie = movies.get(j);
                list.add(movie.select("div.short_img a").attr("href"));
            }
            for (int item = 0; item < list.size(); item++) {
                Document docDetails = Jsoup.connect(list.get(item)).proxy(proxyHost, proxyPort).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                Elements shortItem = docDetails.select("main.main article.main_content");
                List<String> arr = Lists.newArrayList();
                String title = shortItem.select("div.fmainwrap div.game_details div.hname h1").text();
                String dlc = shortItem.select("div.min-details span.pr4").text();
                System.out.println("downLoadCount-------" + dlc.equals(""));
                if(dlc.equals("")){
                    continue;
                }
                arr.add("downLoadCount-------" + dlc.replace(" ", ""));
                arr.add("coverImg-------https://byruthub.org" + shortItem.select("div.fmainwrap div.poster-imgbox img.imgbox").attr("src"));
                arr.add("title-------" + title);
                arr.add("score-------" + shortItem.select("div.fmainwrap div.game_details div.low-info div.ratingbox div.rate-label").text());
                arr.add("gameUrl-------" + shortItem.select("div.block_down_game div.block_down a.itemdown_games").attr("href"));
                arr.add("gameSize-------" + shortItem.select("div.persize_bottom").text().replace("ГБ","GB").replace("МБ","MB"));
                System.out.println("title-------"+title);
                List<String> listSteam = testGame.getInfoDetails(title);
                if (listSteam.size()<=0){
                    continue;
                }
                arr.addAll(listSteam);
                Map<String, String> map = new LinkedHashMap<>();

                for (String c : arr) {
                    String[] arr2 = c.split("-------");
                    map.put(arr2[0], arr2.length>1?arr2[1]:"");
                }

                Data data = new Data();
                data.setDownLoadCount(Long.valueOf(map.get("downLoadCount")));
                data.setCoverImg(map.get("coverImg"));
                data.setZhTitle(map.get("zhTitle"));
                data.setTitle(map.get("title"));
                data.setReleaseDate(getDate(map.get("releaseDate")));
                data.setDeveloper(map.get("developers"));
                data.setScore(map.get("score"));
                data.setGameUrl(map.get("gameUrl"));
                data.setGameSize(map.get("gameSize"));
                data.setGameSizeKb(convertToKB(map.get("gameSize")));
                data.setGameType(map.get("gameType"));
                data.setPublisher(map.get("publisher"));
                data.setTagList(map.get("tagList"));
//                data.setCreateTime(new Date());
//                data.setUpdateTime(new Date());

                DataChild dataChild = new DataChild();
                dataChild.setMinSpec(map.get("minSpec"));
                dataChild.setRecommendSpec(map.get("recommendSpec"));
                dataChild.setMinSpec2(map.get("minSpec2"));
                dataChild.setGameDesc(map.get("desc"));
                dataChild.setGameIntroduce(map.get("gameIntroduce"));
                dataChild.setSteamLatelyMixed(map.get("steamLatelyMixed"));
                dataChild.setSteamLatelyReviewDesc(map.get("steamLatelyReviewDesc"));
                dataChild.setSteamAllPositive(map.get("steamAllPositive"));
                dataChild.setSteamAllReviewDesc(map.get("steamAllReviewDesc"));
                dataChild.setWebmSource(map.get("webmSource"));
                dataChild.setWebmHdSource(map.get("webmHdSource"));
                dataChild.setMp4Source(map.get("mp4Source"));
                dataChild.setMp4HdSource(map.get("mp4HdSource"));
                dataChild.setPoster(map.get("poster"));
                dataChild.setImage(map.get("image"));



                List<Data> dataList = dataService.selectDataByTitle(data.getTitle());
                if (dataList.isEmpty()) {
                    dataService.insertData(data);
                    dataChild.setId(data.getId());
                    dataChildService.insertDataChild(dataChild);
                } else {
                    Long id = dataList.get(0).getId();
                    data.setId(id);
                    dataChild.setId(id);
                    dataService.updateData(data);
                    dataChildService.updateDataChild(dataChild);
                }
            }
            list.clear();
        }
    }
    public static Double convertToKB(String sizeWithUnit) {
        // 提取数值部分
        String numericPart = sizeWithUnit.replaceAll("[^0-9.]", "");
        // 提取单位部分
        String unitPart = sizeWithUnit.replaceAll("[^A-Za-z]", "").toUpperCase();

        double sizeInKB = 0.0;
        switch (unitPart) {
            case "MB":
                sizeInKB = Double.parseDouble(numericPart) * 1024;
                break;
            case "MBMB":
                sizeInKB = Double.parseDouble(numericPart) * 1024;
                break;
            case "GB":
                sizeInKB = Double.parseDouble(numericPart) * 1024 * 1024;
                break;
            case "KB":
                sizeInKB = Double.parseDouble(numericPart);
                break;
            default:
                sizeInKB = 0.0;
        }
        return sizeInKB;
    }

    private static String getDate(String str) {
        int year = 0;
        int month = 0;
        int day = 0;
        int index = 0;
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        while (matcher.find()) {
            if (index == 0) {
                year = Integer.parseInt(matcher.group());
            } else if (index == 1) {
                month = Integer.parseInt(matcher.group());
            } else if (index == 2) {
                day = Integer.parseInt(matcher.group());
            }
            index++;
        }
        if (year == 0 && month == 0 && day == 0) {
            return "0000-00-00";
        }
        return year + "-" + month + "-" + day;
    }

}
