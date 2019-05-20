CFLAGS= -Wall -fpic -coverage -lm -std=c99

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)


cardHelpers.o: cardHelpers.h cardHelpers.c
	gcc -c cardHelpers.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o cardHelpers.o
	gcc -c dominion.c  -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o cardHelpers.o $(CFLAGS)

#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out


player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o cardHelpers.o $(CFLAGS)

randomtestadventurer: randomtestadventurer.c dominion.o
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o cardHelpers.o $(CFLAGS)

randomtestgreathall: randomtestcard1.c dominion.o
	gcc -o randomtestgreathall -g randomtestcard1.c dominion.o rngs.o cardHelpers.o $(CFLAGS)

randomtestembargo: randomtestcard2.c dominion.o
	gcc -o randomtestembargo -g randomtestcard2.c dominion.o rngs.o cardHelpers.o $(CFLAGS)


randomtestresults: randomtestadventurer randomtestgreathall randomtestembargo
	./randomtestadventurer >> randomtestresults.out
	./randomtestgreathall >> randomtestresults.out
	./randomtestembargo >> randomtestresults.out
	gcov -fb cardHelpers.c  >> randomtestresults.out
	gcov dominion.c -b >> randomtestresults.out
	cat dominion.c.gcov >> randomtestresults.out
	gcov dominion.c -fb >> randomtestresults.out

all: playdom player

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe randomtestadventurer randomtestgreathall randomtestembargo
