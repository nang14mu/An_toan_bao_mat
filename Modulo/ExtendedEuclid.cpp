#include <bits/stdc++.h>
using namespace std;

int extended_gcd(int a, int b, int &x, int &y) {
    if (a == 0) {
        x = 0;
        y = 1;
        return b;  // gcd
    }
    int x1, y1; // Cac he so tam thoi
    int gcd = extended_gcd(b % a, a, x1, y1);
    
    // update x va y
    x = y1 - (b / a) * x1;
    y = x1;

    return gcd;  // Tra ve gcd
}

int inverse_mod_euclid(int a, int m) {
    int x, y;
    int gcd = extended_gcd(a, m, x, y);
    
    if (gcd != 1) {
        return -1;
    } else {
        return (x % m + m) % m; 
    }
}

int main(){
	cout << "Output: x = " << inverse_mod_euclid(1885,6563) << endl;
}
