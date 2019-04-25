package token.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;

import token.entity.FlashcardInfo;

@Controller
public class CreateImage {
	@Autowired
	EmailSender sender;
	
	public static void drawText(String text, Graphics2D g2d, int yPOS, int width) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		g2d.drawString(text, width / 2 - stringLen / 2, yPOS);
	}

	public static void drawFlashCard(FlashcardInfo fcInfo, int index) throws IOException {
		int width = 400;
		int height = 200;

		// Constructs a BufferedImage of one of the predefined image types.
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Create a graphics which can be used to draw into the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();

		// fill all the image with white
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// create a string with yellow
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("MS Mincho", Font.BOLD, 50));
		drawText(fcInfo.getSurfaceForm(), g2d, 100, width);

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("MS Mincho", Font.BOLD, 20));
		drawText(fcInfo.getPartOfSpeech(), g2d, 20, width);
		drawText(fcInfo.getReading(), g2d, 125, width);
		if (!fcInfo.getReading().equals(fcInfo.getBaseForm())) {
			drawText(fcInfo.getBaseForm(), g2d, 150, width);
		}
		g2d.dispose();
		new File("flashcards").mkdir();
		File file = new File("flashcards/No" + index + ".png");
		ImageIO.write(bufferedImage, "png", file);
	}

	public static void zipFlashcards(){
		String zipFile = "flashcards.zip";
		String srcDir = "flashcards";
		try {
			// create byte buffer
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File dir = new File(srcDir);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				System.out.println("Adding file: " + files[i].getName());
				FileInputStream fis = new FileInputStream(files[i]);
				// begin writing a new ZIP entry, positions the stream to the
				// start of the entry data
				zos.putNextEntry(new ZipEntry(files[i].getName()));
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}
				zos.closeEntry();
				// close the InputStream
				fis.close();
			}
			// close the ZipOutputStream
			zos.close();
			FileUtils.forceDelete(new File("flashcards"));
		} catch (IOException ioe) {
			System.out.println("Error creating zip file" + ioe);
		}
	}
	@Test
	public void testFlashcardSender() throws IOException {
		FlashcardInfo fi1 = new FlashcardInfo("学生", "がくせい", "Noun", "がくせい", "N5");
		drawFlashCard(fi1, 1);
		FlashcardInfo fi2 = new FlashcardInfo("勉強します", "べんきょうします", "Verb", "勉強する", "N5");
		drawFlashCard(fi2, 2);
		zipFlashcards();
		sender.sendEmail("huutri8991@gmail.com","flashcard","flashcards for you");
		System.out.println("Done");
	}
}