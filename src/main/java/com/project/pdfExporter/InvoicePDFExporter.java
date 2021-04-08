package com.project.pdfExporter;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.project.dto.InvoiceProductDto;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class InvoicePDFExporter {

    @Autowired
    private ProductService productService;


    public void export(HttpServletResponse response) throws IOException {


        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();


        Image image = Image.getInstance("/Users/ruslan/Desktop/companyLogo.png");
        cb.addImage(image, 160, 0, 0, 40,40,780);

        //company Name
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Company Name", 550, 800, 0);

        //company Body
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Address", 550, 785, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "City and State", 550, 775, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "US", 550, 765, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Phone Number", 550, 755, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Email", 550, 745, 0);

        //bar code
        Barcode39 code39 = new Barcode39();
        Random random = new Random();
        code39.setCode(String.valueOf(random.nextInt(1000000)+1000000000));
        code39.setStartStopText(false);
        Image image39 = code39.createImageWithBarcode(cb, Color.BLACK, Color.WHITE);
        cb.addImage(image39, 120, 0, 0, 30,420,670);


        //vendor Name
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Vendor Name", 40, 650, 0);

        //vendor Body
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Address", 40, 635, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "City and State", 40, 625, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "US", 40, 615, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Phone Number", 40, 605, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Email", 40, 594, 0);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineWidth(45);
        lineSeparator.setLineColor(Color.LIGHT_GRAY);
        lineSeparator.drawLine(cb, 350,357,628);

        //Invoice
        cb.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Invoice #", 370, 640, 0);
        cb.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "65345", 425, 640, 0);

        //Order Type, Order Date header
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Order Type", 370, 620, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Order Date", 450, 620, 0);

        //Order Type, Order Date body
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "PURCHASE", 370, 610, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, LocalDate.now().toString(), 450, 610, 0);

        //Payment Method
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Payment Method", 40, 555, 0);

        // separating line
        lineSeparator.setLineWidth(3);
        lineSeparator.drawLine(cb, 40,555,550);

        //Check / Money order
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Check / Money order", 40, 535, 0);


//        Table table = new Table(4, 4);
//        table.setBorder(1);
//        table.setBorderColor(Color.LIGHT_GRAY);
//        Cell cell = new Cell("header");


        Font f = FontFactory.getFont(FontFactory.HELVETICA, 8);

        Table table = new Table(2);
        Phrase phrase = new Phrase("Hello World!", f);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);


//        table.addCell(new Paragraph("name:", f));
//        table.addCell(new Paragraph("Bruno Lowagie", f));
//        table.addCell(new Paragraph("date of birth:", f));
//        table.addCell(new Paragraph("June 10th, 1970", f));
//        table.addCell(new Paragraph("Study Program:", f));
//        table.addCell(new Paragraph("master in civil engineering", f));
//        table.addCell(new Paragraph("option:", f));
//        table.addCell(new Paragraph("architecture", f));


        document.add(table);

//        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 250, 650, 0);
//        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 250, 600, 0);
//
//        // we draw some text on a certain position
//        cb.setTextMatrix(100, 400);
//        cb.showText("Text at position 100,400.");
//
//        // we draw some rotated text on a certain position
//        cb.setTextMatrix(0, 1, -1, 0, 100, 300);
//        cb.showText("Text at position 100,300, rotated 90 degrees.");
//
//        // we draw some mirrored, rotated text on a certain position
//        cb.setTextMatrix(0, 1, 1, 0, 200, 200);
//        cb.showText("Text at position 200,200, mirrored and rotated 90 degrees.");
//
        cb.endText();
        cb.sanityCheck();
        document.close();
    }


}
