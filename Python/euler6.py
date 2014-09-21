def summing():
	sum_of_squares = 0
	square_of_sums = 0
	
	for i in range(101):
		print str(i) + " - " + str(i**2)
		square_of_sums += i
		sum_of_squares += (i**2)
		
		
	print square_of_sums
	print square_of_sums**2
	print sum_of_squares
	square_of_sums = square_of_sums**2
	
	print square_of_sums - sum_of_squares
	
	
summing()
	
	