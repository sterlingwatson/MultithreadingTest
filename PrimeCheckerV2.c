#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <pthread.h>

bool is_prime(unsigned long long n) {
    int sqrt_n = (int)ceil(sqrt(n));
    for (unsigned long long i = 2; i <= sqrt_n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

typedef struct {
    unsigned long long n;
    bool *result;
} threading_data; //struct for holding from each thread

void *find_prime(void *arg){
    threading_data *data = (threading_data*)arg;
    *(data->result) = is_prime(data -> n);
    return NULL;
}

int main() {
    unsigned long long n = 18446744073709551557ULL;

    bool result1 = false;
    bool result2 = false;

    FILE *file = fopen("Times.txt", "a");

    for (int i = 0; i < 5; i++) {
        clock_t start_time = clock();

        pthread_t threads[2];
        threading_data data1 = {n, &result1};
        threading_data data2 = {n, &result2};

        pthread_create(&threads[0], NULL, find_prime, &data1);
        pthread_create(&threads[0], NULL, find_prime, &data2);

        pthread_join(threads[0], NULL);
        pthread_join(threads[1], NULL);


        if (result1 || result2) {
            printf("%llu is a prime number\n", n);
        } else {
            printf("%llu is not a prime number\n", n);
        }

        double time_taken = (double)(clock() - start_time) / CLOCKS_PER_SEC;
        printf("Time: %.2f seconds\n", time_taken);
        //fprintf(file, "Run %d, %.2f seconds, Version 1 with 1 Processor\n", i+1, time_taken);
    }

    fclose(file);
    return 0;
}
