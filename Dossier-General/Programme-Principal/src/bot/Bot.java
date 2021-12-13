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
		int [] pointTotalMax= pointTotalMax(data);
		int bestIndex= pointTotalMax[0];
		int indexColonne= pointTotalMax[1];
		int indexMain=pointTotalMax[2];
		double pointEtreAttaque= etreAttaque(data, indexColonne, bestIndex);
		
	}
	
	
	
	public int[] pointTotalMax(Data data) {
		int[] bestIndex=null;
		bestIndex[0]=0;
		for(int i = 0 ; i< data.getPlateau().getColonnes().length ; i++) {	
			if(!data.getPlateau().getColonne(i).estPleine()) {
				for(int j=0; j<main.length; j++ ) {
				double pointTotal = data.getTotale(i, j, data.getCurrentJoueur());
			    if (pointTotal > bestIndex[0] && pasBon(i, j, pointTotal, data)) { //index+colonne...
			    	bestIndex[0]=(int)pointTotal;
			    	bestIndex[1]=i; //indexColonne 
			    	bestIndex[2]=j; //indexMain
			    	
			    }
			}
			}
			
		}
		return bestIndex;
	}
	//faudra le renommer mauvaisIdee?//se sert à éviter les move pas intelligent rule 5,6
	public Boolean pasBon(int indexColonne, int indexMain,double pointTotal, Data data) {
		for(int i=0; i<data.getJoueurs().length;i++) { 
			if(!(data.getCurrentJoueur()==i)) { //only consider the other players
				double pointTotal2 = data.getTotale(indexColonne, indexMain , i); //point on the colonne of that player
				CarteInfluence cartesSurColonne[]=data.getPlateau().getColonne(indexColonne).getCartesInfluences(); 
				if(pointTotal2>pointTotal && !(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si le nb de point de l'autre joueur est plus élevé there is no more room to play afterward ( the card before last move is already filled)
					return true;
				}	
				else { //si le joueurSuivant peux ajouter une carte pour gagner notre PointTotale
					int indexJoueurSuivant = data.getCurrentJoueur();
					if(data.getCurrentJoueur()<data.getJoueurs().length-1) {
						indexJoueurSuivant++;
					}
					else {
						indexJoueurSuivant=0;
					}
					List<CarteInfluence>cartesMain = getCartesPasDansDefausse(data, indexJoueurSuivant);
					if(cartesMain.size()==3 && !(cartesSurColonne[cartesSurColonne.length-2]==null)) {
						for(int j=0; j<cartesMain.size(); j++) {
							if(data.getTotale(indexColonne, j , indexJoueurSuivant)>pointTotal) {
								return true;
							}
						}
					}
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
	public double etreAttaque(Data data, int indexColonne, int bestIndex) {	
		double pointEtreAttaque=0;
		double pointTotal2=0;
		CarteInfluence cartesSurColonne[]=data.getPlateau().getColonne(indexColonne).getCartesInfluences(); 
		if(!(cartesSurColonne[cartesSurColonne.length-1]==null)) { //si la colonne sera plein apres notre partie et les autres joueurs n'auront aucun chance de nous attaquer
			return 0;
		}
		else {
			double point=0;
			for(int i=0; i<data.getJoueurs().length;i++) { 
			if(!(data.getCurrentJoueur()==i)) {
				List<CarteInfluence>cartesMain = getCartesPasDansDefausse(data, i);
				for(int j=0; j<cartesMain.size(); j++) {
					//gotta put new 3 main card into the colonne
					pointTotal2 = data.getTotale(indexColonne, j , i);
					point=bestIndex-pointTotal2;
					if(point>pointEtreAttaque) {
						pointEtreAttaque=point;
					}
				} 
				
				
			}
			}
		}
		
		return pointEtreAttaque;
	}
	
	public double pointAttaquer(Data data) {
		
		return 0;
	}
	
	
	
	
	
				
				
				
				
				
	public void jouer_difficile(Colonne[] cols) {
	}
	
	
	static private int getRandomInt(int max) {
		return (int) (new Random().nextInt(max));
	}
	public ArrayList<String> get25CartesInfluences() {
		String[] strs = new String[]{ "Alchimiste", "Assassin", "CapeDInvisibilite", "Cardinal","Dragon","Ecuyer","Ermite","Explorateur","Juliette","Magicien","MaitreDArme","Marchand","Mendiant","PetitGeant","Prince","Reine","Roi","Romeo","Seigneur", "Sorciere", "Sosie","Tempete","Traite","TroisMousquetaires","Troubadour" };
		ArrayList<String> cartesInfluences = new ArrayList<String>();
		for (String s : strs) {
		    cartesInfluences.add(s);
		}
		return cartesInfluences;
	}
	public List<CarteInfluence> getCartesPasDansDefausse(Data data, int indexJoueur) {
		CarteInfluence [] cartesDefausse=data.getJoueursAvecIndex(indexJoueur).getDefausse();
		List<CarteInfluence> res = Arrays.asList(cartesDefausse);  
		ArrayList<String> cartesInfluences = get25CartesInfluences();
		for(int i=0; i<cartesDefausse.length; i++) {
			for(int j=0; j<cartesInfluences.size();j++) {
				if(res.get(i).getClass().getName()==cartesInfluences.get(j)) {
					res.remove(i);
				}
			}
			
		}
		return res;
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