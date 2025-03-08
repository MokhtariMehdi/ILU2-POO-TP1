package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private Etal[] etal;
	
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		etal = new Etal[nbEtals];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for(int i = 0;i<nbEtals;i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			if((indiceEtal > 0) && (indiceEtal>etals.length)) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		public int trouverEtalLibre() {
			for(int i = 0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe() == true) {
					return i;
				}
			}
		    return -1;
		}
		public Etal[] trouverEtals(String produit) {
		    int compteur = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].contientProduit(produit)) {
		            compteur++;
		        }
		    }
		    Etal[] etalprod = new Etal[compteur];
		    int k = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].contientProduit(produit)) {
		            etalprod[k] = etals[i];
		            k++;
		        }
		    }
		    return etalprod;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0;i<etals.length;i++) {
				if(etals[i].getVendeur() != null && etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
		return null;
		}
		
		public void afficherMarche() {
			int nbEtalVide = 0;
			for(int i = 0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				}
				else {
					nbEtalVide++;
				}
			}
			System.out.println("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
		}
		}
		
	}
