import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

  /*  @org.junit.Test
    public void testMain() throws Exception {
    }
    */

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @org.junit.Test
    public void testForNumberOfParamters() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("first line must contain 3 integers: number of topics T, number of questions Q, and number of queries N.");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("hello ");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testForIntegersInFirstLine() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("first line must contain only integers");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("a b c");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testTotalNumberOfLine() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, and Queries does not add up in this input");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 1 1");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1 1");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("10001 1 1");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput3() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 0 1");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput4() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 1001 1");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput5() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 1 0");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testBoundariesOfInput6() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("the number of Topics, Questions, or Queries are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 1 10001");
        testInput.validateInput(input);
    }

    @org.junit.Test
    public void testValidateTopicsFormat() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("topic has invalid format for topics: id x y");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1 10001");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testValidateTopicsFormat2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("topic has invalid format for topics: id x y");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("1");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testValidateTopicsFormat3() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("topic has invalid format for coordinates");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1 s");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testValidateTopicsFormat4() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("topic has invalid format for coordinates");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 s 1");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testValidateTopicsFormat5() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("invalid format for topic id");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("a 1 1");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 -1 1");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1 -1");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsBounds3() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1000001 0");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsBounds4() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 0 1000001");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsIdBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("topic id is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("-1 0 0");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testTopicsIdBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("topic id is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("100001 0 0");
        testInput.validateTopics(input);
    }

    @org.junit.Test
    public void testSuccessfulTopicsFormat1() throws Exception {
        Boolean expectedValue = true;
        Boolean returnedValue;
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1.9000000000000000000003 1");
        returnedValue = testInput.validateTopics(input);
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testSuccessfulTopicsFormat2() throws Exception {
        Boolean expectedValue = true;
        Boolean returnedValue;
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 123.434543 1.9000000000000000000003");
        returnedValue = testInput.validateTopics(input);
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testQuestionInputBound1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("question has invalid format: id numberOfTopics topic1 ... topicN. Note N up to 10");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionInputBound2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("question has invalid format: id numberOfTopics topic1 ... topicN. Note N up to 10");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 10 1 2 3 4 5 6 7 8 9 10 11");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionValidIdFormat() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("invalid format for question id");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("s 10 1 2 3 4 5 6 7 8 9 10");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionValidNumberOfQuestionsFormat() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("invalid format for number of topic for question");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 a 1 2 3 4 5 6 7 8 9 10");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionIdBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("question id is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("-1 0 0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionIdBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("question id is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("100001 0 0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionNumTopicsBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("number of topics for question is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 -1 0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionNumTopicsBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("number of topics for question is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 11 0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQuestionLineExpectedCount() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("question line does not match expected count");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 10 0");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testTopicsForQuestionFormat() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("invalid format for number of topic for question");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1 a");
        testInput.validateQuestions(input);
    }

    @org.junit.Test
    public void testQueriesForFormat1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("query has invalid format for topics: queryType numberOfResultsToReturn x y");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 1 a");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueriesForFormat2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("invalid query type");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("w 0 1 a");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueriesForFormat3() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("invalid query type");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("0 0 1 a");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueriesForFormat4() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("invalid format for number of results to return");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t a 1 a");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueriesForFormat5() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("query has invalid format for coordinates");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 1 s");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueriesForFormat6() throws Exception {
        expectedEx.expect(NumberFormatException.class);
        expectedEx.expectMessage("query has invalid format for coordinates");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 s 1");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryCoordinateBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 -1 1");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryCoordinateBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 1 -1");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryCoordinateBounds3() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 1000001 0");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryCoordinateBounds4() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("coordinates are out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 0 0 1000001");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryNumReturnResultsBounds1() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("number of results to return is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t -1 0 0");
        testInput.validateQueries(input);
    }

    @org.junit.Test
    public void testQueryNumReturnResultsBounds2() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("number of results to return is out of bounds");
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("t 101 0 0");
        testInput.validateQueries(input);
    }

    //TODO create test for what queries, questions, and topics should look like

    @org.junit.Test
    public void testCloseTopic() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(0);
        expectedValue.add(1);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("t 2 0.0 0.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestTopics(2, new Point2D.Double(0.0,0.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseTopic6() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(8);
        expectedValue.add(2);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("10 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 10. 10.");
        input.add("4 60. 60.");
        input.add("5 3. 4.");
        input.add("6 7. 8.");
        input.add("7 100. 20.");
        input.add("8 2. 2.");
        input.add("9 4. 6.");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 1 1. 1.");
        input.add("t 2 2. 2.");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestTopics(2, new Point2D.Double(2.,2.) );
        assertEquals(expectedValue,returnedValue);
    }


    @org.junit.Test
    public void testCloseQuestion1() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        expectedValue.add(2);
        expectedValue.add(1);
        expectedValue.add(0);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 5 100.0 100.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(5, new Point2D.Double(100.0,100.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseQuestion2() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        expectedValue.add(2);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 2 100.0 100.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(2, new Point2D.Double(100.0,100.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseQuestion3() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        expectedValue.add(2);
        expectedValue.add(1);
        expectedValue.add(3);
        expectedValue.add(0);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 1 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 2 100.0 100.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(5, new Point2D.Double(100.0,100.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseQuestion4() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        expectedValue.add(2);
        expectedValue.add(1);
        expectedValue.add(3);
        expectedValue.add(0);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 1 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 2 100.0 100.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(5, new Point2D.Double(100.0,100.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseQuestion5() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        expectedValue.add(2);
        expectedValue.add(1);
        expectedValue.add(3);
        expectedValue.add(0);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 1");
        input.add("0 0.25 0.5");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 1 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 2 100.0 100.0");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(5, new Point2D.Double(100.0,100.0) );
        assertEquals(expectedValue,returnedValue);
    }

    @org.junit.Test
    public void testCloseQuestion6() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(5);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("10 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 10. 10.");
        input.add("4 60. 60.");
        input.add("5 3. 4.");
        input.add("6 7. 8.");
        input.add("7 100. 20.");
        input.add("8 2. 2.");
        input.add("9 4. 6.");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("q 1 1. 1.");
        input.add("t 2 2. 2.");
        testInput.validateInput(input);
        returnedValue=testInput.getClosestQuestions(1, new Point2D.Double(1.,1.) );
        assertEquals(expectedValue,returnedValue);
    }


    @org.junit.Test
    public void testEndToEnd() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(0);
        expectedValue.add(1);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("5 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("3 3.0 3.0");
        input.add("4 4.0 4.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("t 2 0.0 0.0");
        input.add("q 5 100.0 100.0");
        testInput.validateInput(input);
        testInput.runQueries();
    }


    @org.junit.Test
    public void testEndToEnd2() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(0);
        expectedValue.add(1);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("3 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("t 2 0.0 0.0");
        input.add("q 5 100.0 100.0");
        testInput.validateInput(input);
        testInput.runQueries();
    }


    @org.junit.Test
    public void testEndToEnd3() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(0);
        expectedValue.add(1);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("4 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.00141471435 2.0");
        input.add("3 2 2");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 1 3");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("t 2 0.0 0.0");
        input.add("q 5 3.0 3.0");
        testInput.validateInput(input);
        testInput.runQueries();
    }

    @org.junit.Test
    public void testEndToEnd4() throws Exception {
        List<Integer> expectedValue = new ArrayList<Integer>();
        List<Integer>  returnedValue;
        expectedValue.add(0);
        expectedValue.add(1);
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        input.add("3 6 2");
        input.add("0 0.0 0.0");
        input.add("1 1.0 1.0");
        input.add("2 2.0 2.0");
        input.add("0 1 0");
        input.add("1 2 0 1");
        input.add("2 3 0 1 2");
        input.add("3 0");
        input.add("4 0");
        input.add("5 2 1 2");
        input.add("t 5 0.0 0.0");
        input.add("q 5 100.0 100.0");
        testInput.validateInput(input);
        testInput.runQueries();
    }

    @org.junit.Test
    public void testStressEndToEnd1() throws Exception {
        Boolean expectedValue = true;
        Boolean returnedValue;
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("2testInput.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            input.add(line);
        }
        br.close();
        returnedValue = testInput.validateInput(input);
        testInput.runQueries();
    }

    @org.junit.Test
    public void testStressEndToEnd2() throws Exception {
        Boolean expectedValue = true;
        Boolean returnedValue;
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("3testInput.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            input.add(line);
        }
        br.close();
        returnedValue = testInput.validateInput(input);
        testInput.runQueries();
    }

    @org.junit.Test
    public void testStressEndToEnd3() throws Exception {
        Boolean expectedValue = true;
        Boolean returnedValue;
        Solution testInput = new Solution();
        List<String> input = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("specfic.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            input.add(line);
        }
        br.close();
        returnedValue = testInput.validateInput(input);
        testInput.runQueries();
    }

    @org.junit.Test
    public void testStressEndToEnd4() throws Exception {
        Process p;
        StringBuffer output = new StringBuffer();
        Solution testInput;
        List<String> input;
        BufferedReader br;
        String line;
        for(int i =0 ; i< 10; i++) {
            try {
                p = Runtime.getRuntime().exec("./gen.sh");
                p.waitFor();

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));

                line = "";
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Boolean expectedValue = true;
            Boolean returnedValue;
            testInput = new Solution();
            input = new ArrayList<String>();
            br = new BufferedReader(new FileReader("6testInput.txt"));
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            br.close();
            returnedValue = testInput.validateInput(input);
            testInput.runQueries();
        }
    }

}
