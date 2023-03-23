import time
import multiprocessing
import math

is_prime_flag = False

def is_prime(n):
    if n <= 1:
        is_prime_flag = False
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            is_prime_flag = False
            return False
    return True

n = 7
number_of_threads = 2

def check_for_prime_in_range(start, end):
    for n in range(start, end):
        if is_prime(n):
            is_prime_flag = True


with open("Times.txt", "a") as file:
    for i in range(1):
        start_time = time.time()
        
        process_1 = multiprocessing.Process(target = check_for_prime_in_range(2, math.ceil(n/number_of_threads)))
        process_2 = multiprocessing.Process(target = check_for_prime_in_range(math.floor(n/number_of_threads), n))
        process_1.start()
        process_2.start()
        process_1.join()
        process_2.join()

        if is_prime_flag:
            print(f"{n} is a prime number")
        else:
            print(f"{n} is not a prime number")

        time_taken = time.time() - start_time
        print(f"Time: {time_taken:.2f} seconds")
        file.write(f"Run {i+1}, {time_taken:.2f} seconds, Version 2 with 1 Processor and 2 threads\n")

file.close()

