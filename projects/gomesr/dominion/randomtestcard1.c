// random test for great hall

#include <time.h>
#include <string.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>


#define TOTAL_TESTS 1000

int passedTests = 0;
int failedTests = 0;

struct gameState theGame, testG;


int testGreatHallCard(int choice1, int choice2, int choice3, int handPos, int currentPlayer)
{
    int bonus = 0; //0 passed into card effect for bonus
    int flag = 0;   //indciate whether test was passed

    cardEffect(great_hall, choice1, choice2, choice3, &testG, handPos, &bonus);

    //the remaining number of cards in the test player's hand is one more than the
    //non affected game
    if((theGame.handCount[theGame.whoseTurn] + 1) != testG.handCount[testG.whoseTurn]){
        // printf("Test failed! Number of remaining cards in hand is incorrect\n" );
        flag = -1;
    } else {
        // printf("Test passed! Number of remaining cards in hand is correct\n" );
        flag = 0;
    }

    // the non affected game should have one less action than the test one
    // great hall should increment actions by 1
    if (theGame.numActions+1 != testG.numActions) {
        // printf("Test failed! Number of actions isn't what is expected\n" );
        flag = -1;
    } else {
        // printf("Test passed! Number of actions correct\n" );
        flag = 0;
    }


    return flag;
}




int main() {

    srand(time(0));
    printf("****RANDOM TEST FOR GREAT HALL CARD****\n");


    for (int i = 0; i < TOTAL_TESTS; i++) {

        int randomSeed = rand();
        // int players =2;
        int players = (rand() % (4 - 2 + 1)) + 2;
        int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};

        //random input values for randomly testing adventurer effect
        int choice1 = rand() % 10;
        int choice2 = rand() % 10;
        int choice3 = rand() % 10;
        int handPos = rand() % 10;
        // int currentPlayer = rand()%2;
        int currentPlayer = rand()%players;

        int gameinit =  initializeGame(players, kingdomCards, randomSeed, &theGame);

        memcpy(&testG, &theGame, sizeof(struct gameState));

        //test with random inputs
        int status = testGreatHallCard(choice1, choice2, choice3, handPos, currentPlayer);

        if (status==0) {
            passedTests++;
        }
        if (status==-1) {
            failedTests++;
        }

    }

    printf("Tests passed: %d/%d\n", passedTests, TOTAL_TESTS);
    printf("Tests failed: %d/%d\n\n", failedTests, TOTAL_TESTS);

}
