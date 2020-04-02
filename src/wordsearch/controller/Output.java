package wordsearch.controller;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Output {
	
	public static void saveText(WordSearchController app, String path, String textToSave)
	{
		String filename = path;
		LocalDateTime date = LocalDateTime.now();
		
		// builds the file name that will be saved
		filename += "/"+date.getMonth()+" " + date.getDayOfMonth()+" ";
		filename += date.getHour() + "-" + date.getMinute();
		File saveFile = new File(filename);
		
		try(Scanner textScanner = new Scanner(textToSave); PrintWriter saveText = new PrintWriter(saveFile))
		{
			while(textScanner.hasNext())
			{
				String currentLine = textScanner.nextLine();
				saveText.println(currentLine);
			}
		}catch (IOException error)
		{
			app.handleErrors(error);
		}catch(Exception genericError)
		{
			app.handleErrors(genericError);
		}
	}

}
