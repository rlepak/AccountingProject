package com.project.pdfExporter;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.project.dto.InvoiceProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseInvoicePDFExporter {

    private List<InvoiceProductDto> list = new ArrayList<>();

    public PurchaseInvoicePDFExporter(List<InvoiceProductDto> invoiceList) {
        this.list = invoiceList;
    }

    public void export(HttpServletResponse response) throws IOException, IOException {


        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();


        Image image = Image.getInstance("/Users/ruslan/Desktop/companyLogo.png");
        cb.addImage(image, 160, 0, 0, 40, 40, 780);

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
        code39.setCode(String.valueOf(random.nextInt(1000000) + 1000000000));
        code39.setStartStopText(false);
        Image image39 = code39.createImageWithBarcode(cb, Color.BLACK, Color.WHITE);
        cb.addImage(image39, 120, 0, 0, 30, 420, 670);


        //vendor Name
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, list.get(0).getInvoice().getVendorClient().getCompanyName(), 40, 650, 0);

        //vendor Body
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, list.get(0).getInvoice().getVendorClient().getAddress(), 40, 635, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, list.get(0).getInvoice().getVendorClient().getState() + ", " + list.get(0).getInvoice().getVendorClient().getZipCode(), 40, 625, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Phone: " + list.get(0).getInvoice().getVendorClient().getPhone(), 40, 615, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, list.get(0).getInvoice().getVendorClient().getEmail(), 40, 605, 0);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineWidth(45);
        lineSeparator.setLineColor(Color.LIGHT_GRAY);
        lineSeparator.drawLine(cb, 350, 357, 628);

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
        lineSeparator.drawLine(cb, 40, 555, 550);

        //Check / Money order
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 10);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Check / Money order", 40, 535, 0);


        Font f = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);

        PdfPTable table = new PdfPTable(5);

        float[] outer = {15, 300, 40, 40, 40};
        table.setWidths(outer);

        PdfPCell cell;


        cell = new PdfPCell(new Paragraph("#", headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(Color.GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Product", headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setBorderColor(Color.GRAY);
        table.addCell(cell);


        cell = new PdfPCell(new Paragraph("Price", headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setBorderColor(Color.GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Quantity", headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setBorderColor(Color.GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total", headerFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setBorderColor(Color.GRAY);
        table.addCell(cell);


        table.setTotalWidth(515);

        double quantity = 0;
        double amount = 0;

        double totalAmount = 0;
        double totalQuantity = 0;


        for (int i = 0; i < list.size(); i++) {

            cell = new PdfPCell(new Paragraph(String.valueOf(i + 1), f));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorderColor(Color.GRAY);
            cell.setFixedHeight(20);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(list.get(i).getProduct().getName() + "\n" + list.get(i).getProduct().getDescription(), f));
            cell.setBorderColor(Color.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(list.get(i).getUnitPrice()), f));
            cell.setBorderColor(Color.GRAY);
            table.addCell(cell);

            quantity = list.get(i).getQuantity();
            cell = new PdfPCell(new Paragraph(String.valueOf(quantity), f));
            totalQuantity += quantity;
            cell.setBorderColor(Color.GRAY);
            table.addCell(cell);

            amount = list.get(i).getQuantity() * list.get(i).getUnitPrice();
            cell = new PdfPCell(new Paragraph(String.valueOf(amount), f));
            totalAmount += amount;
            cell.setBorderColor(Color.GRAY);
            table.addCell(cell);

        }

        table.writeSelectedRows(0, list.size() + 5, 40, 520, cb);

        int endOfMainTableAlignment = 520 - (int) table.getTotalHeight();

        PdfPTable resultTable = new PdfPTable(2);
        float[] resultTableWidth = {53.3F, 26.7F};
        resultTable.setWidths(resultTableWidth);
        resultTable.setTotalWidth(142);

        String[] secondTableKeys = {"Total Quantity", "Subtotal", "Tax", "Grand Total"};
        String[] secondTableValues = {String.valueOf(totalQuantity), String.valueOf(totalAmount),
                String.valueOf(totalAmount * 0.09), String.valueOf(totalAmount + totalAmount * 0.09)};

        for (int i = 0; i < secondTableKeys.length; i++) {

            if (i != secondTableKeys.length - 1) {
                cell = new PdfPCell(new Paragraph(secondTableKeys[i], headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderColor(Color.GRAY);
                cell.setFixedHeight(20);
                resultTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(secondTableValues[i], headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderColor(Color.GRAY);
                resultTable.addCell(cell);
            } else {
                cell = new PdfPCell(new Paragraph(secondTableKeys[i], headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderColor(Color.GRAY);
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setFixedHeight(20);
                resultTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(secondTableValues[i], headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderColor(Color.GRAY);
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                resultTable.addCell(cell);
            }
        }


        resultTable.writeSelectedRows(0, 4, 413, endOfMainTableAlignment, cb);

        int endOfMSecondTableAlignment = endOfMainTableAlignment - (int) resultTable.getTotalHeight();

        // separating line
        lineSeparator.setLineWidth(3);
        lineSeparator.drawLine(cb, 40, 555, endOfMSecondTableAlignment - 30);


        //vendor Name
        cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 14);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Thank you for your business!", 395, 30, 0);


        //end
        cb.endText();
        cb.sanityCheck();
        document.close();


    }
}
