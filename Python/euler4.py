def palindrome(x):

	num = str(x)
	rev_num = num[::-1]
	
	if num == rev_num:
		return True	
	else:
		return False

dromes = []
		
for x in range(100,999):
	
	for y in range(100,999):
		if palindrome(x*y) == True:
			dromes.append(x*y)
			
dromes.sort()

print dromes
print dromes[len(dromes)-1]