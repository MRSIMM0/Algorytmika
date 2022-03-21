import random
import pandas
import quickSort
import time
import sys

sys.setrecursionlimit(1000000000)

def generateRandomTable(n):
    '''generuje losowa tablica'''
    return random.sample(range(1,(n+1)*random.randint(1,10)),n)

def generateRandomTableAndMesureTime(n):
    '''generuje losowa tablica sortuje ja i zwraca czas, tablica nieposortowana i tablica posortowana'''
    s = time.time()
    a = generateRandomTable(n)
    b = a.copy()
    quickSort.quickSort(b)
    e = time.time()
    return([e-s,a,b])


def basicSearch(arr,x):
    '''To nwm czy jest dobrze XD'''
    for j in range(len(arr)):
        if(arr[j] == x):
                return j

def binSearch(arr,low,high,x):
    
    if(high >= low):
        mid = (high + low)//2
        if(arr[mid] == x):
            return mid
        elif (arr[mid] > x):
            return binSearch(arr,low,mid-1,x)
        elif(arr[mid] <x):
            return binSearch(arr,mid+1,high,x)
    else:
        return -1



class Node(object):
    def __init__(self,x):
        self.data = x
        self.left = None
        self.right = None

#to jest to buildBST
def insert(node,data):
    if node == None:
        return Node(data)
    if(data > node.data):
        node.right = insert(node.right,data)
    if(data < node.data):
        node.left = insert(node.left,data)
    return node

def buildBST(arr):
    '''Normalne twoerzenie'''
    root = Node(arr[0])
    for x in range(1,len(arr)-1):
        root = insert(root,arr[x])
    return root

def buildBST2(arr):
    '''Dla posortowanej listy'''
    if not arr:
        return None
    mid = len(arr)//2
  
    node = Node(arr[mid])
    node.left = buildBST2(arr[:mid])
    node.right = buildBST2(arr[mid+1:])

    return node
    

def heightBST(bst):
    '''Wysokość BST'''
    if bst == None: return 0
    else: return 1 + max(heightBST(bst.left), heightBST(bst.right))
                

def TimeSa(arr,sortedArr):
    s = time.time()
    for x in sortedArr:
        basicSearch(arr,x)
    e = time.time()
    return e -s

def TimeSb(arr,sortedArr):
    s = time.time()
    for x in arr:
        binSearch(sortedArr,0,len(sortedArr),x)
    e = time.time()
    return e -s






#To do debugowania

# # def print_tree(root, val="data", left="left", right="right"):
# #     def display(root, val=val, left=left, right=right):
# #         """Returns list of strings, width, height, and horizontal coordinate of the root."""
# #         # No child.
# #         if getattr(root, right) is None and getattr(root, left) is None:
# #             line = '%s' % getattr(root, val)
# #             width = len(line)
# #             height = 1
# #             middle = width // 2
# #             return [line], width, height, middle

# #         # Only left child.
# #         if getattr(root, right) is None:
# #             lines, n, p, x = display(getattr(root, left))
# #             s = '%s' % getattr(root, val)
# #             u = len(s)
# #             first_line = (x + 1) * ' ' + (n - x - 1) * '_' + s
# #             second_line = x * ' ' + '/' + (n - x - 1 + u) * ' '
# #             shifted_lines = [line + u * ' ' for line in lines]
# #             return [first_line, second_line] + shifted_lines, n + u, p + 2, n + u // 2

# #         # Only right child.
# #         if getattr(root, left) is None:
# #             lines, n, p, x = display(getattr(root, right))
# #             s = '%s' % getattr(root, val)
# #             u = len(s)
# #             first_line = s + x * '_' + (n - x) * ' '
# #             second_line = (u + x) * ' ' + '\\' + (n - x - 1) * ' '
# #             shifted_lines = [u * ' ' + line for line in lines]
# #             return [first_line, second_line] + shifted_lines, n + u, p + 2, u // 2

# #         # Two children.
# #         left, n, p, x = display(getattr(root, left))
# #         right, m, q, y = display(getattr(root, right))
# #         s = '%s' % getattr(root, val)
# #         u = len(s)
# #         first_line = (x + 1) * ' ' + (n - x - 1) * '_' + s + y * '_' + (m - y) * ' '
# #         second_line = x * ' ' + '/' + (n - x - 1 + u + y) * ' ' + '\\' + (m - y - 1) * ' '
# #         if p < q:
# #             left += [n * ' '] * (q - p)
# #         elif q < p:
# #             right += [m * ' '] * (p - q)
# #         zipped_lines = zip(left, right)
# #         lines = [first_line, second_line] + [a + u * ' ' + b for a, b in zipped_lines]
# #         return lines, n + m + u, max(p, q) + 2, n + u // 2

# #     lines, *_ = display(root, val, left, right)
# #     for line in lines:
# #         print(line)


CbTime,table,sortedTable = generateRandomTableAndMesureTime(20)


t1 = buildBST2(sortedTable)
t2 = buildBST(table)
        
print(heightBST(t1))
print(heightBST(t2))
print(TimeSa(table,sortedTable))
print(TimeSb(table,sortedTable))







