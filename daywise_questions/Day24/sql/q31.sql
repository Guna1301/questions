/* Find employees who belong to a department with no employees.

Expected Output Columns:
------------------------
+---------+
| dname   |
+---------+

*/
USE test;
select d.dname from dept d left join emp e on d.deptno = e.deptno where e.ename is null group by d.deptno; 