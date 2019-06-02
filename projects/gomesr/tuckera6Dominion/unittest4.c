#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
// #include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

//check buying Cards
int main(){

    printf("******UNIT TEST 4: buying cards******\n");
    struct gameState theGame;
    theGame.numBuys = 2;
	theGame.coins = 2;

    theGame.supplyCount[2]=10;
    theGame.supplyCount[3]=10;

    // buyCard(3, &theGame); //province, 8
    // buyCard(2, &theGame); //dutchy, 5

    //try to buy ductchy, shouldn't be able to
    int buyDutchyUnsuccessful = buyCard(2, &theGame);
    assert(buyDutchyUnsuccessful==-1);

    //now adding coins
    theGame.coins = 20;
    //try to buy province
    int buyProvince = buyCard(3, &theGame);
    assert(buyProvince==0);

    //try to buy dutchy
    int buyDutchy = buyCard(2, &theGame);
    assert(buyDutchy==0);

    //used two buys, see if we can buy another
    int buyAnotherDuthcy = buyCard(2, &theGame);
    assert(buyAnotherDuthcy==-1);


    //set the spplies to 0
    theGame.supplyCount[2]=0;
    theGame.supplyCount[3]=0;

    //everything should be -1
    for (int i = 0; i < 27; i++) {
        assert(buyCard(i, &theGame)==-1);
    }

    printf("Tests PASSED\n");
    printf("******UNIT TEST 4 COMPLETE******\n\n");


}
