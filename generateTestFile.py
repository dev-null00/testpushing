#!/usr/bin/python
from random import randrange
print "10000 1000 10000"
for x in range(0, 10000):
	print str(x) + " " + str(randrange(1000000)) + " " + str(randrange(1000000))

for x in range(0, 1000):
	numTopics=randrange(10)+1
	topicForQuestion=""
	if numTopics > 0:
		for w in range(0, numTopics):
			topicForQuestion = topicForQuestion +" " +str(randrange(10000))
        print str(x) + " "+ str(numTopics) + topicForQuestion
	
for x in range(0, 10000):
        print "q 100 " + str(randrange(1000000)) + " " + str(randrange(1000000))
