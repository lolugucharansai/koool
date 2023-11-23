#include<bits/stdc++.h>
using namespace std;

void calcParityBits(char arr[], int n) {
    for(int i = 0; i < n; i++) {
        if(arr[i] == 'r') {
            int parity = 0;
            for(int j = i + 1; j < n; j++) {
                if((j & i) == i && arr[j] != 'r') {
                    parity ^= arr[j] - '0';
                }
            }
            arr[i] = parity + '0';
        }
    }
}

int main() {
    char arr[] = "rr1r010r1010011";
    int n = strlen(arr);
  
    calcParityBits(arr, n);
  
    cout << "Hamming code is: " << arr << endl;
  
    return 0;
}
