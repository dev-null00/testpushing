#!/bin/bash
java -jar ./out/artifacts/NearbyChallenge_jar/NearbyChallenge.jar < specfic.txt  >output.txt
../nearby/nearby.py < specfic.txt >output2.txt
diff output.txt output2.txt

