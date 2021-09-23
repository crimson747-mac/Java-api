package crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;

public class Project03 {
    public static void main(String[] args) {
        String url = "https://sum.su.or.kr:8888/bible/today";

        try {
            Document doc = Jsoup.connect(url).get();
            Element source = doc.select("source").first();
            String srcUrl = source.attr("src").trim();
            String filename = srcUrl.substring(srcUrl.lastIndexOf("/") + 1);

            Runnable r = new DownloadBroker(srcUrl, filename);
            Thread download = new Thread(r);
            download.start();

            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DownloadBroker implements Runnable {
        String srcUrl;
        String filename;

        public DownloadBroker(String srcUrl, String filename) {
            this.srcUrl = srcUrl;
            this.filename = filename;
        }

        @Override
        public void run() {
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                URL url = new URL(srcUrl);
                InputStream is = url.openStream();
                BufferedInputStream input = new BufferedInputStream(is);

                int data;
                while((data = input.read()) != -1) {
                    bos.write(data);
                }

                bos.close();
                input.close();
                System.out.println("다운로드 완료: " + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
