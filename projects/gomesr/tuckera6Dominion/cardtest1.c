#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
// #include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

//adventurer card test
int main(){

    printf("****CARD TEST 1: Adventurer****\n");
    int i;
    int result;
    int seed = 1000;
    int flag = 0;
    int numPlayer = 2;
    int thisPlayer = 0;
    int cardDrawn1, cardDrawn2;
    int finalCount = 0;
    int originalCount = 0;
    struct gameState theGame, testG;
    int kingdomCards[10] = {adventurer, smithy, gardens, council_room, village, embargo, mine, remodel, sea_hag, tribute};

    int test = initializeGame(numPlayer, kingdomCards, seed, &theGame);
    memcpy(&testG, &theGame, sizeof(struct gameState));



    //manually put adventurer into his hand
    testG.hand[thisPlayer][testG.handCount[thisPlayer]] = adventurer;
    testG.handCount[thisPlayer]++;

    //test hand must be one more than the original
    if(theGame.handCount[thisPlayer] + 1 == testG.handCount[thisPlayer]){
        printf("Test passed! Adventurer card drawn to hand.\n");
    }else
    {
        printf("Test failed. Adventurer card not drawn.\n");
        flag = -1;
    }


    //output tests
    cardEffect(adventurer, 0, 0, 0, &testG,
            testG.hand[thisPlayer][testG.handCount[thisPlayer]-1], 0);

    // for (i = 0; i < testG.handCount[thisPlayer]; i++) {
    //     cardDrawn1 = testG.hand[thisPlayer][i];
    //     if (cardDrawn1 == copper || cardDrawn1 == silver || cardDrawn1 == gold){
    //         finalCount++;
    //     }
    // }
    //
    // //original
    // for (i = 0; i < theGame.handCount[thisPlayer]; i++) {
    //     cardDrawn2 = theGame.hand[thisPlayer][i];
    //     if (cardDrawn2 == copper || cardDrawn2 == silver || cardDrawn2 == gold){
    //         originalCount++;
    //     }
    // }
    //
    //
    // printf("Teasture cards in testG pre card effect::: %d\n", finalCount);
    // printf("Teasture cards in referengeG pre card effect::: %d\n", originalCount);



    //check that 2 treasure cards were drawn
    //test
    for (i = 0; i < testG.handCount[thisPlayer]; i++) {
        cardDrawn1 = testG.hand[thisPlayer][i];
        if (cardDrawn1 == copper || cardDrawn1 == silver || cardDrawn1 == gold){
            finalCount++;
        }
    }

    //original
    for (i = 0; i < theGame.handCount[thisPlayer]; i++) {
        cardDrawn2 = theGame.hand[thisPlayer][i];
        if (cardDrawn2 == copper || cardDrawn2 == silver || cardDrawn2 == gold){
            originalCount++;
        }
    }

    if ((finalCount - originalCount) > 0 && (finalCount - originalCount) < 3 ){
        printf("Test passed! %d additional treasure cards drawn.\n", (finalCount - originalCount));
    }else{
        printf("Test failed. Less than 1 or more than 3 treasure cards drawn.\n");
        flag = -1;
    }

    /* Verifies that additional cards are present after playing Adventure card. */
    if (theGame.handCount[thisPlayer] < testG.handCount[thisPlayer]){
        printf("Test passed! Additional cards added to test hand.\n");
    }else{
        printf("Test failed. No additional cards added to test hand.\n");
        flag = -1;
    }

    if(flag == 0){
        printf("All tests passed!\n\n");
    } else{
        printf("TESTING FAILED!\n\n");
    }

    printf("****CARD TEST 1 Adventurer COMPLETE****\n\n");
}
