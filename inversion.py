
def Isort(alist):
	#print("Splitting ",alist)
	if len(alist)>1:
		mid=len(alist)//2
		lefthalf=alist[:mid]
		righthalf=alist[mid:]
		Isort(lefthalf) #first half
		Isort(righthalf) #second half

		i=0
		j=0
		k=0
		global count
		#print lefthalf
	 	while i < len(lefthalf) and j < len(righthalf):
	 		if lefthalf[i] < righthalf[j]:
	 			alist[k]=lefthalf[i]
	 			i=i+1
	 		else:
	 			count = count + (len(lefthalf) - i)
	 			alist[k]=righthalf[j]
	 			j=j+1
	 		k=k+1
	 	while i < len(lefthalf):
	 		alist[k]=lefthalf[i]
	 		i=i+1
	 		k=k+1
	 	while j < len(righthalf):
	 		alist[k]=righthalf[j]
	 		j=j+1
	 		k=k+1
	#print("merging",alist)
def main():
	filename="/home/shiqi/Desktop/IntegerArray.txt"
	lineA = []
	with open(filename) as f:
		# read rest of lines
		for line in f:
			lineA.append(int(line.rstrip()))
			
	
	#print lineA
	f.close()
	
	#print(lineA)
	#alist = [18, 22, 4, 29, 15, 21, 13, 24, 20, 10, 11, 26, 27, 16, 12, 8, 25, 14, 6, 17, 30, 9, 28, 5, 2, 1, 23, 7, 19, 3 ]
	#print(lineA)
	#print(lineA)
	Isort(lineA)
	print(count)
	#print(lineA)

if __name__ == '__main__':
	count=0
	main()