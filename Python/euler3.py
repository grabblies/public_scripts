def factorable(num):
	high = num
	low = 2
	factorable = False
	
	if num % 2 == 0:
		return True	
	elif num % 5 == 0:
		return True	
	else:	
		while low < high:
			if num % low == 0:
				factorable = True
			low += 1
			high = int(num/low)
	return factorable



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
	
		
print factor_primes(13195)
print factor_primes(600851475143)
