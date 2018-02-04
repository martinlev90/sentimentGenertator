import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
/**
 * Created by Martin on 15/12/2017.
 */
public class SentimentActions {
    private Properties props;
    private StanfordCoreNLP  sentimentPipeline;
    private  final String csvPath = "C:\\Users\\Martin\\Google Drive\\סמסטר ה\\מיני פרוייקט\\l6.csv";
    private final String outputPath = "C:\\Users\\Martin\\Google Drive\\סמסטר ה\\מיני פרוייקט\\output6.csv";
    private ArrayList<Record> songList;


    SentimentActions () {
    }

    private int calculateAvgSentiment (String song)
    {
        String [] lines;
        lines = song.split(",");
        int avg =0;
        int count = 0;
        for(String line:lines)
        {
            avg+=findSentiment(line);
            count++;
            System.out.println(findSentiment(line));
        }

        return avg/count;
    }

    public void findSentiment () throws IOException {
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, parse, sentiment");
        sentimentPipeline =  new StanfordCoreNLP(props);
        CSVWriter writer = new CSVWriter(new FileWriter(outputPath));
        int i = 0;
        for(Record r:songList)
        {
            r.setSentiment(calculateAvgSentiment(r.getLyrics()));
            System.out.println(i+" "+r.getSentiment());
            String [] record = Integer.toString(r.getSentiment()).split(",");
            writer.writeNext(record);
            i ++;
        }
        writer.close();
    }

    private int findSentiment(String song) {

        int mainSentiment = 0;
        if (song != null && song.length() > 0) {
            int longest = 0;
            Annotation annotation = sentimentPipeline.process(song);
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence
                        .get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }

            }
        }
        return mainSentiment;
    }

    public void parseCsv() throws IOException {
        songList = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(csvPath));
        String [] record;
        int i=0;
        while ((record = reader.readNext()) != null) {
            Record r = new Record(record[0],record[1],record[2],record[3],record[4],record[5]);
            songList.add(i,r);
            i++;
        }

        }
    }
