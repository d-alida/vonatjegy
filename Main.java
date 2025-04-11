import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
public static ArrayList<vonat> vonatok=new ArrayList<>();
public static ArrayList<vonatind> elindultak=new ArrayList<>();
	public static void main(String[] args) {
		
		while (true)
		{
			System.out.println("\nVonatrendszer:\n\njegyvásárlás\nmenetrend\nvonatfelvétel\nVonatlista\nFájlbairas\nVonatinditas\nVonatleptetes\n");
			String [] splitelt=scan();

			if(splitelt[0].equals("exit")||splitelt[0].equals(""))
			{
				break;
			}

			if(splitelt[0].equals("vonatleptetes"))
			{
				System.out.println("Melyik vonatot szeretne leptetni(irja be az egyedi azonositot)?");
				splitelt=scan();

				for(vonatind v:elindultak)
                {
                    if(splitelt[0].equals(v.getEgyedi()))
                    {
                        System.out.println("Mennyit szeretne leptetni? Jelenleg itt tart: " + (v.getVonat()).getmegalloVaros(v.getHanyMegallo()) );
						splitelt=scan();

						v.megalload(Integer.parseInt(splitelt[0]));
						System.out.println(v.getEgyedi() + " vonat " + v.getHanyMegallo() + " leptetve.\nMost itt tart: " + (v.getVonat()).getmegalloVaros(v.getHanyMegallo()));

                    }
                }
			}

			if(splitelt[0].equals("vonatinditas"))
			{
				int szamlalo=0;
				System.out.println("Itt egy vonatot tud inditani.");
				System.out.println("Melyik vonatot szeretne elinditani? (irja be a vonat szamat)");
				splitelt=scan();
				String vonatsz=splitelt[0];
				for(vonat v:vonatok)
                {
                    if(splitelt[0].equals(v.vonatszam))
                    {
                        szamlalo++;
                    }
                }
				if(szamlalo>1)//megnezzuk h van e vonat egy szammal tobbszor
				{
                    System.out.println("Tobb vonat is letezik ezzel a szammal. Irja be az indulasi allomast es az idejet meg, emellet irjon be egy egyedi azonositot a vonathoz");
                    splitelt=scan();
					String egyedi=splitelt[2];

					if(letezikeaszam(egyedi))
					{
						System.out.println("Ez az azonosito mar letezik, ujat kell megadnia");
					}
					else
					{
						for(vonat v:vonatok)
                    	{
                        	if(splitelt[0].equals(v.ElsoMegallo()) && splitelt[1].equals(v.ElsoMegalloIdo()) && vonatsz.equals(v.vonatszam))
                        	{
								
                            	vonatind ujv=new vonatind(v,v.getVonatszam(),v.getKocsiszam(),splitelt[2]);
								elindultak.add(ujv);
                        	}
                    	}
					}
				}
				else if(szamlalo==1)
				{
					System.out.println("Kerek meg egy egyedi azonositot is a vonathoz");
					splitelt=scan();
					String egyedi =splitelt[0];
					for(vonat v:vonatok)
                    {
                        if(letezikeaszam(egyedi))
						{
							System.out.println("Ez az azonosito mar letezik, ujat kell megadnia");
						}
						else
						{
							if(vonatsz.equals(v.vonatszam))
							{
								vonatind ujv=new vonatind(v,v.getVonatszam(),v.getKocsiszam(),egyedi);
								elindultak.add(ujv);
							}
						}
                    }
				}
			}

			if(splitelt[0].equals("fajlbairas"))
			{
				System.out.println("Itt egy vonatot tud fajlba irni.");
				

				for(vonat v:vonatok)
				{
					System.out.println(v.vonatszam + " " +v.ElsoMegallo() + " "+v.ElsoMegalloIdo() +" " + v.UtsoMegallo() + " " + v.UtolsoMegalloIdo());
				}

				System.out.println("Melyik vonatot szeretne fajlba irni? (irja be a vonat szamat)");
				splitelt=scan();

				int szamlalo=0;

				for(vonat v:vonatok)
				{
					if(splitelt[0].equals(v.vonatszam))
					{
						szamlalo++;
					}
				}

				if(szamlalo>1)//megnezzuk h van e vonat egy szammal tobbszor
                {
					String vonatsz=splitelt[0];
					System.out.println("Tobb vonat is letezik ezzel a szammal. Irja be az indulasi allomast es az idejet meg");
					splitelt=scan();

					for(vonat v:vonatok)
					{
						if(splitelt[0].equals(v.ElsoMegallo()) && splitelt[1].equals(v.ElsoMegalloIdo()) && vonatsz.equals(v.vonatszam))
						{
							System.out.println("Mi legyen a fajl neve?");
							splitelt=scan();
							String fajlnev=splitelt[0];
							v.fajlbairas(fajlnev);
						}
					}
				}
				else if(szamlalo==1)
				{
					String vonatsz=splitelt[0];
					System.out.println("Mi legyen a fajl neve?");
					splitelt=scan();
					String fajlnev=splitelt[0];
					for(vonat v:vonatok)
					{
						if(vonatsz.equals(v.vonatszam))
						{
							v.fajlbairas(fajlnev);
						}
					}
				}
				else
				{
					System.out.println("Nincs ilyen vonat");
				}
			}

			if (splitelt[0].equals("vonatlista"))
			{
				System.out.println("A vonatokat szeretne listazni a menetrendjukkel(vl),vagy csak azokat amik elindultak?(el)");
				splitelt=scan();
				if(splitelt[0].equals("vl"))
				{
					lista();
				}
				else if(splitelt[0].equals("el"))
				{
					elindultlista();
				}
				else
				{
					System.out.println("Nincs ilyen szuro");
				}
				
			}

			if(splitelt[0].equals("jegyvasarlas"))
			{
				System.out.println("Jegyet egy konkret vonatra szeretne venni (kv) vagy egy allomastol?(a)");
				splitelt=scan();
				if(splitelt[0].equals("kv"))
				{
					System.out.println("Melyik vonatra szeretne jegyet venni? (irja be a vonatszámot!)\n");
					for(vonatind v:elindultak)//adott vonat megnezese
					{
						System.out.println(v.getEgyedi() + " " + (v.getVonat()).ElsoMegallo() + " " + (v.getVonat()).UtsoMegallo() + "\nJelenleg itt van a vonat: " +(v.getVonat()).getmegalloVaros(v.getHanyMegallo()));
               		}
					splitelt=scan();
					String egyedi=splitelt[0];
					for(vonatind v:elindultak)//adott vonat megnezese
					{
						if(v.getEgyedi().equals(splitelt[0]))
						{
							egyvonatlista(v);
							
						}
               		}


					int szamlalo=0;
					
					for(vonatind v:elindultak)//adott vonat megnezese
					{
						if(szamlalo==0)
						{
							if ((v.getEgyedi()).equals(egyedi))
							{

								v.jegykiad(v);
								szamlalo++;
							}
						}
					}
					if(szamlalo<=0)
					{
						System.out.println("Nincs ilyen vonat vagy a vonat mar elment");
					}
					splitelt=scan();
				}
				else if(splitelt[0].equals("a"))
				{
					System.out.println("Melyik allomastol szeretne jegyet venni? (irja be az allomas nevet)");
					for(vonatind v:elindultak)
					{
						System.out.println("\n");
						System.out.println(v.getEgyedi() + " " + (v.getVonat()).ElsoMegallo() + " " + (v.getVonat()).UtsoMegallo() + "\nJelenleg itt van a vonat: " +(v.getVonat()).getmegalloVaros(v.getHanyMegallo()));
					}

					splitelt=scan();
					String honnan=splitelt[0];

					for(vonatind v:elindultak)
					{
						
						if((v.getVonat()).VaneilyenV(honnan) && (v.getVonat()).elmentemar(honnan, v.getHanyMegallo()))
						{
							System.out.println("\n");
							System.out.println(v.getEgyedi());
							(v.getVonat()).maradekMenetrend(honnan);
						}
					}

					System.out.println("Melyik allomasig venne jegyet? Irja be az allomast es az egyedi azonositot");
					splitelt=scan();
					String hova=splitelt[0];
					String egyedi=splitelt[1];

					for(vonatind v:elindultak)
                    {
						
                        if((v.getEgyedi()).equals(egyedi)&&(v.getVonat()).VaneilyenV(hova)&&(v.getVonat()).VaneilyenV(honnan)&&(v.getVonat()).elmentemar(honnan, v.getHanyMegallo()) &&(v.getVonat()).Km(honnan)<(v.getVonat()).Km(hova))
                        {
                            System.out.println("\n");
							v.jegykiadallomastol(v, honnan, hova);
                            
                        }
                    }
				}
			}

			if(splitelt[0].equals("menetrend"))
			{
				while(true)
				{
					System.out.println("\nVonatszám alapján szűrés(vsz)\nIndulás-érkezés alapján szűrés(iv)\nAllomás szerinti szűrés(asz)");
					splitelt=scan();

					if(splitelt[0].equals("exit")||splitelt[0].equals(""))
					{	
						break;
					}
					else if(splitelt[0].equals("asz"))
					{
						System.out.println("\nKérem az állomás/varos nevét: ");
						splitelt=scan();
						int szamlalo=0;//error miatt

						for(vonatind v:elindultak)
						{
							if((v.getVonat()).VaneilyenV(splitelt[0]))
							{
								szamlalo++;
								
								System.out.println(v.vonatszam);
								(v.getVonat()).getMenetrend(v.getHanyMegallo());

								System.out.println("\n");
							}
						}
						if(szamlalo==0)
						{
							System.out.println("Nincs ilyen állomás");
						}
					}
					else if(splitelt[0].equals("vsz"))
					{
						System.out.println("\nKérem a vonatszámot: ");
						splitelt=scan();
						int szamlalo=0;//error miatt
						int hanyadikv=1;
						for(vonatind v:elindultak)
						{
							if(v.getEgyedi().equals(splitelt[0]))
                       		{
								szamlalo++;//ha nincs ilyen vonatszam akkor szamlalo 0
								System.out.println(hanyadikv + ".");
                            	(v.getVonat()).getMenetrend(v.getHanyMegallo());
								System.out.println("\n");
								hanyadikv++;
                        	}
						}
						if (szamlalo<=0)//hibas volt, == volt, tobb ugyanolyan vonatszamnal nem jo ugye
						{
							System.out.println("Nincs ilyen vonat");
						}
					}
					else if(splitelt[0].equals("iv"))
					{
						System.out.println("Kérem az indulást, majd az érkezést, spacel elválasztva: ");
						splitelt=scan();
						String ind=splitelt[0];//indulasi hely
						String erk=splitelt[1];//erkezesi hely
					
						int szamlalo=0;//error miatt
						int hanyadikv=1;
						for(vonatind v:elindultak)//adott vonat megnezese
						{
							if((v.getVonat()).VaneilyenV(ind) && (v.getVonat()).VaneilyenV(erk) && (v.getVonat()).Km(erk)>(v.getVonat()).Km(ind))
                        	{
                            	szamlalo++;//ha nincs ilyen vonatszam akkor szamlalo 0
								System.out.println(hanyadikv + ".");
                            	(v.getVonat()).getMenetrend(v.getHanyMegallo());
								System.out.println("\n");
								hanyadikv++;
                        	}
						
						}
						if (szamlalo==0)
						{
							System.out.println("Nincs ilyen vonat");
						}
					}
					else
					{
						System.out.println("Hibás bemenet");
					}
				
				}
			}
			
			if(splitelt[0].equals("vonatfelvetel"))
			{
				while(true)
				{
					System.out.println("\nManuális vonatfelvétel(mv)\nFájlból olvasás vonatfelvétel(fov)");
					splitelt=scan();
					
					if(splitelt[0].equals("exit")||splitelt[0].equals(""))
					{	
						break;
					}

					if(splitelt[0].equals("mv"))
					{	
						System.out.println("Kérem a vonatszámot,illetve a kocsiszámot:  ");
						splitelt=scan();
						vonat valami = new vonat(splitelt[0],Integer.parseInt(splitelt[1]));

						System.out.println("Kérem az indulást, majd az érkezést, spacel elválasztva: (Ha pedig végzett, akkor exit-tel lépjen ki)");
						while (true)
						{
							splitelt=scan();
							if(splitelt[0].equals("exit")||splitelt[0].equals(""))
							{	
								break;
							}
							else
							{	
								valami.addMegálló(splitelt[0], splitelt[1],splitelt[2]);
							}
						}

						vonatok.add(valami);
					}
					if (splitelt[0].equals("fov")) 
					{
						System.out.println("Kérem a fájl nevét: ");
						splitelt = scan();
						String fileName = splitelt[0];
					
						try 
						{
							FileReader fr = new FileReader(fileName);
							BufferedReader br = new BufferedReader(fr);
							String sor;

							while ((sor = br.readLine()) != null)
							{
								splitelt = sor.split(" ");
					
								String vonatszam = splitelt[0];
								int kocsiszam = Integer.parseInt(splitelt[1]);
					
								vonat valami = new vonat(vonatszam, kocsiszam);
					
								while ((sor = br.readLine()) != null && !sor.isEmpty())
								{
									splitelt = sor.split(" ");
									String varos = splitelt[0];
									String ido = splitelt[1];
									String km = splitelt[2];
									valami.addMegálló(varos, ido, km);
								}
					
								vonatok.add(valami);//emiatt nem tudtam ezt fuggvenybe irni
							}
							br.close();
						}
						catch (Exception e)
						{
							System.out.println("Hiba történt a fájlból történő olvasás közben: " + e.getMessage());
						}
					}
				}
			}
		}
	}
	
	public static String [] scan()
	{	
		Scanner scanner = new Scanner(System.in);
		String beolvasott=scanner.nextLine();
		String [] splitelt=beolvasott.split(" ");
		return splitelt;
	}
	
	public static void sorok()
	{	
		System.out.println("\n\n\n\n\n");
	}

	public static void lista()
	{
		if(vonatok.size()==0)
		{
			System.out.println("Nincsenek vonatok");
		}
		else
		{
			System.out.println("Vonatok:");
			while(true)
			{
				for(vonat v:vonatok)
            	{
					System.out.println("Vonatszám: " + v.vonatszam + "\n\nElső megálló: " + v.ElsoMegallo() + "\nUtolsó megálló: " + v.UtsoMegallo() + "\n");
					v.getMenetrend();
					System.out.println("----------------------------------------------------------------");       
            	}
				String [] splitelt=scan();
				if(splitelt[0].equals("exit")||splitelt[0].equals(""))
				{
					break;
				}
			}
		}
	}

	public static boolean letezikeaszam(String splitelt)
	{
		for(vonatind v:elindultak)
		{
			if(v.getEgyedi().equals(splitelt))
			{
				return true;
			}
		}
		return false;

	}

	public static void egyvonatlista(vonatind v)
	{ 
		System.out.println("\nEgyedi azonosito: "+ v.getEgyedi()+"\n\nElső megálló: " + (v.getVonat()).ElsoMegallo() + "\nUtolsó megálló: " + (v.getVonat()).UtsoMegallo() + "\n");
		(v.getVonat()).getMenetrend(v.getHanyMegallo());
		System.out.println("----------------------------------------------------------------");       
	}

	public static void elindultlista()
	{
		if(elindultak.size()==0)
		{
			System.out.println("Nincsenek vonatok");
		}
		else
		{
			System.out.println("Vonatok:");
			while(true)
			{
				
				for(vonatind v:elindultak)
            	{
					System.out.println("Vonatszám: " +(v.getVonat()).vonatszam+ "\nEgyedi azonosito: "+ v.getEgyedi()+"\n\nElső megálló: " + (v.getVonat()).ElsoMegallo() + "\nUtolsó megálló: " + (v.getVonat()).UtsoMegallo() + "\n");
					(v.getVonat()).getMenetrend(v.getHanyMegallo());
					System.out.println("----------------------------------------------------------------");       
            	}

				String [] splitelt=scan();
				if(splitelt[0].equals("exit")||splitelt[0].equals(""))
				{
					break;
				}
			}
		}
	}

}
