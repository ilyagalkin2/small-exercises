import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest_02day_01 {

    public static void main(String[] args) throws IOException {
        String date = "08/08/2022";
        Document doc = Jsoup.connect("https://www.cbr.ru/scripts/XML_daily.asp?date_req=").get();
        System.out.println(doc.title());
        Elements valutes = doc.select("Valute");
        for (Element valute : valutes) {
            if (valute.attr("ID").equals("R01235")) {
                System.out.println(valute.select("Value").text());
            }
        }
    }

}

//Document doc = Jsoup.connect("https://lenta.ru/rss").get();
//        System.out.println(doc.title());
//        Elements newsHeadLines = doc.select("item");
//        for (Element headline : newsHeadLines) {
//            System.out.println(newsHeadLines.text());
//        }
