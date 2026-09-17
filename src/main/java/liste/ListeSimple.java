```java
package liste;

import java.util.Objects;

public class ListeSimple {

    private long size;
    private Noeud tete;

    public long getSize() {
        return size;
    }

    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;

        while (courant != null
                && !Objects.equals(courant.getElement(), element)) {
            courant = courant.getSuivant();
        }

        if (courant != null) {
            courant.setElement(nouvelleValeur);
        }
    }

    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;

        while (courant != null) {
            if (Objects.equals(courant.getElement(), element)) {
                courant.setElement(nouvelleValeur);
            }

            courant = courant.getSuivant();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud courant = tete;

        while (courant != null) {
            sb.append(courant);
            courant = courant.getSuivant();

            if (courant != null) {
                sb.append(", ");
            }
        }

        sb.append(")");
        return sb.toString();
    }

    public void supprimePremier(Object element) {
        if (tete == null) {
            return;
        }

        if (Objects.equals(tete.getElement(), element)) {
            tete = tete.getSuivant();
            size--;
            return;
        }

        Noeud precedent = tete;
        Noeud courant = tete.getSuivant();

        while (courant != null
                && !Objects.equals(courant.getElement(), element)) {
            precedent = courant;
            courant = courant.getSuivant();
        }

        if (courant != null) {
            precedent.setSuivant(courant.getSuivant());
            size--;
        }
    }

    public void supprimeTous(Object element) {
        tete = supprimeTousRecurs(element, tete);
    }

    private Noeud supprimeTousRecurs(Object element, Noeud courant) {
        if (courant == null) {
            return null;
        }

        Noeud suiteListe =
                supprimeTousRecurs(element, courant.getSuivant());

        if (Objects.equals(courant.getElement(), element)) {
            size--;
            return suiteListe;
        }

        courant.setSuivant(suiteListe);
        return courant;
    }

    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null) {
            return null;
        }

        Noeud courant = tete;
        Noeud suivant = tete.getSuivant();

        while (suivant.getSuivant() != null) {
            courant = suivant;
            suivant = suivant.getSuivant();
        }

        return courant;
    }

    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;

        while (courant != null) {
            Noeud suivant = courant.getSuivant();

            courant.setSuivant(precedent);

            precedent = courant;
            courant = suivant;
        }

        tete = precedent;
    }

    public Noeud getPrecedent(Noeud r) {
        if (tete == null || r == null || r == tete) {
            return null;
        }

        Noeud precedent = tete;
        Noeud courant = tete.getSuivant();

        while (courant != null && courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }

        return courant == r ? precedent : null;
    }

    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == null || r2 == null || r1 == r2) {
            return;
        }

        Noeud precedentR1 = getPrecedent(r1);
        Noeud precedentR2 = getPrecedent(r2);

        // Les deux nœuds doivent appartenir à la liste.
        if (r1 != tete && precedentR1 == null) {
            return;
        }

        if (r2 != tete && precedentR2 == null) {
            return;
        }

        // r1 est la tête
        if (r1 == tete) {
            tete = r2;
        } else {
            precedentR1.setSuivant(r2);
        }

        // r2 est la tête
        if (r2 == tete) {
            tete = r1;
        } else {
            precedentR2.setSuivant(r1);
        }

        Noeud suivantR1 = r1.getSuivant();
        r1.setSuivant(r2.getSuivant());
        r2.setSuivant(suivantR1);
    }
}
```
