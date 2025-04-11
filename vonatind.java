import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class vonatind extends vonat {
    protected vonat vo;
    protected int hanyadikmeg;
    protected String egyedivonatszam;
    protected int hely;
	protected static int kocsitenyleg;

    public vonatind(vonat v, String vonatszam, int kocsiszam,String egyediv)
    {
        super(vonatszam, kocsiszam);
        this.vo=v;
        this.hanyadikmeg=1;
        this.egyedivonatszam=egyediv;
        this.hely=0;
		this.kocsitenyleg=1;

    }

    public int getHely()
	{
		return this.hely;
	}

    public String getEgyedi()
    {
        return egyedivonatszam;
    }

    public int getHanyMegallo()
    {
        return hanyadikmeg;
    }

    public vonat getVonat()
    {
        return vo;
    }

    public int helykiad()
	{
		if(kocsiszam>=kocsitenyleg)//egy kocsiba 30 hely van, hely kiadasa elott ellenorizzuk van e a kocsiban hely
		{
			if(hely<30)
			{
				hely++;
				return hely;
			}
			else
			{
				if(kocsiszam>=kocsitenyleg+1)//ha a kocsi foglalt, akkor megnezzuk hogy a kovetkezo kocsi letezik e
				{
					kocsitenyleg++;
                    hely=1;
					return hely;
				}
				else
				{
					return -1;
				}
			}
		}
		else
		{
			return -1;
		}
	}

    public void jegykiadallomastol(vonatind v,String honnan,String hova)
	{
        
		int egyik=(v.getVonat()).Km(honnan);
		int masik=(v.getVonat()).Km(hova);

		System.out.println("\nMennyi jegy lesz?: ");
        String [] splitelt=scan();
        int mennyi=Integer.parseInt(splitelt[0]);//mennyi jegy

		for(int i=0;i<mennyi;i++)
		{
			int x=helykiad();
			if(x!=-1)
			{
				jegy ez=new jegy((v.getVonat()).kocsiszam,(v.getVonat()).vonatszam,honnan,hova,(v.getVonat()).GetVarosIdo(honnan),(v.getVonat()).GetVarosIdo(hova));
				ez.addAr(egyik,masik);//jegyar
						
				System.out.println("\nA jegy:\n\n-------------------------------------------");
				System.out.println("Vonatszám: " + v.getEgyedi() + " Kocsiszám: " + ez.getKocsiszam() + " Helyjegy: " + this.hely );
				System.out.println("Honnan: "+ ez.getMikorind()+ "\nHova: " + ez.getMikorerk());
				System.out.println(ez.getAr() + "Ft");//valtoztatas elfelejtettem beirni beadasnal
				System.out.println("-------------------------------------------");
								
				try
				{
					FileWriter fw=new FileWriter("jegy.txt",true);
            		BufferedWriter writer = new BufferedWriter(fw);
            		writer.write("A jegy:\n\n-------------------------------------------\n");
            		writer.write("Vonatszám: " + v.getEgyedi() + " Kocsiszám: " + ez.getKocsiszam() + " Helyjegy: " + this.hely + "\n");
            		writer.write("Honnan: " + ez.getMikorind() + "\nHova: " + ez.getMikorerk() + "\n");
					writer.write(ez.getAr() + "Ft");//valtoztatas elfelejtettem beleirni beadasnal
           			writer.write("-------------------------------------------\n\n");
            		writer.close();
        		}
				catch (IOException e) 
				{
            		System.out.println("Hiba történt a fájl írása közben: " + e.getMessage());
        		}
			}
			else
			{
				System.out.println("Nincs hely a kocsiban!");
                break;
			}
			
		}

	}

    public void jegykiad(vonatind v)//jegykiadas. amelyik vonatra szeretnenk jegyet venni azt beadjuk a konstruktornak
	{
		System.out.println("\nHonnan hova szeretne menni? (irja be a város nevét!)\n");
		{	
			String [] splitelt=scan();
			
			String honnann=splitelt[0];//indulasi hely
			String hova=splitelt[1];//erkezesi hely

			if((v.getVonat()).VaneilyenV(splitelt[0]) && (v.getVonat()).VaneilyenV(splitelt[1])&&(v.getVonat()).elmentemar(honnann, v.getHanyMegallo()) && (v.getVonat()).Km(splitelt[0])<(v.getVonat()).Km(splitelt[1]))//le ellenorizzuk hogy a varosok vannak e a vonatban, es hogy a km az indulasnal kissebb mint az erekzesnel, tehat jo e az irany
			{
				int egyik=(v.getVonat()).Km(honnann);//indulasi hely km
				int masik=(v.getVonat()).Km(hova);//erkezesi hely km

				System.out.println("\nMennyi jegy lesz?: ");
                splitelt=scan();
                int mennyi=Integer.parseInt(splitelt[0]);//mennyi jegy

				for(int i=0;i<mennyi;i++)
				{
                    int x=helykiad();
					jegy ez=new jegy((v.getVonat()).kocsiszam,v.egyedivonatszam,honnann,hova,(v.getVonat()).GetVarosIdo(honnann),(v.getVonat()).GetVarosIdo(hova));
					ez.addAr(egyik,masik);//jegyar
									
					System.out.println("\nA jegy:\n\n-------------------------------------------");
					System.out.println("Vonatszám: " + v.getEgyedi() + " Kocsiszám: " + ez.getKocsiszam() + " Helyjegy: " + this.hely );
					System.out.println("Honnan: "+ ez.getMikorind()+ "\nHova: " + ez.getMikorerk());
					System.out.println(ez.getAr() + "Ft");//valtoztatas elfelejtettem beirni beadasnal
					System.out.println("-------------------------------------------");
								
					try
					{
						FileWriter fw=new FileWriter("jegy.txt",true);
            			BufferedWriter writer = new BufferedWriter(fw);
            			writer.write("A jegy:\n\n-------------------------------------------\n");
            			writer.write("Vonatszám: " + v.getEgyedi() + " Kocsiszám: " + ez.getKocsiszam() + " Helyjegy: " + this.hely + "\n");
            			writer.write("Honnan: " + ez.getMikorind() + "\nHova: " + ez.getMikorerk() + "\n");
						writer.write(ez.getAr() + "Ft");//valtoztatas elfelejtettem beleirni beadasnal
           				writer.write("-------------------------------------------\n\n");
            			writer.close();
        			}
					catch (IOException e) 
					{
            			System.out.println("Hiba történt a fájl írása közben: " + e.getMessage());
        			}
				}
			}
			else
			{
				System.out.println("Nincs ilyen vonat");
				
			}
		}					
	}
	

    public void megalload(int hanyat)
    {
        if(vo.hanymegallo()+1<=hanyat+hanyadikmeg)
        {
            System.out.println("Nincs elég megallo, nem lehet hozzadni");
        }
        else
        {
            this.hanyadikmeg+=hanyat;
        }
    }


}



