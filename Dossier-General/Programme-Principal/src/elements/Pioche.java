// package elements;

// import java.util.ArrayList;

// import cartes.CarteObjectif;
// import joueur.Joueur;

// public class Pioche{

//     private String domaine;
//     private CarteObjectif carteObjectif1 = new CarteObjectif(domaine, 1);
//     private CarteObjectif carteObjectif;
//     private ArrayList<CarteObjectif> carteObjectifs;
//     private ArrayList<Joueur> joueurs;


//     public Pioche(ArrayList<CarteObjectif> carteObjectifs, ArrayList<Joueur> joueurs) {
//         this.carteObjectifs=carteObjectifs;
//         this.joueurs=joueurs;
//     }

//     public void ajusterPioche(){
//         switch(joueurs.size()){
//             case 2:
//                 if(carteObjectifs.contains(carteObjectif1)){
//                     for (CarteObjectif carteObjectif1 : carteObjectifs) {
//                     carteObjectifs.remove(carteObjectif1);
//                     }
//                 }
//                 carteObjectifs.add(12, carteObjectif);
//                 break;
//             case 3:
//                 carteObjectifs.add(18, carteObjectif);
//                 break;
//             case 4:
//                 carteObjectifs.add(24, carteObjectif);
//                 break;
//             case 5:
//                 carteObjectifs.add(30, carteObjectif);
//                 break;
//             default:
//                 carteObjectifs.add(36, carteObjectif);
//                 break;

//         }
//     }
// }
