#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <pthread.h>

bool is_prime(unsigned long long start, unsigned long long end) {
        //printf("Inside \n");
    for (unsigned long long n =start; n <= end; n++){
        //printf("Inside \n");
        int sqrt_n = (int)ceil(sqrt(n));
        //printf("SquareRoot calculated \n");

        for (unsigned long long i = 2; i <= sqrt_n; i++) {
            if (end % i == 0) {
                return false;
            }            
        }
    }   
    return true;
}

typedef struct {
    unsigned long long start;
    unsigned long long end;
    bool *result;
} threading_data; //struct for holding from each thread

void *find_prime(void *arg){
    threading_data *data = (threading_data*)arg;
    *(data->result) = is_prime(data -> start, data -> end);
    return NULL;
}

int main() {
    unsigned long long n = 13;//949175020003302601; //
    printf("first line of main \n");
    //unsigned long long n = 18446744073709551557ULL;
    unsigned long long midpoint = n/2;
    printf("midpoint found \n");

    bool result1 = false;
    bool result2 = false;

    //FILE *file = fopen("Times.txt", "a");

    for (int i = 0; i < 5; i++) {
        clock_t start_time = clock();

        pthread_t threads[2];
        printf("made threads\n");
        threading_data data1 = {2, midpoint, &result1};
                printf("data assigned thread 1\n");
        threading_data data2 = {midpoint+1, n, &result2};
                        printf("data assigned thread 2\n");


        pthread_create(&threads[0], NULL, find_prime, &data1);
                        printf("thread 1\n");

        pthread_create(&threads[1], NULL, find_prime, &data2);
                                printf("thread 2\n");


        pthread_join(threads[0], NULL);
        pthread_join(threads[1], NULL);


        if (result1 && result2) {
            printf("%llu is a prime number\n", n);
        } else {
            printf("%llu is not a prime number\n", n);
        }

        double time_taken = (double)(clock() - start_time) / CLOCKS_PER_SEC;
        printf("Time: %.2f seconds\n", time_taken);
        //fprintf(file, "Run %d, %.2f seconds, Version 2 with 3 Processor\n", i+1, time_taken);
    }

    //fclose(file);
    return 0;
}
