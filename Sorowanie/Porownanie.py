import time
import insertionSort,bobbleSort,selectionSort
import random
from prettytable import PrettyTable
tId = time.CLOCK_PROCESS_CPUTIME_ID


def getTime(func):
    """
    Funkcja zwraca czas wykonania danego algorytmu w sekunach
    """
    start = time.clock_gettime(tId)
    func()
    end = time.clock_gettime(tId)
    return end-start

def generateRandomSet(n):
    """
    Generuje losowy zestaw danych
    """
    return random.sample(range(1,n+1),n)

def compare(listOfAlgorithms,testCases,setRange,multiply):
    """
    Funkcja przyjmue liste algorytmow ,zakres tablicy testowaj, ilosc testow i porownuje je i zwraca w formie dict
    """
    result ={"numebrOfTest":testCases,"tests":{}}
    for x in range(1,testCases+1):
        
        set = generateRandomSet(setRange)
      
        algs  = [(alg.name,getTime(lambda: alg.func)) for alg in listOfAlgorithms]
        try:
            result["tests"][x].append(algs)
        except:
            result["tests"][x] = {"setSize":setRange,"algorithms":algs}
        setRange = setRange*multiply
    return result
def showResultsTable(data,algorightms):
    """
    Wyswietla wyniki
    """
    x = PrettyTable()

    headers = ["TestCase", "SetSize"]

    for a in algorightms:
        headers.append(a.name)
    x.field_names = headers
 
    for d in data["tests"]:
        dat = [d, data["tests"][d]["setSize"]]
        for l in range(len(data["tests"][d]['algorithms'])):
          dat.append(data["tests"][d]['algorithms'][l][1])
        x.add_row(dat)
    return x




def main():
    algorightms = [insertionSort,bobbleSort,selectionSort]
    print(showResultsTable(compare(algorightms,15,100,2),algorightms))
    
# getTime(lambda: insertionSort.insertionSort(arr))
main()


