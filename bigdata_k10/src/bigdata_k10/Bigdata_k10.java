/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata_k10;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
/**
 *
 * @author revanth
 */
public class Bigdata_k10 {
public static void main(String[] args) throws FailingHttpStatusCodeException, 
					MalformedURLException, IOException, FileNotFoundException {
	 String COMMA_DELIMITER = ",";
   String NEW_LINE_SEPARATOR = "\n";
    //CSV file header
    String FILE_HEADER = "number, file_link, Data, global, strategy, accelerate, information, technology, results, change, development, potential, future, innovation, analytics";
	// Initailzing Webclient Object to imitate chrome browser
      File file = new File("C:\\Users\\revanth\\Desktop\\its\\file.txt");
 Scanner scanner = new Scanner(file);
 int i=0;
 String []filenew=new String[10];
            while (scanner.hasNextLine()) {
              filenew[i] = scanner.nextLine();
                               i++;
            }
           
WebClient webClient = new WebClient(BrowserVersion.CHROME);
	webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	webClient.getOptions().setThrowExceptionOnScriptError(false);
     String []searchString={"data", "global", "strategy", "accelerate", "information", "technology", "results", "change", "development", "potential", "future", "innovation", "analytics"}; 
     FileWriter fileWriter = null;
      String fileName="C:\\Users\\revanth\\Desktop\\output1.csv";
        try {
         
            fileWriter = new FileWriter(fileName);
fileWriter.append(FILE_HEADER);
fileWriter.append(NEW_LINE_SEPARATOR);
for(int k=0;k<filenew.length;k++){
HtmlPage page = (HtmlPage) webClient.getPage(filenew[k]);
String pageContent=page.asText();
pageContent=StringUtils.lowerCase(pageContent);
System.out.println(k);
fileWriter.append(String.valueOf(k));
fileWriter.append(COMMA_DELIMITER);
fileWriter.append(filenew[k]);
fileWriter.append(COMMA_DELIMITER);
for (int j=0;j<searchString.length;j++) {
int count = StringUtils.countMatches(pageContent, searchString[j]);
fileWriter.append(String.valueOf(count));
fileWriter.append(COMMA_DELIMITER);
}
fileWriter.append(NEW_LINE_SEPARATOR);
     }
        }
	  catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
}
}
