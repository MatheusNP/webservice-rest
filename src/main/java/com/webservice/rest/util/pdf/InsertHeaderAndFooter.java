package com.webservice.rest.util.pdf;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class InsertHeaderAndFooter {

	public void addFooter(String src, String dest, String hash) throws FileNotFoundException, IOException {

		PdfDocument pdfDoc =
			    new PdfDocument(new PdfReader(src), new PdfWriter(dest));

		Rectangle pageSize;
		PdfCanvas canvas;
		int n = pdfDoc.getNumberOfPages();
		for (int i = 1; i <= n; i++) {
		    PdfPage page = pdfDoc.getPage(i);
		    pageSize = page.getPageSize();
		    canvas = new PdfCanvas(page);

		    //Draw hash code
		    canvas.beginText().setFontAndSize(
		            PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
		            .moveText(3, 10)
		            .showText(hash)
		            .endText();

		    canvas.saveState();
		    canvas.restoreState();
		}

		pdfDoc.close();
	}
}
