package epi.GreedyCh17;

import java.util.Arrays;

public class ChangeMaking {

    public static int getChanges(int cents){
        int[] coins = {100, 50, 25, 10, 5, 1};

        int counts = 0;
        for(int coin : coins){
            counts += cents / coin;
            cents %= coin;
            System.out.println(coin+" "+counts);
        }

        return counts;
    }

    public static int coinChange(int[] coins, int amount) {
//         Arrays.sort(coins);
//         int counts = 0;
//         for(int i=(coins.length-1); i>=0; i--){
//             int coin = coins[i];
//             counts += amount / coin;
//             amount %= coin;
//             System.out.println(coin+" "+counts);
//         }

//         return amount==0?counts:-1;
        Arrays.sort(coins);
        // missing j--
        for(int i=0, j = coins.length-1; i<j; i++, j--){
            int temp = coins[i];
            coins[i] = coins[j];
            coins[j] = temp;
        }
        for (int coin: coins) System.out.println(coin);
        return getCoinChange(coins, amount, 0, Integer.MAX_VALUE);
    }

    public static int getCoinChange(int[] coins, int amount, int count, int minCount){
        System.out.println("amount:"+amount+" count"+count+ " minCount:"+minCount);
        if(amount == 0) {
            System.out.println("amount:"+amount+" count"+count+ " minCount:"+minCount);
            return count< minCount? count: minCount;
        }
        if(coins.length == 0) return minCount;
        for(int coin : coins){
            int maxLen = amount/coin;
            for(int i=1; i<=maxLen; i++){
                minCount = getCoinChange(Arrays.copyOfRange(coins, 1, coins.length), amount-(coin*i), count+i, minCount);
            }
        }
        return minCount;

    }

    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        System.out.println(coinChange(coins, 6249));
    }
}
