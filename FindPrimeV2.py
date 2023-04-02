import time
import multiprocessing 
import math

#n= 558533
n = 483463113173934329
#n=13
#n=1500450271
#n=113
#n=1117
#primes = multiprocessing.Queue()
primes = []


def is_prime(n):
    for i in range(2, int(math.sqrt(n)) + 1):
        if (n % i) == 0:
            return False
    #print(f'{n} is prime')
    return True
       

def check_for_prime_in_range(start, end):
    for n in range(start, end):
        if is_prime(n):
            primes.append(n)


def printNumber(start, end):
    for n in range(start, end):
        print(f'checking number is {n}')


if __name__ == '__main__':
    ans = []
    number_of_threads = 2
    with open("Times.txt", "a") as file:
        for y in range(1):
            start_time = time.time()

        with multiprocessing.Pool(2) as pool:

            for j in range(number_of_threads):
                start = j * (math.ceil(n/number_of_threads))
                print(f'start {j} is {start}')
                end = (j+1) * (math.ceil(n/number_of_threads))
                print(f'end {j} is {end}')

                pool.map_async(check_for_prime_in_range(start, end), range(start,end))

                #process = multiprocessing.Process(target=check_for_prime_in_range, args= (start, end))
                #for p in multiprocessing.active_children():
                #    print(f'active children before start {p.name}')
                #process = multiprocessing.Process(target=printNumber, args= (start, end))
                #process.start()
                #for p in multiprocessing.active_children():
                #    print(f'active children after start {p.name}')
                


        #for p in multiprocessing.active_children():
            #process.join()



            #with multiprocessing.Pool(2) as pool:
             #   searchSpace = math.ceil(n/number_of_threads)
              #  answers = pool.map_async(is_prime, range(n+1))
              #  pool.close()
               # pool.join()
            #while not primes.empty():
             #   num = primes.get()
              #  ans.append(num)


            #if n in ans: #or is_prime(n): #this is dumb, but I had constant bugs around it not including n in answers
               # print(f"{n} is a prime number")
            #else:
                #print(f"{n} is not a prime number"
            if primes.__contains__(n):
                print(f'{n} is prime')
        time_taken = time.time() - start_time
        print(f"Time: {time_taken:.2f} seconds")
              #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 1 Processor and 2 threads\n")
             #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 2 Processor and 2 threads\n")
             #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 3 Processor and 3 threads\n")


file.close()

