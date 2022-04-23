#include <fstream>
#include <string>
#include <vector>
#include <iostream>

using namespace std;

ifstream filein("input.txt");
ofstream fileout("output.txt");

const long long x = 9091, p = 99990001;

long long my_hash(string & hashed) {
    long long answer = 0;
    for(char el : hashed) {
        answer *= x; answer += el; answer %= p;
    }
    return answer;
}

long long paw_mod(int basis, int power, int modulus) {
    long long answer = 1;
    for(int n = 0; n < power; n++) {
        answer = (answer * basis) % modulus;
    }
    return answer;
}

void fill_hash_vec(vector<long long> & hashes, string & s, int len) {
    string first_part = s.substr(0, len);
    hashes.push_back(my_hash(first_part));
    long long biggest_x = paw_mod(x, len, p);
    for(int i = 1; i < s.size() + 1 - len; i++) {
        long long val = hashes[hashes.size() - 1] * x - biggest_x * s[i - 1] + s[i + len - 1];
        hashes.push_back((val % p + p) % p);
    }
}

int main() {
    string a, b;
    filein >> b;
    filein >> a;
    vector<long long> hashes(0);
    fill_hash_vec(hashes, a, b.size());
    long long second_hash = my_hash(b);
    vector<int> substr_indexes(0);
    for(int i=0; i<hashes.size(); i++) {
        if(hashes[i] != second_hash) continue;
        substr_indexes.push_back(i);
    }
    fileout << substr_indexes.size() << endl;
    for(int el : substr_indexes) fileout << el + 1 << ' ';
    return 0;
}
