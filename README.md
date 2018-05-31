# PegSolitaireSolver
Java program which solves peg solitaire game


������� 1 � Peg Solitaire

�)��������
�� peg solitaire ����� �������� ���� ������ ��� ����� ��������� ��������� �� ��� ������ �� ����. ������ ��� ���������� ����� �� ������ ��� ������ ���� ���� ��������. ���� ��������� �� ��������. �����������: http://en.wikipedia.org/wiki/Peg_solitaire

�)�������������:
�� ��� ������ �� 32 ��������� �������� �577,116,156,815,309,849,672 ������������ ���������� ��� ����������. ( 577+ �������������������� ������ ���������) �� 40,861,647,040,079,968 ������. (����: http://www.durangobill.com/Peg33.html)
���� ���������� ��� ������ �����ܻ ��� �� �������� ������� ����� ��� �� ��� ���� ������� ������ �� ����� ������ �� ���� �� ����.
�� ������������� ������ ������� � ���������� ������ �� ����� � ������ ������ ��� ����� ��� ������� ��� ������ ��� ���� ���� ���� ������ �� ��������. ��� ������ �� ������������� ���������� ��� ��� ������ �������� ����� ��� ����� �� ���������� �� ������ �����.
���� ��� ���������� ���������� ����� � ������ ��� �������� � ������ ����� ������������� ���������. ���������� �� ��� �������� ��������� ��� ���� ����� ��� �������� �� ����� ��� �������� ��� ����� ��� ����� ��� ����.
� ���������� ������� ����������� ���������� ���� ������� �� ������ ��� ����� ���� �������� ��� �� ��� ������� ���������������� ���������. ���� ����������� �� ����� ���������������(HashMap). 
������������ ��� ��� ���������� ������������ ���������� ��� ������� �������� ����������� ������������ ��������� ����������� ��� �� ������� ��� ���� ��� �������.  
�)�������� ���������:
���� ������������ ��������� � �������� ��������� ������� �� �������� �� ����� ��� �� ������ ��� ���������� ��� ��������������� ����� ���� ���������, �� ������ ��� ������������ ��������, �� ������ ��� �������� ��� ���������� ��� ������ ��� ������ ����� ��� �� ������ ��� �������� ��� ���������� ��� �������� ������ ��� ��� �������� �����. � ������ ��� ����������� ���� �� �������� ����� ������ ��� ���������� ��� ������ ��� ��� ������� �� ������ ����� �������� ����� � ������ ����� � ���������� �� t�� ��������� ��� �������. 
�)������ ������:
�� ���������� ��� �� ������� ��� ������ ������ ����� � ������� ��� �������� ��� ���������� ��� �� ����� , ���� �� �������� , � ������ ��� ���������� ����� ��� �� ������ ��� ����������� ����� �� ���� ����. 
�)����������� ���������� ��� ����������

��������������� 10 ���������� �������� ���������� ��� ������� ��������. ���� �������� ������������ �� ����� �� ��� �����������. ��� Best First Search ( BFS) ��� ��� Depth First Search (DFS). ������ ������� ���� 5 ����� ��� ��� ���� ���������. ���� �������� ������ �������� ��� ���� �������� ��� ��� ���� ��������� � ������ ��� ���������� ��� ��� ������� ��� ����� ��� �� ������ ��� ������ ��� �����������. ���� ������� ����� �������� ��� ��� ������� ������ � ������� ��� ����������� ����� ��� ��������������� �������� ����� 
Best First SearchBest First SearchDepth First SearchDepth First SearchTime ( sec ) Nodes VisitTime ( sec )Nodes VisitBoard A (44 Pegs)----Board B (38 Pegs)0.9632229 --Board C (36 Pegs)----Board D (32 Pegs)0.027112--Board E (40 Pegs)----Board F (35 Pegs)0.0269710.63361151Board G (48 Pegs)----Board H (28 Pegs)0.0572412.70818128Board I   (24 Pegs)1.05243672.51529214Board J   (6 Pegs)0.01190.00811Board K (38 Pegs)0.056246--
������������:
* �� ������������� ���������� ��� ����� ���������� ���� ��� �������������� ��� �����������.
* � �������� ��������� ������ ������ ���� ���� ������������� ��� ����������. ���� �������� ����� ������� ��� ����� ������� ��� ������������� �� ����������, ��� ���������� ��� ������ ��� �� ���.
* ����������� ���������� ����������� ������������ ��������� ����������� ��� �� ������ �� ������ �����.

��� �� ������ (board ) ���������� �� ��� ����� board ���� ������ ������.

