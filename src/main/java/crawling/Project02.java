package crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Project02 {
    public static void main(String[] args) {
        String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1";

        try {
            LocalDate now = LocalDate.now();
            now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            url = url + "&Base_de=" + now.toString() + "bibleType=1";

            Document doc = Jsoup.connect(url).post();

            System.out.println("=======================");
            Element bibleText = doc.select(".bible_text").first();
            System.out.println(bibleText.text());

            Element bibleInfo = doc.select(".bibleinfo_box").first();
            System.out.println(bibleInfo.text());

            Elements list = doc.select(".body_list > li");

            for (Element element : list) {
                System.out.println(
                    element.select(".num").first().text() + ": " +element.select(".info").first().text()
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
