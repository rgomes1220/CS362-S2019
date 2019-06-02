#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
// #include "cardHelpers.h"
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

//smithy card test
int main(){

    int seed = 30;
	int numPlayers = 2;
    int thisPlayer = 0;
	struct gameState theGame, testG;
	int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};
    int flag = 0;

	printf("****CARD TEST 2: Smithy****\n");

	initializeGame(numPlayers, kingdomCards, seed, &theGame);
	memcpy(&testG, &theGame, sizeof(struct gameState));


    //manually put smity into his hand
    testG.hand[thisPlayer][testG.handCount[thisPlayer]] = smithy;
    testG.handCount[thisPlayer]++;

    //draw cards for the test game
    cardEffect(smithy, 0, 0, 0, &testG,
            testG.hand[thisPlayer][testG.handCount[thisPlayer]-1], 0);

    //+4 for the smithy card + 3 additional cards drawn from its effect
    if((theGame.handCount[theGame.whoseTurn] + 4) != testG.handCount[testG.whoseTurn]){
        printf("Test failed! Correct number of additional cards not drawn\n" );
        flag = -1;
    } else {
        printf("Test success! Correct number of additional cards drawn\n" );

    }

    if(flag == 0){
        printf("All tests passed!\n\n");
    } else{
        printf("TESTING FAILED!\n\n");
    }


	printf("****CARD TEST 2 Smithy COMPLETE****\n\n");

	return 0;


}
