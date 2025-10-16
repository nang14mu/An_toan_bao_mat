#include <bits/stdc++.h>
using namespace std;

int powerMod(int a, int m, int n) {
    int result = 1;
    a = a % n;
    while (m > 0) {
        if (m % 2 == 1) result = (result * a) % n;
        a = (a * a) % n;
        m /= 2;
    }
    return result;
}

int main() {
	cout << "Output: b = " << powerMod(397,6329,6329) << endl;
}
