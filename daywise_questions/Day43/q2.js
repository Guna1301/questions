/*
Write a MongoDB aggregation query to display each patient's total billed 
amount across all their visits.

Sample output:
--------------
[
  { totalBilledAmount: 200, patientId: 'PT005', name: 'Patient 5' },
  { totalBilledAmount: 200, patientId: 'PT006', name: 'Patient 6' },
  { totalBilledAmount: 200, patientId: 'PT001', name: 'Patient 1' }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.billing.aggregate([
            {
                $group:{
                    _id:"$patientId",
                    totalBilledAmount:{
                            $sum:"$totalAmount"
                        },
                }
            },
            {
                $lookup:{
                    from:"patients",
                    localField:"_id",
                    foreignField:"patientId",
                    as:"info"
                }
            },{
                $project:{
                    _id:0,
                    patientId:"$_id",
                    name:"$info.name",
                    totalBilledAmount:1
                }
            },{
                $sort:{
                    name:1
                }
            }
        ])
)
