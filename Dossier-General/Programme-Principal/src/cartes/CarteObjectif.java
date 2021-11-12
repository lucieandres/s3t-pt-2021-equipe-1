package cartes;

/** Cette classe définit une carte <i>Objectif</i> ainsi que toute ses propriétés ; son domaine et sa valeur. Il existe trente-six de ces cartes. 
* 
* @author S3T - G1
* 
* @since 1.0
*/
public class CarteObjectif {

    private String domaine;

    private int valeur;
    
    /**
     * Ce constructeur produit une carte <i>Objectif</i> en lui assignant son domaine et sa valeur.
     * 
     * @param domaine Le nom du domaine de la carte <i>Objectif</i> parmi les 6 domaines suivants : "Alchimie", "Combat", "Agriculture", "Commerce", "Religion" et "Musique".
     * 
     * @param valeur La valeur de la carte <i>Objectif</i>.
     * 
     * @since 1.0
     */
    public CarteObjectif(String domaine, int valeur) {
        this.domaine = domaine;
        this.valeur = valeur;
    }
    
    /**
     * Retourne le domaine de la carte <i>Objectif</i>.
     * 
     * @return Le domaine de la carte <i>Objectif</i>.
     * 
     * @since 1.0
     */
    public String getDomaine() {
        return this.domaine;
    }

    /**
     * Modifie le domaine de la carte <i>Objectif</i>.
     * 
     * @param valeur Le domaine de la carte <i>Objectif</i>.
     * 
     * @since 1.0
     */
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    /**
     * Retourne la valeur de la carte <i>Objectif</i>.
     * 
     * @return La valeur de la carte <i>Objectif</i>.
     * 
     * @since 1.0
     */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * Modifie la valeur de la carte <i>Objectif</i>.
     * 
     * @param valeur Le nom de la carte <i>Objectif</i>.
     * 
     * @since 1.0
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
}
