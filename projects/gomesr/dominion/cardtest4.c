#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

//great hall card test
int main(){
    int seed = 30;
	int numPlayers = 2;
    int thisPlayer = 0;
	struct gameState theGame, testG;
	int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};
    int flag = 0;


    printf("****CARD TEST 4: Great HAll****\n");

    initializeGame(numPlayers, kingdomCards, seed, &theGame);
    memcpy(&testG, &theGame, sizeof(struct gameState));

    //manually put village into his hand
    testG.hand[thisPlayer][testG.handCount[thisPlayer]] = village;
    testG.handCount[thisPlayer]++;

    //draw cards for the test game
    cardEffect(great_hall, 0, 0, 0, &testG,
            testG.hand[thisPlayer][testG.handCount[thisPlayer]-1], 0);

    if((theGame.handCount[theGame.whoseTurn] + 1) != testG.handCount[testG.whoseTurn]){
        printf("Test failed! Number of remaining cards in hand is incorrect\n" );
        flag = -1;
    } else {
        printf("Test passed! Number of remaining cards in hand is correct\n" );
    }

    if (theGame.numActions+1 != testG.numActions) {
        printf("Test failed! Number of actions isn't what is expected\n" );
        flag = -1;
    } else {
        printf("Test passed! Number of actions correct\n" );
    }


    if(flag == 0){
        printf("All tests passed!\n\n");
    } else{
        printf("TESTING FAILED!\n\n");
    }


	printf("****CARD TEST 4 Great HAll COMPLETE****\n\n");

	return 0;




}
