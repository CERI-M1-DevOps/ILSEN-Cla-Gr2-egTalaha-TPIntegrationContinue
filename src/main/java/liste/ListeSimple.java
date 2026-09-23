package liste;
/**
 * Liste simplement chaînée d'éléments, construite à partir de {@link Noeud}.
 * Les nouveaux éléments sont ajoutés en tête de liste.
 */
public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments contenus dans la liste.
     * @return la taille de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément en tête de liste.
     * @param element la valeur à ajouter
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Remplace la valeur de la première occurrence d'un élément.
     * La liste reste inchangée si l'élément est absent.
     * @param element la valeur à rechercher
     * @param nouvelleValeur la valeur de remplacement
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Remplace la valeur de toutes les occurrences d'un élément.
     * @param element la valeur à rechercher
     * @param nouvelleValeur la valeur de remplacement
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation textuelle de la liste,
     * par exemple {@code ListeSimple(Noeud(3), Noeud(2), Noeud(1))}.
     * @return la liste sous forme de chaîne
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un élément.
     * La liste reste inchangée si l'élément est absent.
     * @param element la valeur à supprimer
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément.
     * @param element la valeur à supprimer
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime récursivement toutes les occurrences d'un élément
     * dans la sous-liste qui commence au nœud donné.
     * @param element la valeur à supprimer
     * @param tete le premier nœud de la sous-liste
     * @return le premier nœud de la sous-liste après suppression, ou {@code null} si elle est vide
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier nœud de la liste.
     * @return l'avant-dernier nœud, ou {@code null} si la liste contient moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments de la liste, sans créer de nouveaux nœuds.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le nœud qui précède le nœud donné dans la liste.
     * Le nœud doit appartenir à la liste et ne pas être la tête.
     * @param r le nœud dont on cherche le prédécesseur
     * @return le nœud précédant {@code r}
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange la position de deux nœuds de la liste.
     * Ne fait rien si les deux nœuds sont identiques.
     * @param r1 le premier nœud à échanger
     * @param r2 le second nœud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else if (r2 == tete) {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}
