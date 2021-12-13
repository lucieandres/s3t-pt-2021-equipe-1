package bot;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import elements.Colonne;
import cartes.*;
import javafx.scene.paint.Color;
import joueur.Joueur;
import moteur.Data;

public class Bot extends Joueur {
	private Color couleur;
	private String pseudo;
	private String difficulte;
	private CarteInfluence  main[];
	private CarteInfluence  defausse[];
	private CarteInfluence  reserve[];
	private CarteObjectif	objectif[];
	String validDiff[];

	public Bot(String difficulte, Color couleur, String pseudo) {
		super(couleur, pseudo);

		this.validDiff = new String[] {"facile", "moyen", "difficile"};
		this.couleur=couleur;
		this.pseudo=pseudo;
		main = new CarteInfluence[3];
	    defausse = new CarteInfluence[25];
		reserve = new CarteInfluence[25];
		if (!Arrays.asList(this.validDiff).contains(difficulte)) { throw new IllegalArgumentException("Type de difficulte n'existe pas"); }
		this.difficulte = difficulte;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}
	
	@Override
    public void jouer(Data data, int indexMain, int indexColonne) {
		switch (this.difficulte){
			case "facile":
				indexMain = setAleatoireIndexMain();
				indexColonne = setAleatoireIndexColonne(data);
			try {
				data.jouerCarte(indexMain, indexColonne);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			//case "moyen":
				// jouer_moyen(cols.length);
			//break;
			//case "difficile":
				// jouer_difficile(cols.length);
			//break;
		}
	}

	public int setAleatoireIndexMain() {
		return getRandomInt(3);
	}
	
	public int setAleatoireIndexColonne(Data data) {
    	Random rand = new Random();
    	ArrayList<Integer> listIndex = new ArrayList<>();
    	for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {
    		if(!data.getPlateau().getColonnes()[i].estPleine()) {
    			listIndex.add(i);
    		}
    	}
    	if(listIndex.size() == 1) {
    		return(listIndex.get(0));
    	}
    	return listIndex.get(rand.nextInt(listIndex.size()));
	}
	
	
	
	public void jouer_moyen(Data data) {
		
	}
	
	
	
	public double[] pointTotalMax(Data data) {
		double[] bestIndex=null;
		bestIndex[0]=0;
		for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {	
			for(int j=0; j<main.length; j++ ) {
				//attendant methode pointTotal(Colonne c, indexmain, int indexjoueur)
				double pointTotal = data.getTotal(data.getPlateau().getColonne(i), j, data.getCurrentJoueur());
			    if (pointTotal > bestIndex[0] && pasBon(i, j, pointTotal, data)) { //index+colonne...
			    	bestIndex[0]=pointTotal;
			    	
			    }
			}
		}
		return bestIndex;
	}
	//faudra le renommer //se sert à éviter les move pas intelligent rule 5,6
	public Boolean pasBon(int indexColonne, int indexMain,double pointTotal, Data data) {
		for(int i=0; i<data.getJoueurs().length;i++) { 
			if(!(data.getCurrentJoueur()==i)) { //only consider the other players
				double pointTotal2 = data.getTotal(data.getPlateau().getColonne(indexColonne), indexMain , i); //point on the colonne of that player
				CarteInfluence cartesSurColonne[]=data.getPlateau().getColonne(indexColonne).getCartesInfluences(); 
				if(pointTotal2>pointTotal && !(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si le nb de point de l'autre joueur est plus élevé there is no more room to play afterward ( the card before last move is already filled)
					return true;
				}			
			}
		}
		return false;
	}
	//RULES:
	 //1peut caculer le point des autres carte des autres dans la colonne va perdre 
	//2ACTUALLY just need to get the defausse =)))
	//3have to consider next player can be attacking if he has Magicien,... ->what is the percentage??
	//4% of having an assasin hidden, 
	
	//5have to consider the point of opponent on the same colone too, if it's last carte on colone but still losing->NOT play or move to the next colone
	//6or if the opponent have upcoming turn and in his defausse can have a carte that can beat us-> NOT PLAYING by that way
	//7a la fin pouvoir faire FuzzyLogic avec pointTotal, pointAttaque,....
	public double EtreAttaque(Data data) {
		double etreAttaque=0;
		for(int i=0; i<data.getJoueurs().length;i++) { 
			if(!(data.getCurrentJoueur()==i)) {
				//the card that are NOT in the defausse of the player can beat us in the colonne
				CarteInfluence cartesDefausse[]=data.getJoueursAvecIndex(i).getDefausse();
				String typeCartesDefausse[] = null;
				for(int j=0; j<cartesDefausse.length; j++) {
					typeCartesDefausse[j]=cartesDefausse[j].getNom();
				}//How to get the cartes that are not present in the defausse to make a list??
				
				double pointTotal2 = data.getTotal(indexColonne + cartenotindefausse, indexMain , i);
				double etreAttaque= bestIndex-pointTotal2
				
			}
	}
		return etreAttaque;
	}
	
	public double pointAttaquer(Data data) {
		
		return 0;
	}
	
	
	
	
	
				
				
				
				
				
	public void jouer_difficile(Colonne[] cols) {
	}
	
	
	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}


//	public void getClassesCarteInfluence() {
//		 final Class<?> myClazz = this.getClass();
//	     final String myPkg = myClazz.getPackage().getName();
//
//	        final ConfigurationBuilder config = new ConfigurationBuilder()
//	            .setScanners(new ResourcesScanner(), new SubTypesScanner(false))
//	            .setUrls(ClasspathHelper.forPackage(myPkg))
//	            .filterInputsBy(new FilterBuilder().includePackage(myClazz.getPackageName()));
//
//	        final Reflections reflect = new Reflections(config);
//	        
//	        final Collection<Class<?>> scanned = reflect.getSubTypesOf(Object.class);
//	}      
//	               
//		FilterBuilder TestModelFilter = new FilterBuilder()
//		        .includePattern("org\\.cartes\\.TestModel\\$.*")
//		        .includePattern("org\\.cartes\\.UsageTestModel\\$.*");
//		 Reflections reflections = new Reflections(new ConfigurationBuilder()
//	                .setUrls(Collections.singletonList(ClasspathHelper.forClass(CarteInfluence.class)))
//	                .filterInputsBy(TestModelFilter)
//	                .setScanners(
//	                    new SubTypesScanner(),
//	                    new TypeAnnotationsScanner(),
//	                    new MethodAnnotationsScanner(),
//	                    new FieldAnnotationsScanner(),
//	                    Scanners.ConstructorsAnnotated,
//	                    Scanners.MethodsParameter,
//	                    Scanners.MethodsSignature,
//	                    Scanners.MethodsReturn,
//	                    Scanners.ConstructorsParameter,
//	                    Scanners.ConstructorsSignature,
//	                    new ResourcesScanner(),
//	                    new MethodParameterNamesScanner(),
//	                    new MemberUsageScanner()));
//
//	        Set<Class<? extends CarteInfluence>> allClasses = reflections.getSubTypesOf(CarteInfluence.class);
//
//	        for (Class<?> subTypeOfActionInPackageNameClass : allClasses) {
//	           System.out.println(subTypeOfActionInPackageNameClass.getName());
//	        }
//	}
}