package algorithms;

public class Simple {
    static int max(int a, int b) {
        if(a > b) {
            return a;
        } else {
            return b;
        }
    }

    static int min(int a, int b) {
        if(a < b) {
            return a;
        } else {
            return b;
        }
    }

    static int maxOf(int a, int b, int c) {
        if(a > b) {
            if(a > c) {
                return 1;
            } else {
                return 3;
            }
        } else {
            if(b > c) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    static int minOf(int a, int b, int c) {
        if(a < b) {
            if(a < c) {
                return 1;
            } else {
                return 3;
            }
        } else {
            if(b < c) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}