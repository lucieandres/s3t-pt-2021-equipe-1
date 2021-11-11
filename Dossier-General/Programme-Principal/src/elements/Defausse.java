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
//    public void shuffleDefausse() { //si la reserve du joueur est epuisee, il melange sa défausse et la réutilise en tant que reserve face cachée
//    	Collections.shuffle(cartesInfluences);
//    }
//    
//    public void viderDefausse() { //si la réserve a été reconstituée par les cartes de la défausse, celle-ci doit être vidée
//    	this.cartesInfluences.clear();
//    }
//    
//    public ArrayList<CarteInfluence> getDefausse(){
//    	return this.cartesInfluences;
//    }
//}
