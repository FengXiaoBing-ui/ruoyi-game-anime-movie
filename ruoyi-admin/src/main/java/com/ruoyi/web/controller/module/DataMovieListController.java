package com.ruoyi.web.controller.module;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.module.domain.DataMovie;
import com.ruoyi.module.service.IDataMovieService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/module/DataMovieListController")
public class DataMovieListController extends BaseController {

    @Autowired
    private IDataMovieService dataService;

    @Anonymous
    @GetMapping(value = "add")
    public AjaxResult getInfo() throws Exception {
//        getGameInfo(1,1570);
//        getGameInfo(2,620);
//        getGameInfo(4,150);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    getGameInfo(1,5,"电影");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    getGameInfo(2,5,"电视剧");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                try {
                    getGameInfo(4,5,"综艺");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable3).start();
        return success();
    }

    @Anonymous
    @GetMapping(value = "getVideo")
    public AjaxResult getVideo(String url,Long id,String type) throws Exception {

        return success(getVideoInfo(url,id,type));
    }

    public void main(String[] args) throws Exception {
        getGameInfo(1,1570,"电影");
    }

    private void getGameInfo(int zmti,int total,String category) throws Exception {
        for (int i = total; i > 0; i--) {
            String url1 = "https://www.henglihuagong.com/zmti/"+zmti+"-" + i + ".html";
            Document homeDoc = Jsoup.connect(url1).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
            Elements homeDocEle = homeDoc.select("ul.stui-vodlist li");
            List<String> list = Lists.newArrayList();
            for (int j = 0; j < homeDocEle.size(); j++) {
                Element homeDocEleLi = homeDocEle.get(j);
                list.add(homeDocEleLi.select("a.stui-vodlist__thumb").attr("href"));
            }
            for (int item = list.size() - 1; item >= 0; item--) {
                System.out.println("第"+i+"頁-----第"+item+"個影片");
                List<String> dataList = Lists.newArrayList();
                Document detailDoc = Jsoup.connect("https://www.henglihuagong.com/" + list.get(item)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                Elements detailDocEle = detailDoc.select("div.stui-pannel");
                String title = detailDoc.select("div.stui-content__detail h1").text();
                String info = detailDoc.select("div.stui-content__detail p.data").get(0).text();
                String image = detailDoc.select("div.stui-content__thumb a img").attr("data-original");
                System.out.println(image);
                String type = "", area = "", year = "", score = "";
                type = info.substring(info.indexOf("类型：") + "类型：".length(), info.indexOf("地区：")).trim();
                area = info.substring(info.indexOf("地区：") + "地区：".length(), info.indexOf("年份：")).trim();
                year = info.substring(info.indexOf("年份：") + "年份：".length(), info.indexOf("评分：")).trim();
                String[] typeArr = info.split(" ");
                score = typeArr[typeArr.length - 1].split("：")[1];

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

                for (int panel = 1; panel < detailDocEle.size() - 2; panel++) {
                    Element aHref = detailDocEle.get(panel);
                    Elements aList = aHref.select("ul.stui-content__playlist li");
                    if (panel == 1) {
                        for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                            linkHref1.append(aList.get(aListItem).select("a").attr("href") + ";");
                            movieUrl1.append(" ;");
                        }
                        hrefText1 = aHref.select("ul.stui-content__playlist a").text();
                        hrefName1 = aHref.select("div.stui-pannel__head h3").text();
                    }
                    if (panel == 2) {
                        for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                            linkHref2.append(aList.get(aListItem).select("a").attr("href") + ";");
                            movieUrl2.append(" ;");
                        }
                        hrefText2 = aHref.select("ul.stui-content__playlist a").text();
                        hrefName2 = aHref.select("div.stui-pannel__head h3").text();
                    }
                    if (panel == 3) {
                        for (int aListItem = 0; aListItem < aList.size(); aListItem++) {
                            linkHref3.append(aList.get(aListItem).select("a").attr("href") + ";");
                            movieUrl3.append(" ;");
                        }
                        hrefText3 = aHref.select("ul.stui-content__playlist a").text();
                        hrefName3 = aHref.select("div.stui-pannel__head h3").text();
                    }
                }


                dataList.add("category-------"+category);
                dataList.add("image-------"+image);
                dataList.add("title-------" + title);
                dataList.add("type-------" + type);
                dataList.add("area-------" + area);
                dataList.add("year-------" + year);
                dataList.add("score-------" + score);
                dataList.add("language-------" + language);
                dataList.add("performer-------" + performer);
                dataList.add("director-------" + director);
                dataList.add("edition-------" + edition);
                dataList.add("updateMovie-------" + updateMovie);
                dataList.add("desc-------" + desc);
                dataList.add("desc2-------" + desc2);

                dataList.add("hrefName1-------" + hrefName1);
                dataList.add("hrefText1-------" + hrefText1);
                dataList.add("linkHref1-------" + linkHref1);
                dataList.add("movieUrl1-------" + movieUrl1);

                dataList.add("hrefName2-------" + hrefName2);
                dataList.add("hrefText2-------" + hrefText2);
                dataList.add("linkHref2-------" + linkHref2);
                dataList.add("movieUrl2-------" + movieUrl2);

                dataList.add("hrefName3-------" + hrefName3);
                dataList.add("hrefText3-------" + hrefText3);
                dataList.add("linkHref3-------" + linkHref3);
                dataList.add("movieUrl3-------" + movieUrl3);

                Map<String, String> map = new LinkedHashMap<>();

                for (String c : dataList) {
                    String[] arr2 = c.split("-------");
                    if (arr2.length > 1) {
                        map.put(arr2[0], arr2[1]);
                    }
                }
                DataMovie data = new DataMovie();
                data.setMovieTitle(map.get("title"));
                data.setImage(map.get("image"));
                data.setMovieType(map.get("type"));
                data.setMovieArea(map.get("area"));
                data.setMovieYear(map.get("year"));
                data.setMovieScore(map.get("score"));
                data.setMovieLanguage(map.get("language"));
                data.setPerformer(map.get("performer"));
                data.setDirector(map.get("director"));
                data.setMovieEdition(map.get("edition"));
                data.setUpdateMovie(map.get("updateMovie"));
                data.setMovieDesc(map.get("desc"));
                data.setMovieDesc2(map.get("desc2"));
                data.setHrefName1(map.get("hrefName1"));
                data.setHrefText1(map.get("hrefText1"));
                data.setLinkHref1(map.get("linkHref1"));
//                data.setMovieUrl1(map.get("movieUrl1"));
                data.setHrefName2(map.get("hrefName2"));
                data.setHrefText2(map.get("hrefText2"));
                data.setLinkHref2(map.get("linkHref2"));
//                data.setMovieUrl2(map.get("movieUrl2"));
                data.setHrefName3(map.get("hrefName3"));
                data.setHrefText3(map.get("hrefText3"));
                data.setLinkHref3(map.get("linkHref3"));
//                data.setMovieUrl3(map.get("movieUrl3"));
                data.setCategory(map.get("category"));
                data.setCreateTime(new Date());
                data.setUpdateTime(new Date());

                List<DataMovie> dataMovies = dataService.selectDataByTitle(data.getMovieTitle());
                if (dataMovies.isEmpty()) {
                    dataService.insertDataMovie(data);
                } else {
                    data.setId(dataMovies.get(0).getId());
                    dataService.updateDataMovie(data);
                }
            }
        }
    }

    private String getVideoInfo(String url,Long id,String type) throws Exception{
        Document videoDoc = Jsoup.connect("https://www.henglihuagong.com/" + url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
        Element videoDocEle = videoDoc.select("div.stui-player__video script").get(0);
        String movieUrl1 = "";
        // 正则表达式匹配url字段
        Pattern pattern = Pattern.compile("url\":\\s*\"([^\"]+)");
        Matcher matcher = pattern.matcher(videoDocEle.html());
        if (matcher.find()) {
            // 提取url并打印
            movieUrl1 = matcher.group(1).replace("\\", "");
        } else {
            System.out.println("URL not found.");
        }
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder url1 = new StringBuilder();
                    StringBuilder url2 = new StringBuilder();
                    StringBuilder url3 = new StringBuilder();
                    DataMovie dataMovie = new DataMovie();

                    if(type.equals("movieUrl1")){
                        DataMovie dataList1 = dataService.selectMovieUrl1(id);
                        String[] arr1 = dataList1.getLinkHref1().split(";");
                        for (int i = 0; i < arr1.length; i++) {
                            Document doc1 = Jsoup.connect("https://www.henglihuagong.com/" + arr1[i]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                            Element docEle1 = doc1.select("div.stui-player__video script").get(0);
                            Matcher mat1 = pattern.matcher(docEle1.html());
                            if (mat1.find()) {
                                // 提取url并打印
                                url1.append(mat1.group(1).replace("\\", "")+";");
                            } else {
                                System.out.println("URL not found.");
                            }
                        }
                        dataMovie.setMovieUrl1(String.valueOf(url1));
                        dataMovie.setId(id);
                        dataService.updateMovieUrl(dataMovie);
                    }
                    if(type.equals("movieUrl2")){
                        DataMovie dataList2 = dataService.selectMovieUrl2(id);
                        String[] arr2 = dataList2.getLinkHref1().split(";");
                        for (int i = 0; i < arr2.length; i++) {
                            Document doc2 = Jsoup.connect("https://www.henglihuagong.com/" + arr2[i]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                            Element docEle2 = doc2.select("div.stui-player__video script").get(0);
                            Matcher mat2 = pattern.matcher(docEle2.html());
                            if (mat2.find()) {
                                // 提取url并打印
                                url2.append(mat2.group(1).replace("\\", "")+";");
                            } else {
                                System.out.println("URL not found.");
                            }
                        }
                        dataMovie.setMovieUrl1(String.valueOf(url2));
                        dataMovie.setId(id);
                        dataService.updateMovieUrl(dataMovie);
                    }
                    if(type.equals("movieUrl3")){
                        DataMovie dataList3 = dataService.selectMovieUrl3(id);
                        String[] arr3 = dataList3.getLinkHref1().split(";");
                        for (int i = 0; i < arr3.length; i++) {
                            Document doc3 = Jsoup.connect("https://www.henglihuagong.com/" + arr3[i]).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36").data().get();
                            Element docEle3 = doc3.select("div.stui-player__video script").get(0);
                            Matcher mat3 = pattern.matcher(docEle3.html());
                            if (mat3.find()) {
                                // 提取url并打印
                                url3.append(mat3.group(1).replace("\\", "")+";");
                            } else {
                                System.out.println("URL not found.");
                            }
                        }
                        dataMovie.setMovieUrl1(String.valueOf(url3));
                        dataMovie.setId(id);
                        dataService.updateMovieUrl(dataMovie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable1).start();

        return movieUrl1;
    }

}
