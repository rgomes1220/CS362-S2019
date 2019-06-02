// random test for embargo

#include <time.h>
#include <string.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
// #include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>


#define TOTAL_TESTS 1000

int passedTests = 0;
int failedTests = 0;

struct gameState theGame, testG;


int testEmbargoCard(int choice1, int choice2, int choice3, int handPos, int currentPlayer)
{
    int bonus = 0; //0 passed into card effect for bonus
    int flag = 0;   //indciate whether test was passed

    int ceres = cardEffect(embargo, choice1, choice2, choice3, &testG, handPos, &bonus);

    if (ceres!=-1) {

        //the tested state has 2 more coins than the original
        if((theGame.coins + 2) != testG.coins){
            flag = -1;
            printf(" the tested state has 2 more coins than the original \n" );
            return flag;

        } else {
            flag = 0;
        }

        // tested one's choice1 has an embargo token on the pile
        if (theGame.embargoTokens[choice1]+1 != testG.embargoTokens[choice1]) {
            flag = -1;
            return flag;
        } else {
            flag = 0;
        }


        //player in the tested game has one less card after the card effect
        if((theGame.handCount[theGame.whoseTurn] - 1) != testG.handCount[testG.whoseTurn]){
            // printf("Test failed! Number of remaining cards in hand is incorrect\n" );
            flag = -1;

            return flag;
        } else {
            // printf("Test passed! Number of remaining cards in hand is correct\n" );
            flag = 0;
        }
    }




    return flag;
}




int main() {

    srand(time(0));
    printf("****RANDOM TEST FOR EMBARGO CARD****\n");


    for (int i = 0; i < TOTAL_TESTS; i++) {

        int randomSeed = rand();
        int players = (rand() % (4 - 2 + 1)) + 2;
        int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};

        //random input values for randomly testing embargo effect
        int choice1 = rand() % 10;
        int choice2 = rand() % 10;
        int choice3 = rand() % 10;
        int handPos = rand() % 10;
        int currentPlayer = rand()%players;

        int gameinit = initializeGame(players, kingdomCards, randomSeed, &theGame);

        memcpy(&testG, &theGame, sizeof(struct gameState));

        //test with random inputs
        int status = testEmbargoCard(choice1, choice2, choice3, handPos, currentPlayer);

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
