package com.epam.cinema.web.view;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.epam.cinema.web.view.PdfView.getTicketsTable;

public class PdfHttpMessageConverter implements HttpMessageConverter {
    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_PDF);
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        System.out.println("mediaType: " + mediaType);
        Document document = new Document(PageSize.A4, 10f, 10f, 10f, 0f);
        try {

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);

            document.open();
            document.newPage();
            document.add(getTicketsTable((List) o));
            document.close();

            httpOutputMessage.getHeaders().add("Content-Type", "application/pdf");

            httpOutputMessage.getBody().write(os.toByteArray());

        } catch (DocumentException e) {
            e.printStackTrace();
        }



    }


}
