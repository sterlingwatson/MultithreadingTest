import math
import time

start_time = time.time()
n = 9868045784972602240730631194380879408294672286564976063774888099602859710393333183174649996662674633
sqrt_n = int(math.ceil(math.sqrt(n)))
for i in range(2, sqrt_n+1):
    if n % i == 0:
        print( "%s is not a prime " % (n,))
        break

    else:
        print("%s is a prime" % (n,))
    end_time = time.time()
    print ("This took %.2f seconds" % (end_time - start_time))
    break
