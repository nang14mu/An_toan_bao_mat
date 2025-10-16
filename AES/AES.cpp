#include <bits/stdc++.h>
using namespace std;

const string SBox[16][16] = {
    {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
    {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
    {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
    {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
    {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
    {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
    {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
    {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
    {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
    {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
    {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
    {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
    {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
    {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
    {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
    {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}
};

const string Rcon[4][11] = {
    {"00","01", "02", "04", "08", "10", "20", "40", "80", "1b", "36"},
    {"00","00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
    {"00","00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
    {"00","00", "00", "00", "00", "00", "00", "00", "00", "00", "00"}
};

string RotWord(string a) {
    return a.substr(2) + a.substr(0, 2);
}

string sb(string a) {
    int row = stoi(a.substr(0, 1), nullptr, 16);
    int col = stoi(a.substr(1, 1), nullptr, 16);
    return SBox[row][col];
}

string sBox(string a) {
    string kq;
    for (int i = 0; i < 8; i += 2) {
        string hexPair = a.substr(i, 2);
        string sBoxValue = sb(hexPair);
        kq += sBoxValue;
    }
    return kq;
}

int hexToInt(const string &hex) {
    return stoi(hex, nullptr, 16);
}

string intToHex(int value) {
    stringstream ss;
    ss << hex << setfill('0') << setw(2) << value;
    return ss.str();
}

string xorRcon(const string &a, int round) {
    string result = "";
    for (int i = 0; i < 4; i++) {
        int valueA = hexToInt(a.substr(i * 2, 2));
        int valueRcon = hexToInt(Rcon[i][round]);
        int xorValue = valueA ^ valueRcon;
        result += intToHex(xorValue);
    }
    return result;
}

string xorBit(const string &a, const string &b) {
    string result;
    for (size_t i = 0; i < a.length(); i += 2) {
        string byteA = a.substr(i, 2);
        string byteB = b.substr(i, 2);

        int intA = hexToInt(byteA);
        int intB = hexToInt(byteB);

        int xorValue = intA ^ intB;
        result += intToHex(xorValue);
    }
    return result;
}

vector<unsigned char> hexStringToBytes(const string &hex) {
    vector<unsigned char> bytes;
    for (size_t i = 0; i < hex.length(); i += 2) {
        string byteString = hex.substr(i, 2);
        unsigned char byte = (unsigned char) strtol(byteString.c_str(), nullptr, 16);
        bytes.push_back(byte);
    }
    return bytes;
}

string bytesToHexString(const vector<unsigned char> &bytes) {
    stringstream ss;
    for (unsigned char byte : bytes) {
        ss << hex << setw(2) << setfill('0') << (int)byte;
    }
    return ss.str();
}
unsigned char hexToUChar(const string& hex) {
    return static_cast<unsigned char>(stoi(hex, nullptr, 16));
}

string uCharToHex(unsigned char byte) {
    stringstream ss;
    ss << hex << setw(2) << setfill('0') << (int)byte;
    return ss.str();
}

string addRoundKey(const string &state, const string &key) {
    vector<unsigned char> stateBytes = hexStringToBytes(state);
    vector<unsigned char> keyBytes = hexStringToBytes(key);

    for (size_t i = 0; i < stateBytes.size(); i++) {
        stateBytes[i] ^= keyBytes[i];
    }

    return bytesToHexString(stateBytes);
}

string subBytes(const string &state) {
    vector<unsigned char> stateBytes = hexStringToBytes(state);
    vector<unsigned char> substitutedBytes;

    for (unsigned char byte : stateBytes) {
        int row = (byte >> 4) & 0x0F;
        int col = byte & 0x0F;       
        string sBoxValue = SBox[row][col]; 
        substitutedBytes.push_back((unsigned char) strtol(sBoxValue.c_str(), nullptr, 16));
    }

    return bytesToHexString(substitutedBytes);
}

string ShiftRows(const string& state) {
    vector<vector<unsigned char>> matrix(4, vector<unsigned char>(4));
    
    for (size_t i = 0; i < 4; ++i) {
        for (size_t j = 0; j < 4; ++j) {
            matrix[j][i] = hexToUChar(state.substr((i * 4 + j) * 2, 2));
        }
    }

    for (size_t i = 1; i < 4; ++i) {
        vector<unsigned char> tempRow(4);
        for (size_t j = 0; j < 4; ++j) {
            tempRow[j] = matrix[i][(j + i) % 4];
        }
        matrix[i] = tempRow;
    }

    string result;
    for (size_t i = 0; i < 4; ++i) {
        for (size_t j = 0; j < 4; ++j) {
            result += uCharToHex(matrix[j][i]);
        }
    }
    return result; 
}

unsigned char gmul(unsigned char a, unsigned char b) {
    unsigned char result = 0;
    while (b) {
        if (b & 1) {
            result ^= a; 
        }

        bool overflow = a & 0x80; 
        a <<= 1;
        if (overflow) {
            a ^= 0x1b; 
        }
        b >>= 1; 
    }
    return result;
}

string MixColumns(const string& state) {
    vector<vector<unsigned char>> matrix(4, vector<unsigned char>(4));

    for (size_t i = 0; i < 4; ++i) {
        for (size_t j = 0; j < 4; ++j) {
            matrix[j][i] = hexToUChar(state.substr((i * 4 + j) * 2, 2));
        }
    }

    for (size_t i = 0; i < 4; ++i) {
        unsigned char a[4];
        for (size_t j = 0; j < 4; ++j) {
            a[j] = matrix[j][i];
        }

        matrix[0][i] = gmul(a[0], 2) ^ gmul(a[1], 3) ^ gmul(a[2], 1) ^ gmul(a[3], 1);
        matrix[1][i] = gmul(a[0], 1) ^ gmul(a[1], 2) ^ gmul(a[2], 3) ^ gmul(a[3], 1);
        matrix[2][i] = gmul(a[0], 1) ^ gmul(a[1], 1) ^ gmul(a[2], 2) ^ gmul(a[3], 3);
        matrix[3][i] = gmul(a[0], 3) ^ gmul(a[1], 1) ^ gmul(a[2], 1) ^ gmul(a[3], 2);
    }

    string result;
    for (size_t i = 0; i < 4; ++i) {
        for (size_t j = 0; j < 4; ++j) {
            result += uCharToHex(matrix[j][i]);
        }
    }
    return result;
}

void readKeyAndMessage(const string& filename, string& K, string& M) {
    ifstream file(filename);
    if (!file) {
        cerr << "Cannot open file: " << filename << endl;
        return;
    }

    getline(file, K); 
    getline(file, M); 

    file.close();
}

int main() {
    string K;
    string w[44];
	string M;
	readKeyAndMessage("AES.txt", K, M);
    for (int i = 0; i < 4; i++) {
        w[i] = K.substr(i * 8, 8);
    }
//    Câu 1
	cout<<"Cau 1:"<<endl;
	for(int i=0;i<4;i++){
		cout<<"w"<<i<<"=	"<<w[i]<<endl;
	}
//	Câu 2
	cout<<"Cau 2:"<<endl;
    string rw = RotWord(w[3]);
    cout<<"rw = RotWord(w3) =	"<<rw<<endl;
//  Câu 3
	cout<<"Cau 3:"<<endl;
    string sw = sBox(rw);
    cout<<"sw = SubWord(rw) =	"<<sw<<endl;
//    Câu 4
	cout<<"Cau 4:"<<endl;
    string xcsw = xorRcon(sw, 1);
	cout<<"xcsw = XorRcon(sw, RC[i]) =	"<<xcsw<<endl;
//	Câu 5
    w[4] = xorBit(xcsw, w[0]);
    w[5] = xorBit(w[4], w[1]);
    w[6] = xorBit(w[5], w[2]);
    w[7] = xorBit(w[6], w[3]);
	cout<<"Cau 5:"<<endl;
    cout << "w4 = XORbit(xcsw, w0) =	" << w[4] << endl;
    cout << "w5 = XORbit(w4, w1) =	" << w[5] << endl;
    cout << "w6 = XORbit(w5, w2) =	" << w[6] << endl;
    cout << "w7 = XORbit(w6, w3) =	" << w[7] << endl;
	
//	lặp lại
	for (int i = 8; i <= 43; i += 4) {
        string rw1 = RotWord(w[i - 1]);
        string sw1 = sBox(rw1);
        string xcsw1 = xorRcon(sw1, i / 4);
        w[i] = xorBit(xcsw1, w[i - 4]);
        w[i + 1] = xorBit(w[i], w[i - 3]);
        w[i + 2] = xorBit(w[i + 1], w[i - 2]);
        w[i + 3] = xorBit(w[i + 2], w[i - 1]);
    }
    string key[11];
    for(int i =  1 ; i<= 10 ; i++){
        key[i] =  w[i*4] + w[i*4+1] + w[i*4+2] + w[i*4+3]; 
    }
//    Câu 6
	cout<<"Cau 6:"<<endl;
	string state=addRoundKey(M,K);
	cout<<"state=	"<<state<<endl;
//	Câu 7-10
	for(int i=1;i<10;i++){
		cout<<"Vong lap thu "<<i<<endl;
		state=subBytes(state);
		cout<<"Cau 7:  state="<<state<<endl;
		state=ShiftRows(state);
		cout<<"Cau 8:  state="<<state<<endl;
		state=MixColumns(state);
		cout<<"Cau 9:  state="<<state<<endl;
		state=addRoundKey(state,key[i]);
		cout<<"Cau 10: state="<<state<<endl;
	}
//	Câu 11
	cout<<"Cau 11:"<<endl;
	state=addRoundKey(ShiftRows(subBytes(state)),key[10]);
	cout<<state<<endl;
}
