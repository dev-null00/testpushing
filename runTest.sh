#!/bin/bash
COUNTER=0
while [  $COUNTER -lt 10 ]; do
	./generateTestFile.py >4testInput.txt
	echo "java"
	java -jar ./out/artifacts/NearbyChallenge_jar/NearbyChallenge.jar < 4testInput.txt  >output.txt
	echo 
	echo "python"
	../nearby/nearby.py < 4testInput.txt >output2.txt
	#diff output.txt output2.txt || echo "what"
	diff output.txt output2.txt || cp 4testInput.txt ./differentAnswer/$COUNTER.txt
        let COUNTER=COUNTER+1 
	echo 
done
