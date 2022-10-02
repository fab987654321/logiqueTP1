import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private final Scanner scanner;
    public final List<Integer> etatClause = new ArrayList<>();            // etat d'une clause 0-1
    public final List<Integer> etatLitteral = new ArrayList<>();           // etat d'un litteral 0-1-2
    public final List<Integer> longueurClause = new ArrayList<>();        // nbLitteraux/clause
    public final List<List<Integer>> clauseLitteral = new ArrayList<>();   // clause 2 -> litteraux 1,4,8...
    public final List<List<Integer>> litteralClause = new ArrayList<>();   // litteral 1 -> clauses 2,3,8...
    public Parser(FileInputStream file) {
        scanner = new Scanner(file);
        parser();
    }

    public void parser() {
        try {
            String firstLine = scanner.nextLine();
            int nbClause = 0;
            while(scanner.hasNext()) {
                String[] clause = scanner.nextLine().split(" ");
                longueurClause.add(clause.length);
                clauseLitteral.add(new ArrayList<>());
                for(String literal : clause) clauseLitteral.get(nbClause).add(Integer.parseInt(literal));
                nbClause++;
            }
            for(int i=0;i<nbClause;i++) etatClause.add(-1);
            setLitteral(firstLine);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLitteral(String firstLine) {
        String nbVariable = firstLine.substring(firstLine.indexOf(":")+1,firstLine.indexOf("/")).trim();
        int nbVar = Integer.parseInt(nbVariable);
        for(int i=0;i<2*nbVar;i++) {
            etatLitteral.add(0);
            litteralClause.add(new ArrayList<>());
            setLitteralClause(i);
        }
    }
    private void setLitteralClause(int i) {
        for(List<Integer> list : clauseLitteral) {
            if(list.contains(i)) litteralClause.get(i).add(clauseLitteral.indexOf(list));
        }
    }
}
