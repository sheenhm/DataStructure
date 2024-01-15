# Homework #4: Recursions and Stack

## Part I.
1. 이진수를 나타내는 String을 입력으로 받아 10진수를 반환하는 recursive 함수 `BinToDec`을 작성하시오.
```agsl
inputs        outputs
"000"    -->     0
"10"     -->     2
"1010"   -->     6
"0111"   -->     7
```
```agsl
public static int binToDec(String number) {
    if (number.length() == 1) {
        // Base case
    } else {
        // Recursive case
    }
}
```

2. 이진수를 나타내는 String을 입력으로 받아 10진수를 반환하는 tail-recursive 함수 `BinToDecTR`을 작성하시오.
```agsl
public static int binToDecTR(String number, int arg) {
    if (number.length() == 1) {
        // Base case
    } else {
        // Recursive case
    }
}
```
***Note:*** For problems (1) and (2), you can make use of `Integer.parseInt("7")` static method to convert string to integer, and String’s `substring()` instance method to select substrings. Make sure to consult the corresponding Java docs for each method to understand their usages.

## Part II. Maze Solver using Recursion and Stack
In this project, you are required to develop a program that tries to find a path in a maze **using recursion**. However, in order to save a found path, you may still **need a stack to keep track of the path** up to a certain point in time. Therefore, you need to complete `ArrayStack.java` first.

The maze is represented as an `M x N` matrix. In matrix, 0’s mean a path is available, while 1’s mean the path is closed. An example of `4 x 5` maze is given below.
```agsl
int[][] maze = new int[][] {
    { 0, 0, 1, 0, 1 },
    { 0, 0, 0, 0, 0 },
    { 1, 0, 1, 0, 1 },
    { 0, 1, 0, 0, 0 }
};
```

The entry point and the exit point will be chosen randomly as long as the respective entries is 0’s. One possible choices are index positions `[0,0]` and `[M-1,N-1]`, respectively. That is, the entry point is `[0,0]` and the exit point is `[3,4]`. For the sake of simplicity, all the sample mazes are assumed to have 0’s in the entry and exit positions. The rest of the entries in the matrix will be given at random.

If a path exits, the `findPath` returns `true`, and `showPath` returns a sequence of paths from entry point to the exit point **in reverse order** as follow:
```agsl
[3,4] <-- [3,3] <-- [2,3] <-- [1,3] <-- [1,2] <-- [1,1] <-- [0,1] <-- [0,0] <-- Start
```
If no paths exist, the `findPath` returns `false`.

***유의 사항:***
1. Your program **must** search the path in the fixed order: `ease`, `west`, `south`, `north`.
2. 기존의 public methods 이외의 추가 public method는 정의 할 수 없습니다. 단, private fields와 methods는 필요 한 경우 추가로 정의하여 사용 할 수 있습니다.