import time
import math

def is_prime(n):
    sqrt_n = int(math.ceil(math.sqrt(n)))
    for i in range(2, sqrt_n + 1):
        if n % i == 0:
            return False
    return True

n = 483463113173934329 


with open("Times.txt", "a") as file:
    for i in range(5):
        start_time = time.time()

        if is_prime(n):
            print(f"{n} is a prime number")
        else:
            print(f"{n} is not a prime number")

        time_taken = time.time() - start_time
        print(f"Time: {time_taken:.2f} seconds")
        file.write(f"Run {i+1}, {time_taken:.2f} seconds, Version 1 with 1 Processor\n")


file.close()

