UserController CONTROLLER
--------------------
		@PostMapping("saveUser")
		
	POST:http://localhost:8080/saveUser
	
	BODY-->RAW-->JSON

input:

1st input:
	
	{
	    	"name":"Ram",
    		"email":"Ram@gmail.com",
		"phoneNumber":9865743214,
    		"password":"1234",
	    	"role":"STAFF"
	}

output-1: 
	
	{
    	"status": 200,
    	"message": "All user datas are retrived sucessfull",
    	"data": {
            "id": 1,
            "name": "Ram",
            "email": "Ram@gmail.com",
            "phoneNumber": 9865743214,
            "password": "1234",
            "role": "STAFF",
            "foodOrders": []
		}
	}

INPUT-2:-

	{
        "name": "Tom",
        "email": "tom@gmail.com",
        "phoneNumber": 9865743215,
        "password": "1234",
        "role": "MANAGER",
    }
OUTPUT-2:-

	{
    	"status": 201,
    	"message": "Data Save Sucessfull",
    	"data": {
        	"id": 4,
        	"name": "Tom",
        	"email": "tom@gmail.com",
        	"phoneNumber": 9865743215,
        	"password": "1234",
        	"role": "MANAGER",
        	"foodOrders": null
    		}
	}

INPUT-3:-
	
	{
    	"name":"JERRY",
    	"email":"tom@gmail.com",
    	"phoneNumber":5865743215,
    	"password":"1234",
    	"role":"MANAGER"
	}
OUTPUT:-
	
	error:-
		phone number is minmum leangth is 6000000000 max lenght is 9999999999 

-----------@GetMapping("findById")------------
	URL: findById

INPUT:-

	URL: http://localhost:8080/findById?userId=1
OUTPUT:-

	{
	    "status": 200,
	    "message": "Data retrived Sucessfull",
    		"data": {
       		 "id": 1,
        		"name": "dinga",
        		"email": "dinga@gmail.com",
       		"phoneNumber": 6866543210,
        		"password": "1234",
        		"role": "ADMIN",
        		"foodOrders": []
    		}
	}

INPUT:-

	http://localhost:8080/findById?userId=6

OUTPUT:-
	
	{
    	"status": 200,
    	"message": "No such data found in the data base",
    	"data": "Given Data Not Found in the database"
	}	


---------------@GetMapping("findByNumber")----------------
	URL: 	findByNumber

INPUT:-

	http://localhost:8080/findByNumber?phoneNumber=9865743214
	
OUTPUT:-

	{
    "status": 200,
    "message": "Data retrived Sucessfull",
    "data": {
        "id": 3,
        "name": "Sita",
        "email": "sita@gmail.com",
        "phoneNumber": 9865743214,
        "password": "1234",
        "role": "STAFF",
        "foodOrders": []
    		}
    	}	

INPUT:

	http://localhost:8080/findByNumber?phoneNumber=9865743218

here phone number is not present

OUTUT:

	{
    "status": 200,
    "message": "No such data found in the data base",
    "data": "Given Data Not Found in the database"
}

------------	@GetMapping("findByEmail")--------------
	URL: 	findByEmail

INPUT:-

	http://localhost:8080/findByEmail?email=tom@gmail.com
NOTE:-

	email id is present in database.
OUTPUT:-

	{
    "status": 200,
    "message": "Data retrived sucessfull",
    "data": {
        "id": 4,
        "name": "Tom",
        "email": "tom@gmail.com",
        "phoneNumber": 9865743215,
        "password": "1234",
        "role": "MANAGER",
        "foodOrders": []
	    }
	}
INPUT:-

	http://localhost:8080/findByEmail?email=tommy@gmail.com

NOTE:-
	email id is Not present in database.

OUTPUT:-

	{
	    "status": 404,
    	"message": "Invalid EmailId",
    	"data": "Invalid Email Id. Please check your Email id."
    	}
	

INPUT:-

	http://localhost:8080/findAllUser

OUTPUT:-	
	
	{
    "status": 200,
    "message": "All user datas are retrived sucessfull",
    "data": [
        {
            "id": 1,
            "name": "dinga",
            "email": "dinga@gmail.com",
            "phoneNumber": 6866543210,
            "password": "1234",
            "role": "ADMIN",
            "foodOrders": []
        },
        {
            "id": 2,
            "name": "Ram",
            "email": "Ram@gmail.com",
            "phoneNumber": 9865743210,
            "password": "1234",
            "role": "STAFF",
            "foodOrders": []
        },
        {
            "id": 3,
            "name": "Sita",
            "email": "sita@gmail.com",
            "phoneNumber": 9865743214,
            "password": "1234",
            "role": "STAFF",
            "foodOrders": []
       	 }
    	]
	}

		