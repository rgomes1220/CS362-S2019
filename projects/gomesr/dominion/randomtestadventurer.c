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


int testAdventurerCard(int choice1, int choice2, int choice3, int handPos, int currentPlayer)
{
    int bonus = 0; //0 passed into card effect for bonus
    int flag = 0;

    cardEffect(adventurer, choice1, choice2, choice3, &testG, handPos, &bonus);

    //check that 2 treasure cards were drawn
    //test
    int finalCount=0;
    for (int i = 0; i < testG.handCount[currentPlayer]; i++) {
        int cardDrawn1 = testG.hand[currentPlayer][i];
        if (cardDrawn1 == copper || cardDrawn1 == silver || cardDrawn1 == gold){
            finalCount++;
        }
    }

    //original
    int originalCount=0;
    for (int i = 0; i < theGame.handCount[currentPlayer]; i++) {
        int cardDrawn2 = theGame.hand[currentPlayer][i];
        if (cardDrawn2 == copper || cardDrawn2 == silver || cardDrawn2 == gold){
            originalCount++;
        }
    }

    if ((finalCount - originalCount) > 0 && (finalCount - originalCount) < 3 ){
        // printf("Test passed! %d additional treasure cards drawn.\n", (finalCount - originalCount));
        flag=0;
    }else{
        // printf("Test failed. Less than 1 or more than 3 treasure cards drawn.\n");
        flag = -1;
    }

    /* Verifies that additional cards are present after playing Adventure card. */
    if (theGame.handCount[currentPlayer] < testG.handCount[currentPlayer]){
        // printf("Test passed! Additional cards added to test hand.\n");
        flag=0;
    }else{
        // printf("Test failed. No additional cards added to test hand.\n");
        flag = -1;
    }


    return flag;
}




int main() {

    srand(time(0));
    printf("****RANDOM TEST FOR ADVENTURER CARD****\n");


    for (int i = 0; i < TOTAL_TESTS; i++) {

        int randomSeed = rand();
        // int players =2;
        int players = (rand() % (4 - 2 + 1)) + 2;
        // int i;
        // int flag = 0;
        int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};

        int gameinit = initializeGame(players, kingdomCards, randomSeed, &theGame);

        // if (gameinit==0) {
        //     passedTests++;
        // }
        // if (gameinit==-1) {
        //     failedTests++;
        // }

        memcpy(&testG, &theGame, sizeof(struct gameState));


        //random input values for randomly testing adventurer effect
        int choice1 = rand() % 10;
        int choice2 = rand() % 10;
        int choice3 = rand() % 10;
        int handPos = rand() % 10;
        int currentPlayer = rand()%players;

        //randomly decide if we want to empty a deck
        //if we empty a deck, this will force code coverage by
        // forcing the shuffle branch of adventurer to run
        int shuffleFlag = rand() % 2;
        if (shuffleFlag==0) {

            testG.deckCount[0] = 0;
        }



        //test with random inputs
        int status = testAdventurerCard(choice1, choice2, choice3, handPos, currentPlayer);

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
