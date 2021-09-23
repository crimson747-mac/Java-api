package pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfParagraph {

    public static void main(String[] args) {
        Document doc = new Document();

        try {
            FileOutputStream fos = new FileOutputStream("paragraphDemo.pdf");
            PdfWriter.getInstance(doc, fos);
            doc.open();

            String content = "Your word is a lamp to my feet and light for my path";
            Paragraph par1 = new Paragraph(32);
            par1.setSpacingAfter(50);
            par1.setSpacingBefore(50);

            for(int i = 0; i < 20; i++) {
                Chunk chunk = new Chunk(content);
                par1.add(chunk);
            }
            doc.add(par1);

            Paragraph par2 = new Paragraph();
            for(int i = 0; i < 10; i++) {
                par2.add(new Chunk(content));
            }
            doc.add(par2);

            doc.close();
            System.out.println("paragraphDemo 생성완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
