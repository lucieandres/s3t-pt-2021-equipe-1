package elements;

import java.util.ArrayList;

import cartes.CarteInfluence;
import cartes.CarteObjectif;
import joueur.Joueur;

/**
* @generated
*/
public class Colonne {
    
	
    private static ArrayList<CarteInfluence> cartesInfluences;
    private static Joueur joueur;
    /**
    * @generated
    */
    private CarteObjectif carteObjectif;
    private Plateau plateau;
    
    //constructeur
	public Colonne(CarteObjectif carteObjectif, Plateau plateau) {
		super(cartesInfluences,joueur);
        this.carteObjectif=carteObjectif;
        this.plateau=plateau;
	}
    
    /**
    * @generated
    */
    public CarteObjectif getCarteObjectif() {
        return this.carteObjectif;
    }
    
    /**
    * @generated
    */
    public void setCarteObjectif(CarteObjectif carteObjectif) {
        this.carteObjectif = carteObjectif;
    }
    
    public Plateau getPlateau() {
        return this.plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
    
        //methode qui retourne une carte
        public void tournerCarte(CarteInfluence carteInfluence){
            carteInfluence.setEstVisible(true);
        }
    
        //retirer une carte de la colonne et la mettre dans la defausse
        public void Enlever(CarteInfluence carteInfluence,Colonne colonne) {
            colonne.getPlateau().getDefausse().getCartesInfluences().add(carteInfluence);
            colonne.getCartesInfluences().remove(carteInfluence);
        }
    
        //methode qui retire toutes les cartes
        public void EnleverTous(Colonne colonne) {
            for(int i=0; i<colonne.getCartesInfluences().size(); i++) {
                colonne.getPlateau().getDefausse().getCartesInfluences().get(i);
            }
            colonne.setCartesInfluences(new ArrayList<CarteInfluence>());
        }
        
        //methode renvoyant true tant que la colonne n'est pas pleine
        //renvoie false si pleine (est pleine si égale à 6+(nb joueurs)*2)
        public Boolean estPleine(Colonne colonne) {
        	if (cartesInfluences.size() < (6*(Partie.nombreJoueurs(this.partie))+2) {
        		return true;
        	}
        	else
        		return false;
        }
}
