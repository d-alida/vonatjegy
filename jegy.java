import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class jegy extends vonat {
	
	private int ar;
	private String honnan;
	private String hova;
	private String mikorind;
	private String mikorerk;
	protected static int helyszam=0;
	protected static int kocsitenyleg=1;


	public jegy(int kocs, String vo,String ho,String hov, String mi, String me)
	{
		super(vo,kocs);
        this.honnan=ho;
        this.hova=hov;
		this.mikorind=mi;
		this.mikorerk=me;
		
		if(kocsiszam>=kocsitenyleg)//egy kocsiba 30 hely van, hely kiadasa elott ellenorizzuk van e a kocsiban hely
		{
			if(helyszam<30)
			{
				helyszam++;
			}
			else
			{
				if(kocsiszam>=kocsitenyleg+1)//ha a kocsi foglalt, akkor megnezzuk hogy a kovetkezo kocsi letezik e
				{
					kocsitenyleg++;
                    helyszam=1;
				}
				else
				{
					System.out.println("Nincs több hely!");
				}
			}
		}
		else
		{
			System.out.println("Nincs több hely!");
		}
        
	}

	
	public String getMikorind()
	{
		return mikorind;
	}

	public String getMikorerk()
	{
		return mikorerk;
	}

	public String getHonnan()
	{
		return honnan;
	}
	public String getHova()
	{
		return hova;
	}
	public int getAr()
	{
        return ar;
    }

	public String getHely()
	{
		return String.valueOf(helyszam);
	}
	

	public int addAr(int km1,int km2)
	{
		int arr=500+(km2-km1)*10;
		this.ar=arr;
		return ar;
	}

	
}
