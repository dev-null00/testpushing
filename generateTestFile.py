#!/usr/bin/python
from random import randrange
topics=randrange(10000)+1
#topics=randrange(5000)+5000
questions=randrange(1000)+1
#questions=randrange(500)+500
queries=randrange(5000)+1
#queries=randrange(2500)+2500
print str(topics) + " " +str(questions) + " " +str(queries*2)
for x in range(0, topics):
	print str(x) + " " + str(randrange(10000000000)/10000.0) + " " + str(randrange(10000000000)/10000.0)

for x in range(0, questions):
	numTopics=randrange(10)+1
	topicForQuestion=""
	if numTopics > 0:
		for w in range(0, numTopics):
			topicForQuestion = topicForQuestion +" " +str(randrange(topics))
        print str(x) + " "+ str(numTopics) + topicForQuestion
	
for x in range(0, queries):
        print "q "+ str(randrange(100)+1)+" "+  str(randrange(10000000000)/10000.0) + " " + str(randrange(10000000000)/10000.0)
        print "t "+ str(randrange(100)+1)+" "+  str(randrange(10000000000)/10000.0) + " " + str(randrange(10000000000)/10000.0)
