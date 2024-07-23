package com.ruoyi.web.controller.module;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
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
        for (int i = 1; i < 4; i++) {
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
                String arrDetail =
                        "{\"ele\":\"div.min-details span.pr4\",\"type\":\"text\",\"key\":\"downLoadCount\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.poster-imgbox img.imgbox\",\"type\":\"src\",\"key\":\"img\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details div.hname h1\",\"type\":\"text\",\"key\":\"title\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details div.hname div.subhnamever\",\"type\":\"text\",\"key\":\"subhnamever\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details div.hname div.subhname\",\"type\":\"text\",\"key\":\"subhname\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +


                                "{\"ele\":\"div.fmainwrap div.game_details ul.ul-details li span.dateym\",\"type\":\"text\",\"key\":\"releaseDate\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details ul.ul-details li\",\"type\":\"text\",\"key\":\"developer\",\"eleIndex\":\"1\",\"ownText\":\"1\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details ul.ul-details li.ulgenre a\",\"type\":\"text\",\"key\":\"gameType\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details div.low-info div.ratingbox div.rate-label\",\"type\":\"text\",\"key\":\"score\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.fmainwrap div.game_details div.tquote\",\"type\":\"text\",\"key\":\"update\",\"eleIndex\":\"0\",\"ownText\":\"1\"}-," +


                                "{\"ele\":\"div.game_desc\",\"type\":\"text\",\"key\":\"desc\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.scrblock a\",\"type\":\"href\",\"key\":\"descHeightImg\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.scrblock a img\",\"type\":\"src\",\"key\":\"descImg\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div#microtrailer div.iframe-wrap video source\",\"type\":\"src\",\"key\":\"descVideo\",\"eleIndex\":\"1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.block_down_game div.block_down a.itemdown_games\",\"type\":\"href\",\"key\":\"gameUrl\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.persize_bottom\",\"type\":\"text\",\"key\":\"gameSize\",\"eleIndex\":\"-1\",\"ownText\":\"0\"}-," +
                                "{\"ele\":\"div.tech_details-block\",\"type\":\"text\",\"key\":\"minSpec\",\"eleIndex\":\"1\",\"ownText\":\"0\"}-,";
                String[] splitDetail = arrDetail.split("-,");
                List<String> arr = extracted(shortItem, splitDetail);

                Map<String, String> map = new LinkedHashMap<>();

                for (String c : arr) {
                    String[] arr2 = c.split("-------");
                    map.put(arr2[0], arr2[1]);
                }

                Data data = new Data();
                data.setDownLoadCount(Integer.valueOf(map.get("downLoadCount").replace(" ", "")));
                data.setImg(map.get("img"));
                data.setTitle(map.get("title"));
                data.setSubhnamever(map.get("subhnamever"));
                data.setSubhname(map.get("subhname"));
                data.setReleaseDate(map.get("releaseDate"));
                data.setDeveloper(map.get("developer"));
                data.setGameType(map.get("gameType"));
                data.setScore(map.get("score"));
                data.setUpdate(Long.parseLong(map.get("update")));
                data.setDescHeightImg(map.get("descHeightImg"));
                data.setDescImg(map.get("descImg"));
                data.setDescVideo(map.get("descVideo"));
                data.setMovieMax(map.get("movieMax"));
                data.setGameUrl(map.get("gameUrl"));
                data.setMinSpec(map.get("minSpec"));
                data.setDesc(map.get("desc"));
                data.setChTitle(map.get("chTitle"));
                data.setGameSize(map.get("gameSize"));


                List<Data> dataList = dataService.selectDataByTitle(data.getTitle());
                if (dataList.isEmpty()) {
                    System.out.print("add");
                    System.out.println(map);
                    dataService.insertData(data);
                } else {
                    System.out.print("edit");
                    System.out.println(map);
                    data.setId(dataList.get(0).getId());
                    dataService.updateData(data);
                }
            }
        }
    }

    private static List<String> extracted(Elements movies, String[] eleArr) throws Exception {
        String keyValue = "";
        StringBuilder keyValue2 = new StringBuilder();
        String translateKeyValue = "";
        String movieMax = "";
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
                } else {
                    Elements links = movie.select(ele.toString());
                    keyValue2.setLength(0);
                    for (Element link : links) {
                        keyValue2.append(link.attr(type.toString())).append(";");
                        keyValue = keyValue2.toString();
                    }
                }
                if (keyName.toString().equals("update") || keyName.toString().equals("title") || keyName.toString().equals("descHeightImg") || keyName.toString().equals("descImg") || keyName.toString().equals("gameUrl") || keyName.toString().equals("descVideo") || keyName.toString().equals("downLoadCount")) {
                    if (keyName.toString().equals("title")) {
                        translateKeyValue = translate("auto", "zh-CN", keyValue);
                    }
                    if (keyName.toString().equals("update")){
                        String translateDate = translate("ru", "zh-CN", keyValue);
                        keyValue = String.valueOf(new Date().getTime());
                    }
                    if (keyName.toString().equals("descVideo")) {
                        movieMax = keyValue.replace("microtrailer","movie_max");
                    }
                } else if (keyName.toString().equals("img")) {
                    keyValue = "https://byruthub.org/" + keyValue.substring(0, keyValue.length() - 1);
                } else {
                    keyValue = translate("auto", "zh-CN", keyValue);
                }
                list.add(keyName + "-------" + keyValue);
            }
        }
        list.add("chTitle-------" + translateKeyValue);
        list.add("movieMax-------" + movieMax);
        return list;
    }

    public static String translate(String langFrom, String langTo, String word) throws Exception {


        String url = "https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

        URL obj = new URL(url);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);


        con.usingProxy();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResult(response.toString());
    }

    private static String parseResult(String inputJson) {
        JSONArray jsonArray = new JSONArray(inputJson);
        Object o = jsonArray.get(0);
        JSONArray jsonArray2 = new JSONArray(o);

        // 获取第一层数组的第一个元素，它本身也是一个JSONArray
        JSONArray firstLevelArray = jsonArray2.getJSONArray(0);

        // 获取第二层数组的第一个元素，同样是一个JSONArray
        JSONArray secondLevelArray = firstLevelArray.getJSONArray(0);

        // 获取第三层数组
        JSONArray thirdLevelArray = secondLevelArray.getJSONArray(0);

        // 提取"你好世界"
        String helloWorld = thirdLevelArray.getString(0);

        return helloWorld;
    }
}
