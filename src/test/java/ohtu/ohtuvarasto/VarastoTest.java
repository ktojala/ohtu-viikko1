package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUpNega() {
        varasto = new Varasto(-11);
    }

    @Test
    public void konstruktoriLoiNegaVaraston() {
// testi läpäisee arvolla 0 (muutos tehtävää 10 varten arvo 1 että ei mene läpi)
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoaNegalla() {
        varasto.lisaaVarastoon(-8);

        // saldo ei muutu negalisäyksellä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenNegaa() {
        varasto.otaVarastosta(-2);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(10);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tulostaaVaraston() {
        String vastaus = varasto.toString();
        assertEquals("saldo = 0.0, vielä tilaa 10.0", vastaus);   
    }

// testataan eri tavoin kuormitettua konstruktoria

    Varasto varasto2;

    @Before
    public void setUpUnempty1() {
        varasto2 = new Varasto(10,6);
    }

    @Test
    public void konstruktori2OikeaSaldo() {
        assertEquals(6, varasto2.getSaldo(), vertailuTarkkuus);
    }

    Varasto varasto3; 

    @Before
    public void setUpNegaSaldo() {
        varasto3 = new Varasto(10,-1);
    } 

    @Test
    public void negaSaldo() {
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }

    Varasto varasto4;

    @Test
    public void konstruktoriNegaTilavuus() {
        varasto4 = new Varasto(-10,0);
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
    }

    Varasto varasto5;

    @Test
    public void lisaaSaldoaYliTilavuuden() {
        varasto5 = new Varasto(2,0);
        varasto5.lisaaVarastoon(4);
        assertEquals(2, varasto5.getSaldo(), vertailuTarkkuus);
    }

}