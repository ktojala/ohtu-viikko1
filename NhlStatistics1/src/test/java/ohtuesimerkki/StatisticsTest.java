
package ohtuesimerkki;

import ohtuesimerkki.Reader;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void konstruktoriJaStatsSearch(){
        Player p = stats.search("Kurri");
        assertEquals(p.getName(),"Kurri");
    }

    @Test
    public void searchNobody(){
        assertNull(stats.search("Matti"));
    } 

    
    @Test
    public void noTeam(){
        List<Player> list = stats.team("Jokerit");
        assertEquals(0, list.size());
    }

    @Test
    public void whichTeam(){
        List<Player> list = stats.team("PIT");
        Player p2 = list.get(0);
        assertEquals("Lemieux", p2.getName());
    }
    
    @Test
    public void topScoreTest(){
        List<Player> list = stats.topScorers(1);
        Player p = list.get(0);
        assertEquals("Gretzky", p.getName());
    }  

}