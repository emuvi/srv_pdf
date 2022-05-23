package br.net.pin.srv_pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDFService {
  @PostMapping("/extract")
  public @ResponseBody DidExtract extract(@RequestBody ForExtract forExtract) throws IOException {
    var document = PDDocument.load(new File(forExtract.pdfPath));
    var response = new DidExtract();
    if (forExtract.textsOfPage != null) {
      response.textsOfPage = PDFWorker.getTexts(document, forExtract.textsOfPage);
    }
    if (forExtract.imageOfPage != null) {
      response.imageOfPage = PDFWorker.getImage(document, forExtract.imageOfPage);
    }
    document.close();
    return response;
  }
}