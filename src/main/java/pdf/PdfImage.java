package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfImage {

    public static void main(String[] args) {
        Document doc = new Document();
        String imagePath = "/Users/zeros/IdeaProjects/java-api/src/main/resources/images/image.jpeg";
        String imageUrl = "http://cdnimage.dailian.co.kr/news/202012/news_1608543090_948598_m_1.jpeg";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("imageDemo.pdf"));
            doc.open();

            Image image = Image.getInstance(imagePath);
            image.scaleAbsolute(400, 600); //비율이 깨짐
            doc.add(image);

            Image image2 = Image.getInstance(imageUrl);
            image2.scalePercent(100f); // 퍼센트로 비율유지
            doc.add(image2);

            Image image3 = Image.getInstance(imageUrl);
            image3.scaleToFit(400, 600); //픽셀에 맞게 원본 비율이 유지됨
            doc.add(image3);

            System.out.println("생성완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

}
