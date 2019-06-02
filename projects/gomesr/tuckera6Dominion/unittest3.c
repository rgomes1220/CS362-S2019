#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
// #include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>


//test the updateCoins function
int main(){
    struct gameState theGame;
	theGame.coins = 0;
    theGame.handCount[0]=10;

    printf("******UNIT TEST 3: updateCoins()******\n");

    //test copper, put copper in all hand positions
    //total coins should be 10 after updating
    for (int i = 0; i < 10; i++){
        theGame.hand[0][i] = copper;
    }

    updateCoins(0, &theGame, 0);
    // printf("number of coins:: %d\n",theGame.coins );
    assert(theGame.coins == 10);


    //test copper, put copper in all hand positions
    //total coins should be 10 after updating
    for (int i = 0; i < 10; i++){
        theGame.hand[0][i] = copper;
    }
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 10);


    //test Silver, put Silver in all hand positions
    //total coins should be 20 after updating
    for (int i = 0; i < 10; i++){
        theGame.hand[0][i] = silver;
    }
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 20);


    //test gold, put gold in all hand positions
    //total coins should be 30 after updating
    for (int i = 0; i < 10; i++){
        theGame.hand[0][i] = gold;
    }
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 30);


    //test partial
    //total coins should be 3(5)+10=20 after updating
    for (int i = 0; i < 10; i++){
        if (i<=4) {
            theGame.hand[0][i] = gold;
        } else {
            theGame.hand[0][i] = copper;
        }

    }
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 20);


    //test partial
    //total coins should be 2(5)=10 after updating
    for (int i = 0; i < 5; i++){
        theGame.hand[0][i] = silver;

    }
    theGame.hand[0][5] = adventurer;
    theGame.hand[0][6] = smithy;
    theGame.hand[0][7] = gardens;
    theGame.hand[0][8] = gardens;
    theGame.hand[0][9] = council_room;
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 10);


    //no coins
    //total coins should be 0 after updating
    theGame.hand[0][0] = adventurer;
    theGame.hand[0][1] = smithy;
    theGame.hand[0][2] = gardens;
    theGame.hand[0][3] = gardens;
    theGame.hand[0][4] = council_room;
    theGame.hand[0][5] = adventurer;
    theGame.hand[0][6] = smithy;
    theGame.hand[0][7] = gardens;
    theGame.hand[0][8] = gardens;
    theGame.hand[0][9] = council_room;
    updateCoins(0, &theGame, 0);
    assert(theGame.coins == 0);

    printf("Tests PASSED\n");


    printf("******UNIT TEST 3 COMPLETE******\n\n");





}
