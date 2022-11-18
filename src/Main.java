import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        new RandomGenerator(2, 5);
        try {
            Parser parser = new Parser(new FileInputStream("./test.txt"));
            String ec = parser.etatClause.toString();
            String el = parser.etatLitteral.toString();
            String loc = parser.longueurClause.toString();
            String cl = parser.clauseLitteral.toString();
            String lc = parser.litteralClause.toString();
            DPLL solver = new DPLL(parser.etatClause, parser.etatLitteral, parser.longueurClause, parser.clauseLitteral,
                    parser.litteralClause);
            System.out.println(solver.solve());
            System.out.println("etatClause: " + ec + " : " + solver.etatClause);
            System.out.println("etatLitteral: " + el + " : " + solver.etatLitteral);
            System.out.println("longueurClause: " + loc + " : " + solver.longueurClause);
            System.out.println("clauseLitteral: " + cl + " : " + solver.clauseLitteral);
            System.out.println("litteralClause: " + lc + " : " + solver.litteralClause);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
