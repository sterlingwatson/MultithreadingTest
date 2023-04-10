#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>

bool is_prime(unsigned long long n) {
    int sqrt_n = (int)ceil(sqrt(n));
    for (unsigned long long i = 2; i <= sqrt_n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    //unsigned long long n=  949175020003302601; //9782923512283668847ULL;  // 
    unsigned long long n = 18446744073709551557ULL;
    FILE *file = fopen("Times.txt", "a");

    for (int i = 0; i < 5; i++) {
        clock_t start_time = clock();

        if (is_prime(n)) {
            printf("%llu is a prime number\n", n);
        } else {
            printf("%llu is not a prime number\n", n);
        }

        double time_taken = (double)(clock() - start_time) / CLOCKS_PER_SEC;
        printf("Time: %.2f seconds\n", time_taken);
        fprintf(file, "Run %d, %.2f seconds, Version 1 with 1 Processor\n", i+1, time_taken);
    }

    fclose(file);
    return 0;
}
