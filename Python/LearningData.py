##problem 1
"""
sum=0

for n in range(0,1000):
	
	if n%3==0 or n%5==0:
		sum += n
		n += 1
	else:
		n += 1
	
print sum
"""
##problem 2
sum = 2
first = 1
second = 2
print "1, 2, "
next = 0

while next < 4000001:
	
	next = first + second
	first = second
	second = next
	#print str(next)
	
	if next % 2 == 0:
		sum += next
		
print sum
		