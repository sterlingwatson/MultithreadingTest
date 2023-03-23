import time

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

# Hard-coded value for n (a prime number)
n = 3761876186350270487

start_time = time.time()

# Check if n is prime
if is_prime(n):
    print(f"{n} is a prime number")
else:
    print(f"{n} is not a prime number")

elapsed_time = time.time() - start_time
print(f"Elapsed time: {elapsed_time:.2f} seconds")

