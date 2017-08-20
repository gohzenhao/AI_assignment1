import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 
public class ReadFileFromCMD {

	public static void main(String[] args) throws IOException
	{
		File environmentFile = new File(args[0]+".txt");
		//File queryFile = new File(args[1]);
		File outputFile = new File(args[1]+".txt");
		
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			if(environmentFile.exists())
			{
				fr = new FileReader(environmentFile);
				br = new BufferedReader(fr);
				String line = br.readLine();
				try
				{
					fw = new FileWriter(outputFile);
					bw = new BufferedWriter(fw);
					while(line!=null)
					{
						bw.write(line);
						bw.newLine();
						line = br.readLine();
					}
					bw.flush();
					bw.close();
				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
				finally
				{
					try// if the file was opened, close it
					{
					if (fw != null)
					fw.close();
					}
					catch (IOException ioe)
					{
						System.out.println(ioe.getMessage());
					}

				}

			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try// if the file was opened, close it
			{
				if (fr != null)
				fr.close();
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}

		}

	}
}
