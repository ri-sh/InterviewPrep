In a computer game, you are fighting against 𝑛 monsters. Monster number 𝑖 has 𝑎𝑖 health points, all 𝑎𝑖  are integers. 
A monster is alive while it has at least 1 health point. You can cast spells of two types: 

Damage to any single alive monster of your choice. (damage to a monster reduces its health by 1)
Damage to all alive monsters. If at least one monster dies (ends up with 0 health points) as a result of this action, 
then repeat it (and keep repeating while at least one monster dies every time).

Spells of type 1 can be cast any number of times, while a spell of type 2 can be cast at most once during the game. 
What is the smallest number of times you need to cast spells of type 1 to kill all monsters?


2
3
3 1 2
2 1 2
2 1 1 
1 0 0
0 0 0


3 1 2
2  0 1
1 0 0
0 0 0

1 3 5
1 2 3

2 3 4

// arr
// sort(arr)
// int[]b = new int[n];

// b[0] = 1;
// b[i] min(b[i - 1] + 1, arr[i])

// ans = 0;
// int b = min(b + 1, arr[i])
// ans += a[arr[i] - b]


// sigma (a[i] - b[i])



6
4 1 5 4 1 1


int calculateMinStepsOfType1(int[] arr) {


    1 1 1 4 4 5
    1 1 1 2 3 4
    int n = arr.length;
    Arrays.sort(arr);
    int[] temp = new int[n];
    temp[0] = 1;
    int ans = 0;
    for (int i = 1; i < n; i++) {
        temp[i] = Math.min(temp[i - 1] + 1, a[i]);
    }
    for (int i = 0; i < n; i++) {
        ans += (a[i] - b[i]);
    }
    return ans;
}


Q.2 There is a matrix of m*n , of 0,1 now you need to print which all rows are the same.


0 1 1 0

              root
              0
            0   1
              1
              0



class Trie{
    Trie[] children;
    List<Integer> rows;
    Trie(){
        children = new Trie[2];
        rows = new ArrayList();
    }

    void insert(int rowNumber, List<Integer> row){
        Trie root = this;
        for (int n : row) {
            if (root.children[n] == null) {
                root.children[n] = new Trie();
            }
            root = root.children[n];
        }. 
        rows.add(rowNumber);

    }

    
}
Trie trie = new Trie();
trie.insert(0, new ArryList<>(0, 1))


0 1 1 0
0 0 0 0
0 1 1 0




Merge 100 gb of files containing numbers in sorted order  ito a single file






