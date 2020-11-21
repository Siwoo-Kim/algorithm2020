package com.siwoo.p1000;

public class PL1603 {

    class ParkingSystem {
        private final int[] spaces = new int[4];
        
        public ParkingSystem(int big, int medium, int small) {
            spaces[1] = big;
            spaces[2] = medium;
            spaces[3] = small;
        }

        public boolean addCar(int carType) {
            if (spaces[carType] == 0)
                return false;
            spaces[carType]--;
            return true;
        }
    }
}
