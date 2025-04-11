import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class vonat extends menetrend {

	protected String vonatszam;
	protected int kocsiszam;
	protected TreeMap<String, TreeMap<String,String>> menetrend;
	


	public vonat(String vsz, int ksz)
	{
		this.vonatszam=vsz;
		this.kocsiszam=ksz;
		this.menetrend=new TreeMap<String, TreeMap<String,String>>();
		
	}

	


	public String getVonatszam()
	{
        return this.vonatszam;
    }

	public int getKocsiszam()
	{
        return this.kocsiszam;
    }

	public void addMenetrend(String város, String idő, String km)
	{
        super.addMegálló(város, idő, km);
	}
	
	public void addMenetrend(TreeMap<String, TreeMap<String,String>> menet)
	{
        this.menetrend=menet;
	}

	public int mennyiMegallo()
	{
		return this.menetrend.size();
	}

	

	
	public static String [] scan()
	{	
		Scanner scanner = new Scanner(System.in);
		String beolvasott=scanner.nextLine();
		String [] splitelt=beolvasott.split(" ");
		return splitelt;
	}

	public void fajlbairas(String splitelt)
	{
		String fajlnev = splitelt;		
		try 
		{
			FileWriter fr = new FileWriter(fajlnev);
			BufferedWriter br = new BufferedWriter(fr);
			
			br.write(vonatszam + " " + kocsiszam);
			
			TreeMap <String, TreeMap<String, String>> menet=this.getMenet();

			for(String varos:menet.keySet())
			{
				TreeMap<String, String> menet2=menet.get(varos);
				for(String ido:menet2.keySet())
				{
					br.write("\n"+varos + " " + ido + " " + menet2.get(ido));
				}
			}

			br.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Hiba történt a fájlba torteni iras közben: " + e.getMessage());
		}
			
	}

	public vonat fajlbololvas(String [] splitelt)//ezt vegul nem hasznaltam
	{
		String fajlnev = splitelt[0];		
		try 
		{
			FileReader fr = new FileReader(fajlnev);
			BufferedReader br = new BufferedReader(fr);
			String sor;

			sor=br.readLine();
			while (sor != null)
			{
				splitelt = sor.split(" ");
					
				String vonatszam = splitelt[0];
				int kocsiszam = Integer.parseInt(splitelt[1]);
					
				vonat valami = new vonat(vonatszam, kocsiszam);
				
				sor=br.readLine();
				while (sor != null)
				{
					splitelt = sor.split(" ");
					String varos = splitelt[0];
					String ido = splitelt[1];
					String km = splitelt[2];
					valami.addMegálló(varos, ido, km);
				}
				
				return valami;
			}

			br.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Hiba történt a fájlból történő olvasás közben: " + e.getMessage());
			}
			return null;
	}
}
