/*

Find the names of products that are only ordered by customers who live in a
specific city (e.g., 'New York')

Sample Output:
==============

Name                                                                                                                    
Keyboard                                                                                                          
                                                                                            


Customers:
==========
Field   Type    Null    Key     Default Extra                                                                           
CustomerID      int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Email   varchar(255)    YES             NULL                                                                            
Address varchar(255)    YES             NULL                                                                            
PhoneNumber     varchar(20)     YES             NULL                                                                    

Orders:
=======
Field   Type    Null    Key     Default Extra                                                                           
OrderID int     NO      PRI     NULL                                                                                    
CustomerID      int     YES     MUL     NULL                                                                            
OrderDate       date    YES             NULL                                                                            
TotalCost       decimal(10,2)   YES             NULL                                                                    
Status  varchar(20)     YES             NULL                                                                            

OrderItems:
============
Field   Type    Null    Key     Default Extra                                                                           
OrderItemID     int     NO      PRI     NULL                                                                            
OrderID int     YES     MUL     NULL                                                                                    
ProductID       int     YES     MUL     NULL                                                                            
Quantity        int     YES             NULL                                                                            
UnitPrice       decimal(10,2)   YES             NULL                                                                    

Products:
=========
Field   Type    Null    Key     Default Extra                                                                           
ProductID       int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Description     varchar(255)    YES             NULL                                                                    
Price   decimal(10,2)   YES             NULL  


*/

use fs;

-- Write your query below.
select distinct p.Name from Products p
join OrderItems oi on oi.productID = p.productID
join Orders o on o.orderID = oi.orderID
join Customers c on c.customerID = o.customerID
where address like "%New York%"
and p.productID not in (
    select p2.productID from Products p2
    join OrderItems oi2 on oi2.productID = p2.productID
    join Orders o2 on o2.orderID = oi2.orderID
    join Customers c2 on c2.customerID = o2.customerID
    where c2.address not like "%New York%"
)