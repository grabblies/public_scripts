import math
import time



def solution(A):

	length = len(A)
	A_sort = sorted(A)
	gaps = []

	print "range of y: " + str(range(min(A_sort), max(A_sort)))
	print "sorted array: " + str(A_sort)


	for y in range(min(A_sort), max(A_sort)):
		
		find_closest(5,A_sort)


	print gaps
	print max(gaps)
	return max(gaps)

def find_closest(y,array):
	print "making it to recursion"
	length = len(array)
	print "length: " + str(length)
	print "array: " + str(array)
	print "searching for: " + str(y)


	if y == array[0]:
		print "y: (as equal)" + str(y)
		print "eval. as == to first"
		return 0

	if y == array[length-1]:
		print "y: (as equal) " + str(y)
		print "eval. as == to last"
		return 0

	if length <3:
		print "y: " + str(y)
		print "eval. as length being small"
		return min(abs(array[0]-y),abs(array[1]-y))

	b = int(math.floor(length/2))
	print "cut-off point b is: " + str(b)

	if y < array[b]:
		print "y: (as less)" + str(y)
		#print "mid val: " + str(array(b))
		print "eval. as y being less than middle"
	# if y is less than the half-way value, then search in the bottom half
		return find_closest(y, array[0:b])

	if y > array[b]:
	# if y is greater than the half-way value, then search in the top half
		print "y: (as greater)" + str(y)
		print "mid val: " + str(array(b))
		print "eval. as y being greater than middle"		
		return find_closest(y, array[b+1:length-1])






C = [10, 0, 8, 2, -1, 12, 11, 3]
solution(C)
#solution(C)
