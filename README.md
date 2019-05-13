# Clojure-Sales-Order-Application
Comp 6441 (Comparative study of programming language) Assignment-2 in functional programming language Clojure.
See A2.pdf for better understanding of problem statement.

You will provide the following menu to allow the
user to perform actions on the data:
*** Sales Menu ***
------------------
1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit
Enter an option?
The options will work as follows
1. You will display the contents of the Customer table. The output should be similar to the
following:
1: ["John Smith" "123 Here Street" "456-4567"]
2: ["Sue Jones" "43 Rose Court Street" "345-7867"]
3: ["Fan Yuhong" "165 Happy Lane" "345-4533"]
Note that exact formatting does not matter. You can use commas as separators or round
brackets instead of square brackets. The important thing is that each record lists the ID,
followed by the data associated with the ID. Records should be sorted by ID.
2. Same thing for the prod table (sorted by Product ID)
3. The sales table is a little different. ID values aren’t very useful for viewing purposes, so the
custID should be replaced by the customer name and the prodID by the product description,
as follows:
1: ["John Smith" "shoes" "3"]
2: ["Sue Jones" "milk" "3"]
3: ["Sue Jones" "shoes" "1"]
4: ["Fan Yuhong" "jam" "4"]
Again, the list should be sorted by Sales ID.
4. For option 4, you will prompt the user for a customer name. You will then determine the
total value of the purchases for this customer. So for Sue Jones you would display a result
like:
Sue Jones: $20.90
This represents 1 pair of shoes and 3 cartons of milk (in our simple example).
5. Here, we do the same thing, except we are calculating the sales count for a given product. So,
for shoes, we might have:
Shoes: 4
This represents three pairs for John Smith and one for Sue Jones.
6. Finally, if the Exit option is entered the program will terminate with a “Good Bye” message.
Otherwise, the menu will be displayed again.
