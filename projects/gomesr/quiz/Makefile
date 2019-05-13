CFLAGS= -Wall -fpic -coverage -lm -std=c99

all:
	gcc -o testme -g testme.c $(CFLAGS)
	./testme

clean:
	rm -f testme *.gcov *.gcda *.gcno *.o
