
import java.util.*;

public class Grille {

	private ArrayList<Tuile> tuiles;

	private Tuile[][] grille;

	public Grille() {
		tuiles = new ArrayList<>();

		tuiles.add(new Tuile(Role.recupCristal, "La Caverne des Ombres"));
		tuiles.add(new Tuile(Role.aucun, "Le Val du Crépuscule"));
		tuiles.add(new Tuile(Role.porteVerte, "La Porte de Cuivre"));
		tuiles.add(new Tuile(Role.porteNoire, "La Porte de Fer"));
		tuiles.add(new Tuile(Role.aucun, "Le Marais Brumeux"));
		tuiles.add(new Tuile(Role.recupZephyr, "Jardin des Murmures"));
		tuiles.add(new Tuile(Role.recupCristal, "La Caverne du Brasier"));
		tuiles.add(new Tuile(Role.porteJaune, "La Porte d'Or"));
		tuiles.add(new Tuile(Role.recupPierre, "Le Temple du Soleil"));
		tuiles.add(new Tuile(Role.aucun, "Le Pont des Abîmes"));
		tuiles.add(new Tuile(Role.porteViolette, "La Porte d'Argent"));
		tuiles.add(new Tuile(Role.recupZephyr, "Le Jardin des Hurlements"));
		tuiles.add(new Tuile(Role.aucun, "Les Dunes de l'Illusion"));
		tuiles.add(new Tuile(Role.aucun, "Les Falaises de l'Oubli"));
		tuiles.add(new Tuile(Role.aucun, "L'Observatoire"));
		tuiles.add(new Tuile(Role.recupCalice, "Le Palais des Marées"));
		tuiles.add(new Tuile(Role.heliport, "L'Héliport"));
		tuiles.add(new Tuile(Role.aucun, "Le Rocher Fantôme"));
		tuiles.add(new Tuile(Role.recupPierre, "Le Temple de la Lune"));
		tuiles.add(new Tuile(Role.aucun, "La Forêt Pourpre"));
		tuiles.add(new Tuile(Role.aucun, "Le Lagon Perdu"));
		tuiles.add(new Tuile(Role.porteRouge, "La Porte de Bronze"));
		tuiles.add(new Tuile(Role.recupCalice, "Le Palais de Corail"));
		tuiles.add(new Tuile(Role.aucun, "La Tour de Guet"));

		grille = new Tuile[6][6];
		int k = 0;

		Collections.shuffle(tuiles);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {


				if ((i == 0 && j == 2) || (i == 0 && j == 3) || (i == 1 && j == 1) || (i == 1 && j == 2) || (i == 1 && j == 3) || (i == 1 && j == 4) || (i == 2 && j == 0)
						|| (i == 2 && j == 1) || (i == 2 && j == 2) || (i == 2 && j == 3) || (i == 2 && j == 4) || (i == 2 && j == 5) || (i == 3 && j == 0) || (i == 3 && j == 1)
						|| (i == 3 && j == 2) || (i == 3 && j == 3) || (i == 3 && j == 4) || (i == 3 && j == 5) || (i == 4 && j == 1) || (i == 4 && j == 2) || (i == 4 && j == 3)
						|| (i == 4 && j == 4) || (i == 5 && j == 2) || (i == 5 && j == 2) || (i == 5 && j == 3)) {
                                        tuiles.get(k).setCoord(i, j);
                                        grille[i][j] = tuiles.get(k);
					k++;
				}

			}
		}
	}

	public ArrayList<Tuile> getTuilesPraticables() {
		Tuile tuile;
		ArrayList<Tuile> tuilesPrat = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				tuile = grille[i][j];
				if (Utils.EtatTuile.COULEE != tuile.getEtat()) {
					tuilesPrat.add(grille[i][j]);
				}

			}
		}
		return tuilesPrat;
	}

	public ArrayList<Tuile> getTuiles() {
		return tuiles;
	}

	public Tuile[][] getGrille(){
		return this.grille;
	}

	public Tuile getTuileCoord(int x,int y) {
		return grille[x][y];
	}

	public ArrayList<Tuile> getTuilesAvecJoueurs() {
		ArrayList<Tuile> tuilesJoueurs = new ArrayList<>();

		for (Tuile tuile : tuiles) {
			if (tuile.getJoueurs().size() != 0) {
				tuilesJoueurs.add(tuile);
			}
		}

		return tuilesJoueurs;
	}

	public Tuile getTuileUnique(Role role) {
		for (Tuile[] ligneTuiles : grille) {
			for (Tuile tuile : ligneTuiles) {
				if (tuile != null) {
					if (tuile.getRole() == role) {
						return tuile;
					}
				}
			}
		}

		return null;
	}

	public ArrayList<Tuile> getTuileTresor(Role role) {
		ArrayList<Tuile> listeTuiles = new ArrayList<>();
		for (Tuile[] ligneTuiles : grille) {
			for (Tuile tuile : ligneTuiles) {
				if (tuile != null) {
					if (tuile.getRole() == role) {
						listeTuiles.add(tuile);
					}
				}
			}
		}

		return listeTuiles;
	}

	public HashMap<Aventurier, ArrayList<Tuile>> getDeplacementsAutres(Aventurier navi, ArrayList<Aventurier> joueurs) {
		int i = 0;
		ArrayList<Aventurier> joueursDeplacables = new ArrayList<>();
		ArrayList<ArrayList<Tuile>> deplacementsPossiblesJoueur = new ArrayList<>();
		HashMap<Aventurier, ArrayList<Tuile>> resultat = new HashMap<>();

		// Affichage des possibilités
		System.out.println("Voici les joueurs déplaçables :");
		for (Aventurier joueurTeste : joueurs) {

			ArrayList<Tuile> listeTuilesAdj = new ArrayList<>();

			for (Tuile tuileTestee : joueurTeste.getDeplacementsPossibles(this)) {
				int x = tuileTestee.getCordX();
				int y = tuileTestee.getCordY();
				Tuile tuileTestee2;

				for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
					for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
						if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
							tuileTestee2 = this.getTuileCoord(xTuile, yTuile);
							if (tuileTestee2 != null) {
								if ((tuileTestee2.getEtat() != Utils.EtatTuile.COULEE) && !((Math.abs(xTuile - x) == 1) && (Math.abs(yTuile - y) == 1)) && !(listeTuilesAdj.contains(tuileTestee2)) && (!tuileTestee2.equals(joueurTeste.getPosition()))) {
									listeTuilesAdj.add(tuileTestee2);
								}
							}
						}
					}
				}
			}

			if ((!listeTuilesAdj.isEmpty()) && joueurTeste != navi){
				deplacementsPossiblesJoueur.add(listeTuilesAdj);
				joueursDeplacables.add(joueurTeste);
				i++;
			}
		}

		for (int j = 0; j < joueursDeplacables.size(); j++) {
			resultat.put(joueursDeplacables.get(j), deplacementsPossiblesJoueur.get(j));
		}

		return resultat;
	}
}
