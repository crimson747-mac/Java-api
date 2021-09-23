package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class Table {

    public static void main(String[] args) {
        String[] title = new String[] {"제목", "저자", "출판사", "이미지_URL"};
        String[][] rows = new String[][] {
            {"물리법칙의 이해", "리처드 파인먼", "해나무", "https://pbs.twimg.com/media/D66nvt6UcAApY7q.jpg"},
            {"Java의 정석", "남궁성", "도우출판", "https://spnimage.edaily.co.kr/images/Photo/files/NP/S/2019/08/PS19080200018.jpg"},
            {"리눅스 프로그래밍", "창병모", "생능출판", "http://cdnimage.dailian.co.kr/news/202012/news_1608543090_948598_m_1.jpeg"}
        };

        Document doc = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(new File("book.pdf")));
            doc.open();

            //한글 폰트
            BaseFont baseFont = BaseFont.createFont("/Users/zeros/IdeaProjects/java-api/src/main/resources/font/NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontTitle = new Font(baseFont, 12);
            Font fontRows = new Font(baseFont, 10);

            //table 생성
            PdfPTable table = new PdfPTable(title.length);
            table.setWidthPercentage(100);

            //table-column 생성
            float[] colWidth = new float[] {20f, 15f, 15f, 30f};
            table.setWidths(colWidth);

            for(String header : title) {
                //cell 생성 및 설정
                PdfPCell cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(10);
                cell.setGrayFill(0.9f);

                //cell 에 들어갈 내용
                cell.setPhrase(new Phrase(header, fontTitle));

                //table 에 cell 부착
                table.addCell(cell);
            }
            table.completeRow();

            //본문
            for(String[] row : rows) {
                for(String data : row) {
                    Phrase phrase = new Phrase(data, fontRows);
                    PdfPCell cell = new PdfPCell(phrase);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPaddingTop(20);
                    cell.setPaddingRight(30);
                    cell.setPaddingLeft(30);
                    cell.setPaddingBottom(20);
                    table.addCell(cell);
                }
                table.completeRow();
            }

            PdfPCell cell4 = new PdfPCell(new Phrase("Cell 5"));
            cell4.setColspan(2);

            PdfPCell cell5 = new PdfPCell(new Phrase("Cell 6"));
            cell5.setColspan(2);

            table.addCell(cell4);
            table.addCell(cell5);
            table.completeRow();

            doc.addTitle("PDF Table Demo");
            doc.add(table);

            System.out.println("테이블 셍성 완료");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }

    }

}
