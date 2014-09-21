def divisible_by_1_20():
	x = 20
	count = 0
	div = False
	numbers = [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
	print len(numbers)
	
	while div == False:
		
		for i in numbers:
			if x % i != 0:
				break
			else:
				count += 1
			
		
		print count
		
		if count == len(numbers):
			div = True
		else:
			count = 0
			x += 20
	
	print x
	
	
		
divisible_by_1_20()