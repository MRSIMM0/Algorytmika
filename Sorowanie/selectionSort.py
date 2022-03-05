name = "SelectionSort"
def func(arr):
    for i in range(len(arr)):   
        minIndex = i
        #znajdowanie najmiejszego indexu
        for j in range(i+1, len(arr)):
            if(arr[minIndex]> arr[j]):
                minIndex = j
        #zamina elementu najmiejszego z elementem na pierwszej pozycji nieposortowanej listy
        arr[i],arr[minIndex] = arr[minIndex],arr[i]

