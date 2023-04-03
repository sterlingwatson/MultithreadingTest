#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>

bool is_prime(unsigned long long n) {
    int sqrt_n = (int)ceil(sqrt(n));
    for (int i = 2; i <= sqrt_n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    long long n = 8733997939747861159;

    FILE *file = fopen("Times.txt", "a");
    for (int i = 0; i < 5; i++) {
        clock_t start_time = clock();

        if (is_prime(n)) {
            printf("%lld is a prime number\n", n);
        } else {
            printf("%lld is not a prime number\n", n);
        }

        double time_taken = (double)(clock() - start_time) / CLOCKS_PER_SEC;
        printf("Time: %.2f seconds\n", time_taken);
        //fprintf(file, "Run %d, %.2f seconds, Version 1 with 1 Processor\n", i+1, time_taken);
    }

    fclose(file);
    return 0;
}