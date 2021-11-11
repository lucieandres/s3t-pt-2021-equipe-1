//package elements;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import cartes.CarteInfluence;
//
///**
//* @generated
//*/
//public class Defausse extends ElementPlateau {
//    
//	private ArrayList<CarteInfluence> cartesInfluences;
//	
//        //constructeur
//    public Defausse() {
//    	this.cartesInfluences = null;
//    }
//    
//    public void addDefausseCarteInfluence(CarteInfluence carteInfluence) {
//    	if(!carteInfluence.getEstVisible())
//    		carteInfluence.setEstVisible(true);
//    	
//    	this.cartesInfluences.add(carteInfluence);
//    }
//    
//    public void shuffleDefausse() { //si la reserve du joueur est epuisee, il melange sa d�fausse et la r�utilise en tant que reserve face cach�e
//    	Collections.shuffle(cartesInfluences);
//    }
//    
//    public void viderDefausse() { //si la r�serve a �t� reconstitu�e par les cartes de la d�fausse, celle-ci doit �tre vid�e
//    	this.cartesInfluences.clear();
//    }
//    
//    public ArrayList<CarteInfluence> getDefausse(){
//    	return this.cartesInfluences;
//    }
//}
