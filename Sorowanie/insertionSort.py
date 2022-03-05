name ="InsertionSort"

def func(arr):
    for i in range(len(arr)):
        #wybieranie pierwszego elementu nieposortowanej listy
        key = arr[i]
        j = i-1
        #porownywanie elementow
        while j>=0 and key<arr[j]:
            #przesueniecie tablicy
            arr[j+1] = arr[j]
            j-=1
        #zmiana klucza na koleny element
        arr[j+1] =key
