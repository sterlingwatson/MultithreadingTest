import time
import multiprocessing
import math

#n = 558533
#n = 483463113173934329
n=13
sqrt_n = int(math.ceil(math.sqrt(n)))

def is_prime(n):
    for i in range(2, sqrt_n + 1):
        if n % i == 0:
            return False
    return True

number_of_threads = 2
results = multiprocessing.Manager().list()
answers =[]

def check_for_prime_in_range(start, end, check):
    for n in range(start, end):
        if is_prime(n):
            check.append(n)


with open("Times.txt", "a") as file:
    for y in range(5):
        start_time = time.time()
        
        for j in range(number_of_threads):
            start = j * (math.ceil(n/ number_of_threads))
            end = (j+1) * (math.ceil(n/number_of_threads))
            if j == number_of_threads - 1:
                end = n
            process = multiprocessing.Process(target = check_for_prime_in_range, args =  (start, end, results))
            process.start()

        #wait for children to die
        for p in  multiprocessing.active_children():
            process.join()

        for r in results:
            answers.append(r)
            #print(f"{answers}")

    if n in answers or is_prime(n): #this is dumb, but I had constant bugs around it not including n in answers
            print(f"{n} is a prime number")
    else:
            print(f"{n} is not a prime number")
            
    time_taken = time.time() - start_time
    print(f"Time: {time_taken:.2f} seconds")
    #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 1 Processor and 2 threads\n")

    #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 2 Processor and 2 threads\n")
    #file.write(f"Run {y+1}, {time_taken:.2f} seconds, Version 2 with 3 Processor and 3 threads\n")


file.close()

