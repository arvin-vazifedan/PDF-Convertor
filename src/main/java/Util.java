import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.fit.pdfdom.PDFDomTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {

    public void toText(File file, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {
            //load pdf file
            PDDocument document = PDDocument.load(file);
            //get text from pdf document
            PDFTextStripper stripper = new PDFTextStripper();
            //write given text to output file
            writer.write(stripper.getText(document));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void toHTML(File file, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardCharsets.UTF_8)) {
            //load pdf file
            PDDocument pdf = PDDocument.load(file);
            //create a new pdf DOM and write to output file
            new PDFDomTree().writeText(pdf, writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
