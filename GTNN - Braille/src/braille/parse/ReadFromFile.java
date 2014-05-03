package braille.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadFromFile {

	private String fileContents = "";
	private String pathFile = "";

	public ReadFromFile(String path) {
		pathFile = path;
		
		String tempExtend = "";
		int index = path.length() - 1;
		while(path.charAt(index) != '.') {
			tempExtend += path.charAt(index);
			index--;
		}
		String extendOfFile = "";
		for (int i = tempExtend.length(); i > 0; i--) {
			extendOfFile += tempExtend.charAt(i - 1); 
		}
		
		if (extendOfFile.toUpperCase().equals("TXT")) {
			readTxtFile();
		} else if (extendOfFile.toUpperCase().equals("DOC")) {
			readDocFile();
		} else if (extendOfFile.toUpperCase().equals("PDF")) {
			readPdfFile();
		} else {
			JOptionPane.showMessageDialog(null, "File is not support !", "Error", JOptionPane.ERROR);
		}
	}

	public String getFileContents() {
		return fileContents;
	}

	private void readTxtFile() {
		try {
			FileReader iFile = new FileReader(pathFile);
			int charac = -1;
			try {
				while ((charac = iFile.read()) != -1)
					fileContents = fileContents + ((char) charac);
				if (iFile != null)
					iFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"FileNotFoundException\nPlease try again !", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("resource")
	private void readDocFile() {
		FileInputStream fStream;
		POIFSFileSystem fs = null;
		try {
			fStream = new FileInputStream(pathFile);
			fs = new POIFSFileSystem(fStream);
			HWPFDocument iFile = new HWPFDocument(fs);
			WordExtractor extractor = new WordExtractor(iFile);
			String[] paragraphs = extractor.getParagraphText();
			for (int i = 0; i < paragraphs.length; i++) {
				fileContents = fileContents + paragraphs[i];
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"IOException\nPlease try again !", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Exception:\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void readPdfFile() {
		PDDocument pdfDocument;
		try {
			pdfDocument = PDDocument.load(new File(pathFile));
			PDFTextStripper pdfStripper = new PDFTextStripper();
			fileContents = pdfStripper.getText(pdfDocument);
			pdfDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
