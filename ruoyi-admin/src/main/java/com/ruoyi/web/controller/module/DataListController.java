package com.ruoyi.web.controller.module;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.module.domain.Data;
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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Document pageDoc = Jsoup.connect(pageUrl).proxy(proxyHost, proxyPort).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Elements pages = pageDoc.select("div.navigation div.pages a");
        Integer total = Integer.parseInt(pages.last().text());
        System.out.println(total);
        for (int i = 23; i < total; i++) {
            System.out.println("第---"+i+"---页");
            String url = "https://byruthub.org/page/" + i;
            Document doc = Jsoup.connect(url).proxy(proxyHost, proxyPort).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
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
                arr.add("downLoadCount-------" + shortItem.select("div.min-details span.pr4").text().replace(" ", ""));
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
                data.setReleaseDate(map.get("releaseDate"));
                data.setDeveloper(map.get("developers"));
                data.setScore(map.get("score"));
                data.setGameUrl(map.get("gameUrl"));
                data.setMinSpec(map.get("minSpec"));
                data.setRecommendSpec(map.get("recommendSpec"));
                data.setMinSpec2(map.get("minSpec2"));
                data.setGameSize(map.get("gameSize"));
                data.setGameDesc(map.get("desc"));
                data.setGameType(map.get("gameType"));
                data.setGameIntroduce(map.get("gameIntroduce"));
                data.setPublisher(map.get("publisher"));
                data.setTagList(map.get("tagList"));
                data.setSteamLatelyMixed(map.get("steamLatelyMixed"));
                data.setSteamLatelyReviewDesc(map.get("steamLatelyReviewDesc"));
                data.setSteamAllPositive(map.get("steamAllPositive"));
                data.setSteamAllReviewDesc(map.get("steamAllReviewDesc"));
                data.setWebmSource(map.get("webmSource"));
                data.setWebmHdSource(map.get("webmHdSource"));
                data.setMp4Source(map.get("mp4Source"));
                data.setMp4HdSource(map.get("mp4HdSource"));
                data.setPoster(map.get("poster"));
                data.setImage(map.get("image"));
                data.setCreateTime(new Date());
                data.setUpdateTime(new Date());



                List<Data> dataList = dataService.selectDataByTitle(data.getTitle());
                if (dataList.isEmpty()) {
                    dataService.insertData(data);
                } else {
                    data.setId(dataList.get(0).getId());
                    dataService.updateData(data);
                }
            }
            list.clear();
        }
    }
}
