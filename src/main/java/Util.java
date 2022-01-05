import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class Util {


    public void toHTML(File file, String filePath) {
        try (Writer output = new PrintWriter(filePath, StandardCharsets.UTF_8)) {
            PDDocument pdf = PDDocument.load(file);
            new PDFDomTree().writeText(pdf, output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void toTEXT(File file, String filePath) {


    }

    public void toDOCX(File file, String filePath) {

    }


}
