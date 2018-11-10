# optimalSequence
optimalSequence

Implement and test the following algorithm which computes an optimal sequence for performing   a set of tests that does not violate a given precedence constrains on the tests sequencing. The precedence constraints are represented by an array of disjoined linked lists of test, where   the ith   test in a linked list is the immediate successor of the (i-1)th  test in the same linked list. For each test, , there are  cost of testing  i and probability of positive result . An optimal sequencing of tests is based on the minimizing the expected   testing cost   It has been proven that the following Greedy algorithm  provides an optimal sequencing of tests.
Assume the array of linked lists is dented by L, where L[i][k] is the kth test in the linked list L[i] and the its immediate predecessors are L[i}{0}, L[i}[1],….L[i][k-1].  Let C[i][k] and P[i][k] be the cost and probability associated with the test L[i][k]

                  The Algorithm 

1.  Let OP denotes an optimal sequence and set it to the empty string;

2.  While ( the linked lists are not completely empty), Do the following four steps:

3.  For each test L[i][j], compute the ratio R[i][j] as follows:
        R[i][j] =  ((∑_(k=0)^j▒〖C[i][k]〗))/((∑_(k=0)^j▒〖P[i][k]〗) )
        
4.  Choose the test with the smallest R ratio and has no predecessor test with the same R ratio;

5.  Add this test with its predecessors tests to the optimal sequence OP;

6.  Remove this test and its predecessors from its linked list.

7.  Return OP.

