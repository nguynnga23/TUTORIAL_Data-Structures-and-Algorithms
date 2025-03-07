package com.nga;

import java.util.ArrayList;
import java.util.List;

public class ClosestPrimeNumbersinRange {
    // Hàm kiểm tra số nguyên tố
    private static boolean isPrime(int n, boolean[] sieve){
        return sieve[n];
    }

    // Sàng Eratosthenes để đánh dấu số nguyên tố từ 2 đến giới hạn
    private static boolean[] sieveOfEratosthenes(int max){
        boolean[] isPrime = new boolean[max + 1];
        for(int i = 2; i <= max; i++){
            isPrime[i] = true; // Ban đầu giả sử tất cả đều là số nguyên tố
        }

        for(int p = 2; p * p <= max; p++){
            if(isPrime[p]){
                for(int i = p * p; i <= max; i += p){
                    isPrime[i] = false;
                }
            }
        }
        return isPrime;
    }
    public static int[] closestPrimes(int left, int right) {
        boolean[] sieve = sieveOfEratosthenes(right);
        List<Integer> listPrimes = new ArrayList<>();

        // Lọc các số nguyên tố trong khoảng [left, right]
        for(int i = left; i <= right; i++){
            if(isPrime(i, sieve)){
                listPrimes.add(i);
            }
        }

        // Nếu có ít hơn 2 số nguyên tố -> Không có cặp nào
        if(listPrimes.size() < 2){
            return new int[]{-1, -1};
        }

        // Tìm cặp số nguyên tố gần nhau nhất
        int minDiff = Integer.MAX_VALUE;
        int num1 = -1, num2 = -1;

        for(int i = 1; i < listPrimes.size(); i++){
            int diff = listPrimes.get(i) - listPrimes.get(i - 1);
            if(diff < minDiff){
                minDiff = diff;
                num1 = listPrimes.get(i - 1);
                num2 = listPrimes.get(i);
            }
        }
        return new int[]{num1, num2};
    }

    public static void main(String[] args) {
        int left = 10, right = 19;
        int[] result = closestPrimes(left, right);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
