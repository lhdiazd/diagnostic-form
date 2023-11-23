package cl.impac.diagnostico.controllers;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.utils.PDFGenerator;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/formularios/pdf")
public class PDFController {
		
	
	@PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody EquipmentFormDTO equipmentFormDTO) {
        try {
            ByteArrayOutputStream pdfStream = PDFGenerator.generatePdfStream(equipmentFormDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "output.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfStream.toByteArray());
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Log the exception properly in a real application
            return ResponseEntity.status(500).body(null);
        }
    }
	
	
}
