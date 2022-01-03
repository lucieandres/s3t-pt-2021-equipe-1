package tests;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import bot.Bot;
import joueur.Joueur;
import moteur.Data;

class TestsBot {

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void test() {
		
		//test constructeur
		
		Bot bobby = new Bot("Facile", Color.BLUE, "Bobby");
		assertEquals(bobby.getCouleur(), Color.BLUE);
		assertEquals(bobby.getPseudo(), "Bobby");
		
		Bot jessica = new Bot("Moyen", Color.WHITE, "Jessica");
		assertEquals(jessica.getCouleur(), Color.WHITE);
		assertEquals(jessica.getPseudo(), "Jessica");
		
		Bot oka = new Bot("Difficile", Color.RED, "Oka");
		assertEquals(jessica.getCouleur(), Color.RED);
		assertEquals(jessica.getPseudo(), "Oka");
		
		//test get et set de couleur
		
		bobby.setCouleur(Color.WHITE);
		assertEquals(bobby.getCouleur(), Color.WHITE);

		//test get et set de pseudo
		
		bobby.setPseudo("bobby");
		assertEquals(bobby.getPseudo(), "bobby");	
		
		//test get et set de difficulte
		
		bobby.setDifficulte("Facile");
		assertEquals(bobby.getDifficulte(), "Facile");
		
		//test methodes
		
		bobby.initMain();
		jessica.initReserve();
		jessica.initMain();
		
		//test main
		
		CarteInfluence[] main = new CarteInfluence[3];
		main = bobby.getMain();
		bobby.setMain(main);
		assertEquals(bobby.getMain(), main);
		
		//test reserve
		
		CarteInfluence[] reserve = new CarteInfluence[25];
		reserve = bobby.getReserve();
		bobby.setReserve(reserve);
		assertEquals(bobby.getReserve(), reserve);
		
		//test defausse

		CarteInfluence[] defausse = new CarteInfluence[25];
		defausse = bobby.getDefausse();
		bobby.setDefausse(defausse);
		assertEquals(bobby.getDefausse(), defausse);
		
		//test jouer
		
		try {
			Data data = new Data (3);
			bobby.jouer(data, 0, 0);
			jessica.jouer(data, 0, 0);
			oka.jouer(data, 0, 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		//test cartes objectifs
				
		ArrayList<CarteObjectif> objectifs = new ArrayList<CarteObjectif>(); 
		bobby.setObjectif(objectifs);
		assertEquals(bobby.getObjectif(), objectifs);
		CarteObjectif objectif = new CarteObjectif("Alchimie", 3);
		bobby.addCarteObjectif(objectif);
		assertEquals(bobby.getObjectif().get(0), objectif);
				
		//test score
				
		assertEquals(bobby.getScore(), 3);	
		assertEquals(jessica.getScore(),2);
		assertEquals(oka.getScore(),5);		
			
	}

}
