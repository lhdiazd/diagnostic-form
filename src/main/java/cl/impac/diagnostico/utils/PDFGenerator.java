package cl.impac.diagnostico.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;

public class PDFGenerator {

	private static final String ICON_IMAGE_PATH = "static/images/iconos.png";
	private static final String LOGO_IMAGE_PATH = "static/images/logo.png";
	private static final float IMAGE_WIDTH = 150;
	private static final float IMAGE_HEIGHT = 150;
	private static final float ICON_IMAGE_POSITION = 120;
	private static final float LOGO_IMAGE_POSITION = 300;
	private static final float LINE_SPACING = 4;
	private static final float CELL_PADDING = 7;

	public static ByteArrayOutputStream generatePdfStream(EquipmentFormDTO equipmentFormDTO)
			throws DocumentException, MalformedURLException, IOException {

		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		addHeaderImages(document);

		document.add(new Paragraph("\n\n"));

		Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
		Font italicFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);

		addParagraph(document, "Nombre del Cuestionario: ", boldFont);
		addParagraph(document, equipmentFormDTO.getName());

		addParagraph(document, "Categorías del cuestionario:", boldFont);
		for (BaseCategory baseCategory : equipmentFormDTO.getBaseCategories()) {
			addParagraph(document, "- " + baseCategory.getName());
		}
		document.add(new Paragraph("\n"));

		PdfPTable table = new PdfPTable(5);
		setTableColumnWidths(table, document);

		if (equipmentFormDTO == null || equipmentFormDTO.getQuestions().isEmpty()) {
			document.add(new Paragraph("*El cuestionario no tiene preguntas asociadas", italicFont));
		} else {
			List<DiagnosticQuestion> sortedQuestions = equipmentFormDTO.getQuestions();
			Collections.sort(sortedQuestions, Comparator.comparingInt(DiagnosticQuestion::getOrderIndex));

			addTableHeader(table, new String[] { "Detalle de preguntas", "Sí", "No", "P", "F" }, boldFont);
			addRows(table, sortedQuestions);
		}

		document.add(table);
		document.close();
		return outputStream;
	}

	private static void addHeaderImages(Document document) throws DocumentException, IOException {
		addImage(document, ICON_IMAGE_PATH, IMAGE_WIDTH, IMAGE_HEIGHT, ICON_IMAGE_POSITION);
		addImage(document, LOGO_IMAGE_PATH, IMAGE_WIDTH, IMAGE_HEIGHT, LOGO_IMAGE_POSITION);
	}

	private static void addImage(Document document, String imagePath, float width, float height, float position)
			throws DocumentException, IOException {
		Image image = Image.getInstance(ClassLoader.getSystemResource(imagePath));
		image.scaleToFit(width, height);
		image.setAbsolutePosition(position, document.top() - 10);
		document.add(image);
	}

	private static void addParagraph(Document document, String content) throws DocumentException {
		Paragraph paragraph = new Paragraph(content);
		paragraph.setSpacingAfter(LINE_SPACING);
		document.add(paragraph);
	}

	private static void addParagraph(Document document, String content, Font font) throws DocumentException {
		Paragraph paragraph = new Paragraph(content, font);
		paragraph.setSpacingAfter(LINE_SPACING);
		document.add(paragraph);
	}

	private static void addTableHeader(PdfPTable table, String[] headings, Font font) {
		Stream.of(headings).forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.WHITE);
			header.setBorderWidth(1);
			header.setPhrase(new Phrase(columnTitle, font));
			header.setPadding(CELL_PADDING);
			table.addCell(header);
		});
	}

	private static void addRows(PdfPTable table, List<DiagnosticQuestion> diagnosticQuestions) {
		for (DiagnosticQuestion diagnosticQuestion : diagnosticQuestions) {
			PdfPCell cell = new PdfPCell(new Phrase(diagnosticQuestion.getDetalle()));
			cell.setPadding(CELL_PADDING);
			table.addCell(cell);

			PdfPCell emptyCell1 = new PdfPCell();
			emptyCell1.setPadding(CELL_PADDING);
			table.addCell(emptyCell1);

			PdfPCell emptyCell2 = new PdfPCell();
			emptyCell2.setPadding(CELL_PADDING);
			table.addCell(emptyCell2);

			PdfPCell emptyCell3 = new PdfPCell();
			emptyCell3.setPadding(CELL_PADDING);
			table.addCell(emptyCell3);

			PdfPCell emptyCell4 = new PdfPCell();
			emptyCell4.setPadding(CELL_PADDING);
			table.addCell(emptyCell4);
		}

	}

	private static void setTableColumnWidths(PdfPTable table, Document document) throws DocumentException {
		float[] columnWidths = { 9f, 1f, 1f, 1f, 1f };
		table.setWidths(columnWidths);
		table.setTotalWidth((document.getPageSize().getWidth()) - (document.leftMargin()) - (document.rightMargin()));
		table.setLockedWidth(true);
	}

}
