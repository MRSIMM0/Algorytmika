name = "BubbleSort"
def func(arr):
    n = len(arr)
    #iteracja po wszystkich elementach tablicy
    for i in range(n-1):
        #iteracja po elementach nieposortowanych
        for j in range(0,n-i-1):
            #zamiana elementow tablicy
            if arr[j] > arr[j+1]:
                arr[j],arr[j+1] = arr[j+1],arr[j]
