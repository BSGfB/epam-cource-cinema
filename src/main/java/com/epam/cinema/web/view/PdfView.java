package com.epam.cinema.web.view;

import com.epam.cinema.model.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(
            Map<String, Object> map,
            Document document,
            PdfWriter pdfWriter,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        map.forEach((key, val) -> {
            switch (key) {
                case "tickets":
                    try {
                        if (val instanceof List) {
                            document.add(getTicketsTable((List) val));
                        }
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
    }

    private static PdfPTable getTicketsTable(final List tickets) {
        final PdfPTable table = new PdfPTable(4);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);

        table.addCell("User id");
        table.addCell("Event id");
        table.addCell("Event date and time");
        table.addCell("Seat number");


        tickets.stream()
                .filter(Ticket.class::isInstance)
                .forEach(o -> {
                    Ticket ticket = (Ticket) o;
                    table.addCell(ticket.getUser().getId().toString());
                    table.addCell(ticket.getEvent().getId().toString());
                    table.addCell(ticket.getDateTime().toString());
                    table.addCell(String.valueOf(ticket.getSeat()));
                });

        return table;
    }
}
