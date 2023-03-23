import time

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

n = 7


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

