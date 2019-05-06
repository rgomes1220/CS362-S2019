#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include "cardHelpers.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#include <assert.h>



//test the getCost function
int main (){

    int currentCard =0;
    int cost;
    int i = 0;
    int numberOfCards = 27;
    int cardCosts[] = {0,2,5,8,0,3,6,6,5,4,4,5,4,4,3,4,3,5,3,5,3,4,2,5,4,4,4};

    printf("******UNIT TEST 2: getCost()******\n");

    //the indivdual card costs are above, as the cards are looped
    //through, the cost returned should match waht's above
    //assert that it matches, and invalid index returns 0

    while (i <= numberOfCards){
        if (i == numberOfCards){ //test invalid index
            cost = getCost(i);
            assert(cost == -1);
        }
        else{
            cost = getCost(i);
            assert(cost == cardCosts[i]);
        }
        // move to next card
        i++;
    }

    printf("Test PASSED\n");
    printf("******UNIT TEST 2 COMPLETE******\n\n");
    return 0;

}
