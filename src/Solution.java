import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Solomon Lasluisa on 8/30/14.
 */
public class Solution {

    private List<Query> queriesFromInput = new ArrayList<Query>();
    HashMap<Integer, QuoraData> topicsFromInput = new HashMap<Integer, QuoraData>();
    HashMap<Integer, QuoraData> questionsFromInput = new HashMap<Integer, QuoraData>();
    QuoraData[] topicsFromInputArray;
    QuoraData[] questionsFromInputArray;
    public static final  Integer MAXNUMBEROFQUESTION = 1000;

    public boolean validateInput(List<String> inputFromQuora){
        Boolean returnValue = false;
        String firstLine = inputFromQuora.get(0);
        String [] parameters = firstLine.split(" ");
        List<String> topics;
        List<String> questions;
        List<String> queries;
        Integer numTopics;
        Integer numQuestions;
        Integer numQueries;

        if(parameters.length != 3) {
            throw new IllegalArgumentException("first line must contain 3 integers: number of topics T, number of questions Q, and number of queries N.");
        }

        try {
            numTopics = Integer.parseInt(parameters[0]);
            numQuestions = Integer.parseInt(parameters[1]);
            numQueries = Integer.parseInt(parameters[2]);
        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException("first line must contain only integers");
        }

        if(  numTopics> 10000 | numTopics < 1 | numQuestions > MAXNUMBEROFQUESTION | numQuestions <1 | numQueries > 10000 | numQueries <1 ) {
            throw new IllegalArgumentException("the number of Topics, Questions, or Queries are out of bounds");
        }

        if(inputFromQuora.size() != 1 + numTopics + numQuestions + numQueries) {
            throw new IllegalArgumentException("the number of Topics, Questions, and Queries does not add up in this input");
        }

        topics    = inputFromQuora.subList(1, 1+numTopics);
        questions = inputFromQuora.subList(1 + numTopics, 1 + numTopics + numQuestions);
        queries   = inputFromQuora.subList(1 + numTopics + numQuestions, 1 + numTopics + numQuestions + numQueries);

        validateTopics(topics);
        validateQuestions(questions);
        validateQueries(queries);

        returnValue = true;
        return returnValue;
    }

    public boolean validateTopics(List<String> inputTopics) {
        Boolean returnValue = false;
        String [] splitTopicLine;
        Double x;
        Double y;
        Integer id;
        Integer topicsAddedSoFar =0;
        QuoraData tempTopic;
        this.topicsFromInputArray = new QuoraData[inputTopics.size()];
        for (String topic : inputTopics) {
            splitTopicLine = topic.split(" ");
            if(splitTopicLine.length != 3) {
                throw new IllegalArgumentException("topic has invalid format for topics: id x y");
            }

            try {
                id = Integer.parseInt(splitTopicLine[0]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("invalid format for topic id");
            }

            try {
                x = Double.parseDouble(splitTopicLine[1]);
                y = Double.parseDouble(splitTopicLine[2]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("topic has invalid format for coordinates");
            }

            if( x < 0| x > 1000000.0 |y < 0 | y >1000000.0) {
                throw new IllegalArgumentException("coordinates are out of bounds");
            }

            if( id < 0 | id> 100000 ) {
                throw new IllegalArgumentException("topic id is out of bounds");
            }
            tempTopic = new QuoraData(id, x, y);
            this.topicsFromInput.put(id, tempTopic);
            this.topicsFromInputArray[topicsAddedSoFar] = tempTopic;
            topicsAddedSoFar++;
        }
        returnValue = true;
        return returnValue;
    }

    public boolean validateQuestions(List<String> inputQuestions) {
        Boolean returnValue = false;
        String [] splitQuestionLine;
        Integer topicForThisQuestion ;
        Integer id;
        Integer numberOfTopics;
        Integer sumOfNumberOfUsableQuestions = 0;
        for (String question : inputQuestions) {
            splitQuestionLine = question.split(" ");
            if(splitQuestionLine.length < 2| splitQuestionLine.length > 12) {
                throw new IllegalArgumentException("question has invalid format: id numberOfTopics topic1 ... topicN. Note N up to 10");
            }

            try {
                id = Integer.parseInt(splitQuestionLine[0]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("invalid format for question id");
            }

            try {
                numberOfTopics = Integer.parseInt(splitQuestionLine[1]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("invalid format for number of topic for question");
            }

            if( id < 0 | id> 100000 ) {
                throw new IllegalArgumentException("question id is out of bounds");
            }

            if( numberOfTopics < 0 | numberOfTopics > 10 ) {
                throw new IllegalArgumentException("number of topics for question is out of bounds");
            }

            if(splitQuestionLine.length != numberOfTopics + 2) {
                throw new IllegalArgumentException("question line does not match expected count");
            }

            if (numberOfTopics > 0) {
                sumOfNumberOfUsableQuestions = sumOfNumberOfUsableQuestions+1;
            }
        }

        questionsFromInputArray = new QuoraData[sumOfNumberOfUsableQuestions];

        Integer questionsSoFar =0;
        for (String question : inputQuestions) {
            splitQuestionLine = question.split(" ");

            for (int i = 2; i < splitQuestionLine.length; i++) {
                try {
                    topicForThisQuestion = Integer.parseInt(splitQuestionLine[i]);
                } catch (NumberFormatException ex) {
                    throw new NumberFormatException("invalid format for number of topic for question");
                }
                id = Integer.parseInt(splitQuestionLine[0]);
                if(!questionsFromInput.containsKey(id)) {
                    List<QuoraData> otherData = new ArrayList<QuoraData>();
                    otherData.add(this.topicsFromInput.get(topicForThisQuestion));
                    questionsFromInput.put(id, new QuoraData(id ,otherData));
                    questionsFromInputArray[questionsSoFar] =questionsFromInput.get(id);
                    questionsSoFar++;
                } else {
                    questionsFromInput.get(id).otherData.add(this.topicsFromInput.get(topicForThisQuestion));
                }
            }
        }
        returnValue = true;
        return returnValue;
    }

    public boolean validateQueries(List<String> inputQueries) {
        Boolean returnValue = false;
        String [] splitQueryLine;
        Double x;
        Double y;
        String queryType;
        Integer numberOfReturnResults;
        for (String topic : inputQueries) {
            splitQueryLine = topic.split(" ");
            if(splitQueryLine.length != 4) {
                throw new IllegalArgumentException("query has invalid format for topics: queryType numberOfResultsToReturn x y");
            }

            queryType = splitQueryLine[0];

            if(!queryType.equalsIgnoreCase("q") & !queryType.equalsIgnoreCase("t") ) {
                throw new IllegalArgumentException("invalid query type");
            }

            try {
                numberOfReturnResults = Integer.parseInt(splitQueryLine[1]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("invalid format for number of results to return");
            }

            try {
                x = Double.parseDouble(splitQueryLine[2]);
                y = Double.parseDouble(splitQueryLine[3]);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("query has invalid format for coordinates");
            }

            if( x < 0| x > 1000000.0 |y < 0 | y >1000000.0) {
                throw new IllegalArgumentException("query coordinates are out of bounds");
            }

            if( numberOfReturnResults < 0 | numberOfReturnResults> 100 ) {
                throw new IllegalArgumentException("number of results to return is out of bounds");
            }
            this.queriesFromInput.add(new Query(queryType, numberOfReturnResults, x,y));
        }
        return returnValue;
    }

    public void runQueries() {
        //Integer i =0;
        List<Integer> toPrint;
        for(Query query:queriesFromInput) {
            if(query.type.equalsIgnoreCase("t")) {
                toPrint = getClosestTopics(query.numberOfResultsToReturn, query.cords);
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Boolean firstLine = true;
                for (Integer num : toPrint) {
                    try {
                        if (firstLine) {
                            out.write(String.valueOf(num));
                            firstLine = false;
                        } else {
                            out.write(" "+String.valueOf(num));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    out.write('\n');
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (query.type.equalsIgnoreCase("q")) {
                toPrint = getClosestQuestions(query.numberOfResultsToReturn,query.cords);
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Boolean firstLine = true;
                for (Integer num : toPrint) {
                    try {
                        if (firstLine) {
                            out.write(String.valueOf(num));
                            firstLine = false;
                        } else {
                            out.write(" "+String.valueOf(num));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    out.write('\n');
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Integer> getClosestTopics(Integer numberToReturn, Point2D.Double center) {
        List<Integer> returnListOfTopics = new ArrayList<Integer>();
        List<QuoraData> kSmallestDistances = new ArrayList<QuoraData>();
        //Distance[] unorderedArray = createUnsortedArray(center); //= new Distance[this.topicsFromInput.size()];

        for(int k=0; k< this.topicsFromInputArray.length; k++) {
            //unorderedArray[k] = new Distance(this.topicsFromInputArray[k]);
            this.topicsFromInputArray[k].setCenter(center);
        }

        QuoraData kthSmallestDistance;
        kthSmallestDistance =selectKth(this.topicsFromInputArray,numberToReturn);

        for(int w=0; w<this.topicsFromInputArray.length; w ++) {
            if(this.topicsFromInputArray[w].minDistance<=kthSmallestDistance.minDistance) {
                kSmallestDistances.add(this.topicsFromInputArray[w]);
            }
        }
        Collections.sort(kSmallestDistances);
        if(kSmallestDistances.size()< numberToReturn) {
            numberToReturn = kSmallestDistances.size();
        }
        for(int i=0; i<numberToReturn; i++) {
            returnListOfTopics.add(kSmallestDistances.get(i).id);
        }

        return returnListOfTopics;
    }

    public List<Integer> getClosestQuestions(Integer numberToReturn, Point2D.Double center) {
        List<Integer> returnListOfQuestions = new ArrayList<Integer>();

        List<QuoraData> kSmallestDistances = new ArrayList<QuoraData>();
        Distance[] unorderedArray = new Distance[this.questionsFromInputArray.length];

        for(int k=0; k< this.questionsFromInputArray.length; k++) {
            this.questionsFromInputArray[k].setCenter(center);
        }

        QuoraData kthSmallestDistance;
        kthSmallestDistance =selectKth(this.questionsFromInputArray,numberToReturn);

        for(int w=0; w<unorderedArray.length; w ++) {
            if(this.questionsFromInputArray[w].minDistance<=kthSmallestDistance.minDistance) {
                kSmallestDistances.add(this.questionsFromInputArray[w]);
            }
        }
        Collections.sort(kSmallestDistances);
        if(kSmallestDistances.size()< numberToReturn) {
            numberToReturn = kSmallestDistances.size();
        }
        for(int i=0; i<numberToReturn; i++) {
            returnListOfQuestions.add(kSmallestDistances.get(i).id);
        }

        return returnListOfQuestions;
    }

    public static QuoraData selectKth(QuoraData[] arr, int k) {
        if (arr == null )
            throw new Error();
        if( arr.length <= k)
            k=arr.length-1;
        Integer from = 0, to = arr.length - 1;
        // if from == to we reached the kth element
        while (from < to) {
            Integer r = from, w = to;
            Double mid = arr[(r + w) / 2].minDistance;
            // stop if the reader and writer meets
            while (r < w) {
                if (arr[r].minDistance >= mid) { // put the large values at the end
                    QuoraData tmp = arr[w];
                    arr[w] = arr[r];
                    arr[r] = tmp;
                    w--;
                } else { // the value is smaller than the pivot, skip
                    r++;
                }
            }
            // if we stepped up (r++) we need to step one down
            if (arr[r].minDistance > mid)
                r--;
            // the r pointer is on the end of the first k elements
            if (k <= r) {
                to = r;
            } else {
                from = r + 1;
            }
        }
        return arr[k];
    }

    public static void main(String[] args) throws IOException {
        String[] parameters;
        Integer numTopics;
        Integer numQuestions;
        Integer numQueries;

        Solution sol = new Solution();
        List<String> inputFromQuora = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        inputFromQuora.add(line);
        parameters = line.split(" ");
        try {
            numTopics = Integer.parseInt(parameters[0]);
            numQuestions = Integer.parseInt(parameters[1]);
            numQueries = Integer.parseInt(parameters[2]);
        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException("first line must contain only integers");
        }

        for(int i=0; i<numTopics+numQuestions+numQueries; i++) {
            line = br.readLine();
            inputFromQuora.add(line);
        }

        sol.validateInput(inputFromQuora);
        sol.runQueries();
    }


    public class QuoraData implements Comparable<QuoraData>, Distance{
        Integer id;
        Point2D.Double cords;
        List<QuoraData> otherData;
        Double minDistance;
        //QuoraData dataBeingHeld;
        Point2D.Double center;

        public int compareTo(QuoraData other) {
            if (Math.abs(minDistance-other.minDistance)<=0.001)
                return other.id.compareTo(id);
            return minDistance.compareTo(other.minDistance);
            //return other.id.compareTo(id);
        }
        QuoraData(Integer id, Double x, Double y) {
            this.id = id;
            this.cords = new Point2D.Double(x,y);
        }
        QuoraData(Integer id, List<QuoraData> otherData) {
            this.id = id;
            this.otherData = otherData;
        }

        public void setCenter(Point2D.Double center) {
            this.center = center;
            calculateMinDistance();
        }

        public void cal() {
            Double tempMin;
            Integer totalTopics = otherData.size();
            for(int i = 0; i < totalTopics; i++) {
                //QuoraData data: dataBeingHeld.otherData) {
                tempMin = Math.sqrt((otherData.get(i).cords.getX() - center.getX()) * (otherData.get(i).cords.getX() - center.getX()) + (otherData.get(i).cords.getY() - center.getY()) * (otherData.get(i).cords.getY() - center.getY()));
                if(  this.minDistance == null) {
                    this.minDistance = tempMin;
                } else if(tempMin< minDistance ) {
                    this.minDistance = tempMin;
                }
            }
        }

        public void calculateMinDistance() {
            Double tempMin;
            this.minDistance = null;
            if(otherData == null) {
                this.minDistance = Math.sqrt((cords.getX() - center.getX()) * (cords.getX() - center.getX()) + (cords.getY() - center.getY()) * (cords.getY() - center.getY()))   ;
            } else {
                cal();
            }

        }
    }

    private class Query {
        String type;
        Point2D.Double cords;
        Integer numberOfResultsToReturn;

        Query(String type, Integer numberOfResultsToReturn, Double x, Double y) {
            this.type = type;
            this.numberOfResultsToReturn = numberOfResultsToReturn;
            this.cords = new Point2D.Double(x,y);
        }
    }

    public interface Distance {
        public void setCenter(Point2D.Double center);
        public void cal();
        public void calculateMinDistance();
    }
    /*
    public class Distance implements Comparable<Distance>{
        Double minDistance;
        QuoraData dataBeingHeld;
        Point2D.Double center;

        Distance(QuoraData dataToHold) {
            this.dataBeingHeld = dataToHold;
        }
        public int compareTo(Distance other)
        {
            if (Math.abs(minDistance-other.minDistance)<=0.001)
                return other.dataBeingHeld.id.compareTo(dataBeingHeld.id);
            return minDistance.compareTo(other.minDistance);
        }

        public void setCenter(Point2D.Double center) {
            this.center = center;
            calculateMinDistance();
        }

        private void cal() {
            Double tempMin;
            Integer totalTopics = dataBeingHeld.otherData.size();
            for(int i = 0; i < totalTopics; i++) {
                    //QuoraData data: dataBeingHeld.otherData) {
                tempMin = Math.sqrt((dataBeingHeld.otherData.get(i).cords.getX() - center.getX()) * (dataBeingHeld.otherData.get(i).cords.getX() - center.getX()) + (dataBeingHeld.otherData.get(i).cords.getY() - center.getY()) * (dataBeingHeld.otherData.get(i).cords.getY() - center.getY()));
                if(  this.minDistance == null) {
                    this.minDistance = tempMin;
                } else if(tempMin< minDistance ) {
                    this.minDistance = tempMin;
                }
            }
        }

        private void calculateMinDistance() {
            Double tempMin;
            this.minDistance = null;
            if(dataBeingHeld.otherData == null) {
                this.minDistance = Math.sqrt((dataBeingHeld.cords.getX() - center.getX()) * (dataBeingHeld.cords.getX() - center.getX()) + (dataBeingHeld.cords.getY() - center.getY()) * (dataBeingHeld.cords.getY() - center.getY()))   ;
            } else {
                cal();
            }

        }

    }
    */
}
