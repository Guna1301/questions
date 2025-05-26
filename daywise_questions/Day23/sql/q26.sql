/* Find departments that have at least one employee earning more than 3000.

Expected Output Columns:
------------------------
+--------+------------+
| deptno | dname      |
+--------+------------+

*/
USE test;
select e.deptno, d.dname from emp e join dept d on e.deptno = d.deptno where e.sal > 3000 group by e.deptno having count(*)>=1;