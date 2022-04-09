#include<fstream>
#include<vector>

using namespace std;

struct Node {
    long long key;
    int left;
    int right;
};

vector<Node> tree;

ifstream input("input.txt");
ofstream output("output.txt");

long long min_key(int i) {
    if(tree[i].left) return min_key(tree[i].left);
    return tree[i].key;
}

long long max_key(int i) {
    if(tree[i].right) return max_key(tree[i].right);
    return tree[i].key;
}

bool is_correct_tree(int i) {
    Node current = tree[i];
    bool correctness_left = true, correctness_right = true;
    if(current.left) {
        if(max_key(current.left) >= current.key) return false;
        correctness_left = is_correct_tree(current.left);
    }
    if(current.right) {
        if(min_key(current.right) <= current.key) return false;
        correctness_right = is_correct_tree(current.right);
    }

    return correctness_left && correctness_right;
}

int main() {
    int n; input >> n;
    tree.resize(n + 1);
    for(int i=1; i<=n; i++) {
        long long key; int left, right;
        input >> key >> left >> right;
        tree[i].key = key;
        tree[i].left = left;
        tree[i].right = right;
    }

    if(n != 0) {
        if(is_correct_tree(1)) output << "YES";
        else output << "NO";
    }
    else output << "YES";
}