// Interface for document operations
interface Document {
    void print();
    void save(String content);
    void open();
}

class PDFDocument implements Document {
    private String content;

    @Override
    public void print() {
        System.out.println("Printing PDF document.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("Saving content to PDF document.");
    }

    @Override
    public void open() {
        System.out.println("Opening PDF document.");
    }
}

class WordDocument implements Document {
    private String content;

    @Override
    public void print() {
        System.out.println("Printing Word document.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("Saving content to Word document.");
    }

    @Override
    public void open() {
        System.out.println("Opening Word document.");
    }
}

class ExcelDocument implements Document {
    private String content;

    @Override
    public void print() {
        System.out.println("Printing Excel document.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("Saving content to Excel document.");
    }

    @Override
    public void open() {
        System.out.println("Opening Excel document.");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

// creating documents
class PDFDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// loging messages
class Logger {
    public static void log(String message) {
        System.out.println("LOG: " + message);
    }
}

public class DocumentGenerationSystem {
    public static void main(String[] args) {
        // Create and use PDF document
        DocumentFactory pdfFactory = new PDFDocumentFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();
        pdf.save("PDF Content");
        pdf.print();
        Logger.log("PDF document operations completed.");

        // Create and use Word document
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document word = wordFactory.createDocument();
        word.open();
        word.save("Word Content");
        word.print();
        Logger.log("Word document operations completed.");

        // Create and use Excel document
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excel = excelFactory.createDocument();
        excel.open();
        excel.save("Excel Content");
        excel.print();
        Logger.log("Excel document operations completed.");
    }
}
