def factorable(num):
	high = num
	low = 3
	factorable = False
	
	if num > 5:
		if num % 2 == 0:
			return True	
		elif num % 5 == 0:
			return True
			
	while low < high:
		if num % low == 0:
			factorable = True
		low += 2
		#high = int(num/low)
	return factorable

	
def count_primes(num_prime):
	primes = 0
	value = 2
	primes_array = []
	
	while primes < num_prime + 1:
		if factorable(value) == True:
			value += 1
		else:
			print str(value) + ", ",
			primes_array.append(value)
			value += 1
			primes += 1
			
	print " "
		
	print primes_array[primes-1]

def factor_primes(x):
	high = x
	low = 2
	primes = []
	
	while low < high:
		if x % low == 0:
			if factorable(low) == False:
				primes.append(low)
			if factorable(x/low) == False:
				primes.appent(x/low)
		low += 1
		high = int(x/low)
	print primes
	
		
count_primes(10001)