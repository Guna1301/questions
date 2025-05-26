/* Get clerks or analysts hired between 1996 and 2000

Expected Output Columns:
+------+-------+------------+
| ID   | Name  | StartDate  |
+------+-------+------------+

*/
USE test;
select empno as "ID", ename as "Name", hiredate as "StartDate" from emp where (job="CLERK" or job="ANALYST") and (year(hiredate) between 1996 and 2000);