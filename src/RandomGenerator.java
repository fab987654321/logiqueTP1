import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomGenerator {
    private final Random random = new Random();
    private final PrintWriter writer;  // Pair -> literaux positifs // Impair -> literaux negatifs
    private final int nbVariable;
    private final int nbClause;

    public RandomGenerator(int nbVariable, int nbClause) {
        this.nbVariable = nbVariable;
        this.nbClause = nbClause;
        try {
            writer = new PrintWriter("D://bureau/Coding/Java/DPLL/testGenerator/test.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setGenerator();
    }

    public void setGenerator() {
        writer.println("nbVariable: "+nbVariable+" / nbClause: "+nbClause);
        for(int i=0; i<nbClause-1; i++) writer.println(createClause());
        writer.print(createClause());
        writer.close();
    }

    private String createClause() {  // literaux entre 0 et 2n-1
        StringBuilder clause = new StringBuilder();
        for(int i=0; i<random.nextInt(1,2*nbVariable+1); i++) { // nb aleatoire de literaux par ligne
            clause.append(random.nextInt(0,2*nbVariable));
            clause.append(" ");
        }
        return clause.toString();
    }
}
