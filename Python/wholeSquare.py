import math
# you can use print for debugging purposes, e.g.
# print "this is a debug message"

def solution(A, B):
    # write your code in Python 2.7
    count = 0

    for x in range(A,B+1):
    	if math.floor(math.sqrt(x)) == math.ceil(math.sqrt(x)):
    		count +=1
    		#print x


    #print count
    return count
    pass


solution(4,17)