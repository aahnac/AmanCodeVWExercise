package main.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Question1 
{
	//Question1: Part a) Method to verify whether file exists in provided path
	static Boolean doesFileExist(String path)
	{
		File tempFile = new File(path); 
		try
		{ 
			if(tempFile.exists()==false)
			{
				throw new FileNotFoundException();
			}
			else 
			{
				System.out.println("The inpiut file exists"); 
				return true;	
			}
		}
	    catch (FileNotFoundException ex) 
		{
    	    System.err.println("The file does not exists");
    	    ex.getStackTrace();
    	    return false;    	  
        }
	}

	public static void main(String[] a) throws InterruptedException
	{
		String path = new File("src/resources/input.txt").getAbsolutePath();
		if(doesFileExist(path))
	    {
			try 
			{
				//Question2 
				File tempFile = new File(path);
				BufferedReader br = new BufferedReader(new FileReader(tempFile));
				for (String line = br.readLine(); line != null; line = br.readLine())
			    {
					String[] line1 = line.split("-");
				    String word = line1[0].trim();
					System.out.println(word);
					String[] temp = line1[1].split(",");
					for (String j : temp) 
					{
						String meaning = j.trim();
						System.out.println(meaning);
					}
				}
			br.close();
		    }
		    catch (Exception ex)	
		    {
		    	ex.getStackTrace();
		    }
        }
    }
}
