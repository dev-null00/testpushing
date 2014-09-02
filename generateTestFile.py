#!/usr/bin/python
from random import randrange
topics=randrange(10)+1
questions=randrange(10)+1
queries=randrange(5)+1
print str(topics) + " " +str(questions) + " " +str(queries*2)
for x in range(0, topics):
	print str(x) + " " + str(randrange(1000000)) + " " + str(randrange(1000000))

for x in range(0, questions):
	numTopics=randrange(10)+1
	topicForQuestion=""
	if numTopics > 0:
		for w in range(0, numTopics):
			topicForQuestion = topicForQuestion +" " +str(randrange(topics))
        print str(x) + " "+ str(numTopics) + topicForQuestion
	
for x in range(0, queries):
        print "q "+ str(randrange(100)+1)+" "+  str(randrange(1000000)) + " " + str(randrange(1000000))
        print "t "+ str(randrange(100)+1)+" "+  str(randrange(1000000)) + " " + str(randrange(1000000))
