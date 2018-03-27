# Front end application

This application hosts a simple web page to accept a number input from the user, the
last N quotes of which the user wants to obtain average. The average is calculated 
by the priceseries service and the result is displayed on the same webpage. A GET 
call is made to the price series service.

* Sample url for GET call: http://localhost:8080/priceApp/getAveragePriceQuote?numberOfQuotes=5
* Launch the frontend application on http://localhost:9000/userinput
* Enter a number to obtain average, negative number input will flag a error
