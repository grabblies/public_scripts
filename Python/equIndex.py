

def equIndex(A):
	#print "Original array:			" + str(A)			#this is the array in question
	length = len(A)				#intializing length
	total_in_A = 0 				#initializing total
	total_from_right = 0		#total from right
	prog_sums = [0]*length		#sums of array elements up to index
	prog_sums_from_r = [0]*length

	equilPnts = []

	#print "length:		" + str(length)

	for i in range(0,length):
		total_in_A += A[i]
		total_from_right += A[length-1-i]
		prog_sums[i] = total_in_A
		prog_sums_from_r[length-1-i] = total_from_right

	for i in range(0,length):

		if i == 0: 
		#if i is first element, then the remaining elements must sum to 0
			if prog_sums_from_r[i+1] == 0:
				equilPnts.append(i)
		if i == length-1:
		#if i is the last element, then the elements up to that must sum to 0
			if prog_sums[i-1] == 0:
				equilPnts.append(i)
		else:
		#if i is in the middle somewhere, then previous elements must sum to the same as elements behind
			if prog_sums[i-1] == prog_sums_from_r[i+1]:
				equilPnts.append(i)


	if len(equilPnts) == 0:
		print -1
		return -1

	else:
		print equilPnts
		return equilPnts



B = [-1,3,-4,5,1,-6,2,1]
C = [-7,1,5,2,-4,3,0]
equIndex(B)
equIndex(C)
