#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>



//check if a player's turn is finished
int main (){
    printf("******UNIT TEST 1: TURN OVER?******\n");

    // int initializeGame(int numPlayers, int kingdomCards[10], int randomSeed, struct gameState *state)
    struct gameState currState;
    //kingdom cards for intilize game function
    int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};

    int gameInit = initializeGame(2, kingdomCards, 3, &currState);
    assert(gameInit == 0);


    int gameOver = isGameOver(&currState);
    assert(gameOver==0);
    printf("Test PASSED\n");

    printf("******UNIT TEST 1 COMPLETE******\n\n");


	return 0;

}
