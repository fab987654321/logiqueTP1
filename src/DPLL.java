import java.util.List;
import java.util.Stack;

public class DPLL {
    private Stack<Integer[]> stack; // [lit, 0-1] garde en memoire l'action precedente
    /** Longueur de chaques clauses */
    public List<Integer> longueurClause;
    public List<List<Integer>> litteralClause;
    /** Litéraux de chaques clauses */
    public List<List<Integer>> clauseLitteral;
    /**
     * Valeur donnée a chaque litéral V/F
     */
    public List<Integer> etatLitteral;
    /** Si = 1 alors clause traité entièrement */
    public List<Integer> etatClause;

    public DPLL(List<Integer> etatClause, List<Integer> etatLitteral, List<Integer> longueurClause,
            List<List<Integer>> clauseLitteral, List<List<Integer>> litteralClause) {
        this.etatClause = etatClause;
        this.etatLitteral = etatLitteral;
        this.longueurClause = longueurClause;
        this.clauseLitteral = clauseLitteral;
        this.litteralClause = litteralClause;
    }

    public Stack<Integer[]> solve() {
        // test monoLit a echoue ( [a] et [-a] => insatisfaisable )
        // TODO remplacer par un throws (si possible)
        if (!testMonoLit())
            return null;
        testPurLit();
        // while (!isOver()) {
        // algo backtrack
        // }
        return stack;
    }

    private boolean testMonoLit() {
        int monoLit;
        int monoLitB;
        while (longueurClause.contains(1))
            for (int i = 0; i < longueurClause.size(); i++)
                // Détecte un monolitéral
                if (longueurClause.get(i) == 1) {
                    if (clauseLitteral.get(i).isEmpty())
                        continue;

                    monoLit = clauseLitteral.get(i).remove(0);// Vide la clause
                    etatClause.set(i, 1);// Définit la clause comme complétement traité
                    longueurClause.set(i, 0);// taille clause => 0
                    for (int k = 0; k < clauseLitteral.size(); k++) {
                        // on met a vrai toutes les clauses qui contiennent ce litteral
                        if (clauseLitteral.get(k).contains(monoLit)) {
                            clauseLitteral.get(k).clear();
                            longueurClause.set(k, 0);
                            etatClause.set(k, 1);
                        }
                    }
                    etatLitteral.set(monoLit, 2); // le litteral est mis a vrai et ne changera plus
                    litteralClause.get(monoLit).clear(); // le litteral n'apparait plus dans une clause

                    // TODO vérifier qu'un monolitéral et son inverse soient bien à de valeurs
                    // inverse
                    //// Si un monolitéral inverse alors on arrête
                    //// Sinon on mets sa valeur à getLitB(monoLit);
                    /*
                     * A voir mais pour moi implique de devoir tester si le litéral na pas déjà de
                     * valeur définitive en amon
                     */
                    // Met à faux le monolitéral inverse (a=1 et â=0)
                    monoLitB = getLitB(monoLit);
                    System.out.println(">>>" + monoLit + ":" + monoLitB);
                    // etatLitteral.set(monoLitB,3); // le litteralB est mis a faux et ne changera
                    // plus

                }

        return false;
    }

    private void testPurLit() {
        // Literaux purs CAD

    }

    private int getLitB(int lit) { // return l'inverse de lit
        return lit % 2 == 0 ? lit + 1 : lit - 1;
    }

    private boolean isOver() { // Tous les litteraux ont ete teste
        return etatLitteral.contains(0) || etatLitteral.contains(1);
    }
}
