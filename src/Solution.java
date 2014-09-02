import com.sun.corba.se.impl.orbutil.closure.Constant;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Solomon Lasluisa on 8/30/14.
 */
public class Solution {

    private List<Query> queriesFromInput = new ArrayList<Query>();
    Hashtable<Integer, QuoraData> topicsFromInput = new Hashtable<Integer, QuoraData>();
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
            //this.topicsFromInput.add(new Topic(id,x,y));
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
        Integer sumOfNumberOfTopicsForQuestions = 0;
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
                sumOfNumberOfTopicsForQuestions = sumOfNumberOfTopicsForQuestions+numberOfTopics;
                /*
                for (int i = 2; i < splitQuestionLine.length; i++) {
                    try {
                        //topicsForThisQuestion.add(Integer.parseInt(splitQuestionLine[i]));
                        topicForThisQuestion= Integer.parseInt(splitQuestionLine[i]);
                    } catch (NumberFormatException ex) {
                        throw new NumberFormatException("invalid format for number of topic for question");
                    }
                    this.topicsFromInput.get(topicForThisQuestion).questionsForThisTopic.add(new Question(id));

                }
                */
            }
        }

        questionsFromInputArray = new QuoraData[sumOfNumberOfTopicsForQuestions];

        Integer questionsSoFar =0;
        for (String question : inputQuestions) {
            splitQuestionLine = question.split(" ");

            for (int i = 2; i < splitQuestionLine.length; i++) {
                try {
                    //topicsForThisQuestion.add(Integer.parseInt(splitQuestionLine[i]));
                    topicForThisQuestion = Integer.parseInt(splitQuestionLine[i]);
                } catch (NumberFormatException ex) {
                    throw new NumberFormatException("invalid format for number of topic for question");
                }
                id = Integer.parseInt(splitQuestionLine[0]);
                //this.topicsFromInput.get(topicForThisQuestion).questionsForThisTopic.add(new Question(id));
                questionsFromInputArray[questionsSoFar] = new QuoraData(id, this.topicsFromInput.get(topicForThisQuestion).cords.getX(), this.topicsFromInput.get(topicForThisQuestion).cords.getY());
                questionsSoFar++;
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
                //System.out.println(getClosestTopics(query.numberOfResultsToReturn, query.cords).toString().replace("[","").replace("]","").replace(",",""));
                toPrint = getClosestTopics(query.numberOfResultsToReturn, query.cords);
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //long start = System.currentTimeMillis();
                Boolean firstLine = true;
                for (Integer num : toPrint) {
                    try {
                        //out.write("abcdefghijk ");
                        if (firstLine) {
                            out.write(String.valueOf(num));
                            firstLine = false;
                        } else {
                            out.write(" "+String.valueOf(num));
                        }

                        //out.write('\n');
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
                //System.out.println(i.toString() + ":"+getClosestQuestions(query.numberOfResultsToReturn,query.cords).toString().replace("[","").replace("]","").replace(",",""));
                //System.out.println(getClosestQuestions(query.numberOfResultsToReturn,query.cords).toString().replace("[","").replace("]","").replace(",",""));
                toPrint = getClosestQuestions(query.numberOfResultsToReturn,query.cords);
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), "ASCII"), 512);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //long start = System.currentTimeMillis();
                Boolean firstLine = true;
                for (Integer num : toPrint) {
                    try {
                        //out.write("abcdefghijk ");
                        if (firstLine) {
                            out.write(String.valueOf(num));
                            firstLine = false;
                        } else {
                            out.write(" "+String.valueOf(num));
                        }

                        //out.write('\n');
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
                //System.err.println("Loop time: " + (System.currentTimeMillis() - start));
            }
            //i++;
        }
    }

    public List<Integer> getClosestTopics(Integer numberToReturn, Point2D.Double center) {
        List<Integer> returnListOfTopics = new ArrayList<Integer>();
        Distance currentDistance;
        Integer internalMaxNumberToReturn;
        List<Distance> topicsOrderByDistance;

        topicsOrderByDistance = calculateTopicDistances3(center, numberToReturn,false);

        if(topicsOrderByDistance.size()<numberToReturn) {
            internalMaxNumberToReturn=topicsOrderByDistance.size();
        }
        else {
            internalMaxNumberToReturn=numberToReturn;
        }
        for(int i=0; i<internalMaxNumberToReturn; i++)
        {
            currentDistance = topicsOrderByDistance.get(i);
            returnListOfTopics.add(currentDistance.dataBeingHeld.id);
        }
        return returnListOfTopics;
    }

    public List<Integer> getClosestQuestions(Integer numberToReturn, Point2D.Double center) {
        List<Integer> returnListOfQuestions = new ArrayList<Integer>();
        Hashtable<Integer,Integer> returnHashOfQuestions = new Hashtable<Integer,Integer>();
        Distance currentDistance;
        QuoraData currentQuestion;
        Integer internalMaxNumberToReturn;
        List<Distance> questionsOrderByDistance;

        questionsOrderByDistance = calculateTopicDistances3(center, numberToReturn, true);

        if(questionsOrderByDistance.size()<numberToReturn) {
            internalMaxNumberToReturn=questionsOrderByDistance.size();
        }
        else {
            internalMaxNumberToReturn=numberToReturn;
        }
        for(int i=0; i<internalMaxNumberToReturn; i++)
        {
            currentDistance = questionsOrderByDistance.get(i);
            if(!returnHashOfQuestions.containsKey(currentDistance.dataBeingHeld.id)) {
                returnListOfQuestions.add(currentDistance.dataBeingHeld.id);
                returnHashOfQuestions.put(currentDistance.dataBeingHeld.id,1);
            }
            //returnListOfQuestions.add(currentDistance.dataBeingHeld.id);
        }
        return returnListOfQuestions;
    }

    public Distance[] getUnorderedArrayOfTopics(Point2D.Double center, Boolean isAQuestion) {
        Double calculatedDistance;
        Distance[] unorderedArray;// = new Distance[this.topicsFromInput.size()];
        Hashtable<Integer, Distance> hashTableOfMinDistancesForQuestion = new Hashtable<Integer, Distance>();
        Integer dataAddSoFar=0;
        Enumeration<Integer> enumKey;

        QuoraData topic;
        QuoraData question;

        if(isAQuestion) {
            unorderedArray = new Distance[this.questionsFromInputArray.length];
            for(int k=0; k< this.questionsFromInputArray.length; k++) {
                //only get min for questionl
                question = this.questionsFromInputArray[k];
                calculatedDistance = Math.sqrt((question.cords.getX() - center.getX()) * (question.cords.getX() - center.getX()) + (question.cords.getY() - center.getY()) * (question.cords.getY() - center.getY()));
                if(hashTableOfMinDistancesForQuestion.containsKey(question.id)) {
                    if(hashTableOfMinDistancesForQuestion.get(question.id).distance > calculatedDistance) {
                        hashTableOfMinDistancesForQuestion.put(question.id, new Distance(calculatedDistance, question));
                    }
                } else {
                    hashTableOfMinDistancesForQuestion.put(question.id, new Distance(calculatedDistance, question));
                }
                //unorderedArray[i] = new Distance(calculatedDistance, question);
                //i++;

            }
            enumKey = hashTableOfMinDistancesForQuestion.keys();
            while(enumKey.hasMoreElements()) {
                Integer key = enumKey.nextElement();
                Distance val = hashTableOfMinDistancesForQuestion.get(key);
                unorderedArray[dataAddSoFar]=val;
                dataAddSoFar++;
            }
        } else {
            unorderedArray = new Distance[this.topicsFromInput.size()];
            for(int k=0; k< this.topicsFromInputArray.length; k++) {
                topic = this.topicsFromInputArray[k];
                calculatedDistance = Math.sqrt((topic.cords.getX() - center.getX()) * (topic.cords.getX() - center.getX()) + (topic.cords.getY() - center.getY()) * (topic.cords.getY() - center.getY()));
                unorderedArray[dataAddSoFar] = new Distance(calculatedDistance, topic);
                dataAddSoFar++;
            }
        }
        return  Arrays.copyOf(unorderedArray,dataAddSoFar);
    }

    public List<Distance> calculateTopicDistances3(Point2D.Double center, Integer kthSmallest,Boolean question) {
        List<Distance> returnList = new ArrayList<Distance>();
        Distance[] unorderedArray = new Distance[this.topicsFromInput.size()];
        Distance kthSmallestDistance;
        unorderedArray = getUnorderedArrayOfTopics(center,question);
        kthSmallestDistance =selectKth(unorderedArray,kthSmallest);

        for(int w=0; w<unorderedArray.length; w ++) {
            if(unorderedArray[w].distance<=kthSmallestDistance.distance) {
                returnList.add(unorderedArray[w]);
            }
        }
        Collections.sort(returnList);
        return returnList;
    }


    public static Distance selectKth(Distance[] arr, int k) {
        if (arr == null )
            throw new Error();
        if( arr.length <= k)
            k=arr.length-1;
        Integer from = 0, to = arr.length - 1;
        // if from == to we reached the kth element
        while (from < to) {
            Integer r = from, w = to;
            Double mid = arr[(r + w) / 2].distance;
            // stop if the reader and writer meets
            while (r < w) {
                if (arr[r].distance >= mid) { // put the large values at the end
                    Distance tmp = arr[w];
                    arr[w] = arr[r];
                    arr[r] = tmp;
                    w--;
                } else { // the value is smaller than the pivot, skip
                    r++;
                }
            }
            // if we stepped up (r++) we need to step one down
            if (arr[r].distance > mid)
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


    public class QuoraData implements Comparable<QuoraData>{
        Integer id;
        Point2D.Double cords;
        public int compareTo(QuoraData other) {
            return other.id.compareTo(id);
        }
        QuoraData(Integer id, Double x, Double y) {
            this.id = id;
            this.cords = new Point2D.Double(x,y);
        }
    }
/*
    public class Topic {
        Point2D.Double cords;
        Integer id;
        List<Question> questionsForThisTopic = new ArrayList<Question>();
        Topic(Integer id, Double x, Double y) {
            this.id = id;
            this.cords = new Point2D.Double(x,y);
        }
    }

    private class Question implements Comparable<Question>{
        Integer id;
        Question(Integer id) {//, List<Integer> topicIds) {
            this.id = id;
        }
        public int compareTo(Question other)
        {
            return other.id.compareTo(id);
        }
    }
*/
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

    public class Distance implements Comparable<Distance>{
        Double distance;
        QuoraData dataBeingHeld;

        Distance(Double distance, QuoraData dataToHold) {
            this.distance =distance;
            this.dataBeingHeld = dataToHold;
        }
        public int compareTo(Distance other)
        {
            if (Math.abs(distance-other.distance)<=0.001)
                return other.dataBeingHeld.id.compareTo(dataBeingHeld.id);
            return distance.compareTo(other.distance);
        }

    }
}
