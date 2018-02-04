import java.io.IOException;

/**
 * Created by Martin on 15/12/2017.
 */
public class Main {

    public static void main(String args[]) throws IOException {
        SentimentActions sentimentActions = new SentimentActions();
        sentimentActions.parseCsv();
        sentimentActions.findSentiment();
    }

}



