/*
Identify food items that were ordered more than 3 times in total across all orders.
Display food item names along with their total order counts, sorted from most to 
least popular and by food name in ascending order .


Sample output:
--------------
[
  { _id: 'F103', orderCount: 7, food_name: 'Hyderabadi Biryani' },
  { _id: 'F109', orderCount: 7, food_name: 'Gulab Jamun' }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.FoodItems.aggregate([
        {
            $lookup:{
                "from":"Orders",
                "localField":"food_id",
                "foreignField":"food_items.food_id",
                "as":"order"
            }
        },{
            $unwind:"$order"
        },{
            $group:{
                _id:"$food_id",
                "orderCount":{
                    $sum:1,
                },
                "food_name":{
                    $first:"$name"
                }
            }
        },{
           $match:{
               "orderCount":{
                   $gt:3
               }
           } 
        },
        
        {
            $project:{
                "_id":1,
                "orderCount":1,
                "food_name":1
            }
        },{
            $sort:{
                "orderCount":-1,
                "food_name":1
            }
        }
    ])
)
