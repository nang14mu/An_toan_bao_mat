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

bool isPrimitiveRoot(int n, int a) {
    if (n == 2)
        return (a == 1);
    vector<bool> check(n, false);
    for (int i = 1; i < n; i++) {
        int x = powerMod(a, i, n);
        if (check[x])
            return false;
        check[x] = true;
    }
    return true;
}

int main(){
	int a = 5,n = 463;
    if (isPrimitiveRoot(n,a)){
    	cout << a << " la can nguyen thuy cua " << n << endl;
	}  
	else{
		cout << a << "khong la can nguyen thuy cua " << n << endl;
	}
}
