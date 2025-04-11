import java.util.TreeMap;

public class menetrend {
	private TreeMap<String, TreeMap<String, String>> menet;
	private TreeMap<String,String> menet2;
	
	public menetrend()
	{
		this.menet=new TreeMap<>();
		this.menet2=new TreeMap<>();
	}
	
	public void addMegálló(String város, String idő, String km)
	{	
		menet2=new TreeMap<>();
		this.menet2.put(idő, km);//egyik treemap adatai
		
		this.menet.put(város,menet2);//masik treemap adatai
		
		
	}

	public String getmegalloVaros(int hanyadik)
	{
		String ezlesz="";
		int szamlalo=1;
		for(String vár:menet.keySet())
		{
			if(szamlalo==hanyadik)
            {
                ezlesz=vár;
            }
            szamlalo++;
		}

		return ezlesz;
	}

	public TreeMap<String,TreeMap<String,String>> getMenet()
	{
		return menet;
	}

	public int hanymegallo()
	{
		return menet.size();
	}

	public void getMenetrend()
	{
		for(String vár:menet.keySet())
		{
			menet2=menet.get(vár);			
			for(String idő:menet2.keySet())
			{
				System.out.println(vár + ": " + idő + " " + this.menet2.get(idő) + " km" );
			}
		}
	}
	

	public void maradekMenetrend(String varos)
	{
		int valt=0;
		
		for(String vár:menet.keySet())
		{
			if(vár.equals(varos))
			{
				valt=1;
			}
			if(valt!=0)
			{
				menet2=menet.get(vár);
				for(String idő:menet2.keySet())
				{
					System.out.println(vár + ": " + idő + " " + menet2.get(idő) + " km" );
				}
			}
		}
	} 

	public void getMenetrend(int hanyadik)
	{
		String var=getmegalloVaros(hanyadik);
		for(String vár:menet.keySet())
		{
			menet2=menet.get(vár);			
			for(String idő:menet2.keySet())
			{
				if(vár.equals(var))
				{
					System.out.println(vár + ": " + idő + " " + this.menet2.get(idő) + " km" + " *" );
				}
				else
				{
					System.out.println(vár + ": " + idő + " " + this.menet2.get(idő) + " km" );
				}
			}
		}
	}

	public boolean elmentemar(String varos,int hanyadik)
	{
		int valt=0;
        for(String vár:menet.keySet())
        {
            if(vár.equals(getmegalloVaros(hanyadik)))
            {
                valt=1;
            }
            if(valt!=0 && vár.equals(varos))
            {
				System.out.println("dsdsxd");
				return true;
            }
        }
        return false;
	}


	
	public String ElsoMegallo()
	{
		String elsosor=menet.firstKey();
		return elsosor;
		
	}

	public String ElsoMegalloIdo()
	{
		menet2=menet.get(ElsoMegallo());
		return menet2.firstKey();
		
	}



	public String UtsoMegallo()
	{
		String elsosor=menet.lastKey();
		return elsosor;
		
	}
	
	public String UtolsoMegalloIdo()
	{
		menet2=menet.get(UtsoMegallo());
		return menet2.lastKey();
		
	}

	public boolean VaneilyenV(String varos)
	{
		if(menet.containsKey(varos))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String GetVarosKm(String varos)
	{
		if(menet.containsKey(varos))
		{
			return varos + " " + Km(varos);
		}
		else
		{
			return "Nincs ilyen!";
		}
	}

	public String GetVarosIdo(String varos)
	{
		if(menet.containsKey(varos))
		{
			return varos + " " + Ido(varos);
		}
		else
		{
			return "Nincs ilyen!";
		}
	}



	public int Km(String varos)
	{
		TreeMap<String,String> ideiglenes=menet.get(varos);
		int km=0;
		for(String idő:ideiglenes.keySet())
		{
			km=Integer.parseInt(ideiglenes.get(idő));
		}

		return km;
	}

	public String Ido(String varos)
	{
		TreeMap<String,String> ideiglenes=menet.get(varos);
        String ido="";
        for(String idő:ideiglenes.keySet())
        {
            ido=idő;
        }

		return ido;
	}

}
