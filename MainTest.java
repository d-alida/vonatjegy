import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MainTest {

   
    @Test
    public void testUtolsoMegallo() {
        menetrend menet = new menetrend();
        menet.addMegálló("Budapest", "10:00", "0");
        menet.addMegálló("Nagykanizsa", "11:00", "100");
        menet.addMegálló("Szolnok", "12:00", "200");
        assertEquals("Szolnok", menet.UtsoMegallo());//utolso megallo helyes e
    }

    @Test
    public void testElsoMegallo() {
        menetrend menet = new menetrend();
        menet.addMegálló("Budapest", "10:00", "0");
        menet.addMegálló("Szolnok", "12:00", "200");
        assertEquals("Budapest", menet.ElsoMegallo());//elsojoe
    }

    @Test
    public void vonatleptetes() {
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        ez.megalload(2);
        assertEquals("Szolnok",(ez.getVonat()).getmegalloVaros(ez.getHanyMegallo()));//jo e a lepesek szama
        

    }

    @Test
    public void vonatleptetesNemJo() {
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        ez.megalload(5);
        assertEquals(1,ez.getHanyMegallo());//jo e a lepesek szama, mivel otot akarunk lepni, es nics annyi megallo ezert nem lepunk.

    }


    @Test
    public void megallobavonat() {
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        String honnan="Nagykanizsa";
        String hova="valami";
        
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        ez.megalload(1);
        assertTrue((ez.getVonat()).elmentemar("Nagykanizsa", ez.getHanyMegallo()));//elmentemar, true mert forditva van, akkor true ha van varos
        //alul nezzuk a jegykiadas parameteret, elsonel false, mert nem jo a megallo nev, masodiknal true mert stimmel
        assertFalse((ez.getVonat()).VaneilyenV(honnan) && (ez.getVonat()).VaneilyenV(hova)&&(ez.getVonat()).elmentemar(honnan, ez.getHanyMegallo()) && (ez.getVonat()).Km(honnan)<(ez.getVonat()).Km(hova));
        hova="Szolnok";
        assertTrue((ez.getVonat()).VaneilyenV(honnan) && (ez.getVonat()).VaneilyenV(hova)&&(ez.getVonat()).elmentemar(honnan, ez.getHanyMegallo()) && (ez.getVonat()).Km(honnan)<(ez.getVonat()).Km(hova));
    
    }

    @Test
    public void elmentemaravonat() {
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        ez.megalload(2);
        assertFalse(ez.elmentemar("Nagykanizsa", ez.getHanyMegallo()));//False kell, elment a vonat (forditva van a true false)
    }

    @Test
    public void allomasos() {
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");

        vonat x = new vonat("456", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("xde", "11:00", "100");
        v.addMegálló("sdads", "12:00", "200");
        vonatind az = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        assertTrue(((az.getVonat()).getMenet()).containsKey("Budapest") && ((ez.getVonat()).getMenet()).containsKey("Budapest"));//Atmegy e az allomason

        
    }

    @Test
    public void utsohely() {//tesztelem itt meg a jegykaidas feltetelt is
        vonat v = new vonat("1234", 5); 
        v.addMegálló("Budapest", "10:00", "0");
        v.addMegálló("Nagykanizsa", "11:00", "100");
        v.addMegálló("Szolnok", "12:00", "200");
        vonatind ez = new vonatind(v, v.getVonatszam(), v.getKocsiszam(), "elso");
        ez.megalload(2);//itt leszunk a vegallomason
        ez.megalload(1);
        assertEquals(3, ez.getHanyMegallo());//vegallomason vagyunk, nem tudunk egyet hozzaadni
        }

    @Test
    public void testJegyHelyjegy() {
        jegy j = new jegy(2, "ABC", "Budapest", "Szolnok", "14:00", "12:00");
        assertEquals("2", j.getHely());//rossz volt multkor azert kell ez most, azert ketto mert ket jegyet hozok 
    }

    @Test
    public void testAddAr() {
        jegy j = new jegy(123, "ABC", "Budapest", "Szolnok", "13:00", "15:00");
        j.addAr(100, 200);
        assertEquals(1500, j.getAr());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MainTest.class);
        int hibak_sz = 0;
        if (!result.wasSuccessful()) {
            System.out.println("Hibás teszt:");
            for (Failure failure : result.getFailures()) {
                System.out.println("Hibát találtam:\n" + failure.toString());
                hibak_sz++;
            }
        } else {
            System.out.println("Minden teszt sikeres.");
        }
        System.out.println("Hibák száma: " + hibak_sz);
    }
}
