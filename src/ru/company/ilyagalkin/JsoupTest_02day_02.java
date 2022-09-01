import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest_02day_02 {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://lenta.ru/rss").get();
        int number = 1;
        int index =number - 1;
        Element news = doc.select("item").get(index);
        String category = news.select("category").text();
        String title = news.select("title").text();
        String link = news.select("link").text();
        String description = news.select("description").text();
        String result = category + "\n" + title + "\n" + description +"\n" + link;
        System.out.println(result);
    }

}

//Бывший СССР
//В Закарпатье заявили о покупке земли Украины компаниями неизвестных владельцев
//Крупные компании неизвестных владельцев еще до моратория на продажу сельскохозяйственных угодий арендовали земли на Украине и с 2024 года будут иметь приоритетное право для их покупки после снятия моратория. Об этом заявил председатель Общества венгерской культуры Закарпатья Ласло Брензович.
//https://lenta.ru/news/2022/08/10/ukraina_zeml/

//Document doc = Jsoup.connect("https://lenta.ru/rss").get();
//        System.out.println(doc.title());
//        Elements newsHeadLines = doc.select("item");
//        for (Element headline : newsHeadLines) {
//            System.out.println(newsHeadLines.text());
//        }
