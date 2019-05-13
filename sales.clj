(def file_lines "")

(def cust_file "cust.txt")
(def prod_file "prod.txt")
(def sales_file "sales.txt")

(def cust_ID_Name_Map {})
(def cust_ID_Info_Map {})

(def prod_ID_Item_Map {})
(def prod_ID_Cost_Map {})

(def sales_ID_custID_Map {})
(def sales_ID_prodID_Map {})
(def sales_ID_ItemCount_Map {})

(def total_Sales_cust_Map {})
(def total_product_count_Map {})
(def final_total_Sales_cust_Map {})
(def final_total_product_count_Map {})


;method for reading txt file
(defn getLineFromFile [file]
  (def file_lines (clojure.string/split-lines (slurp file)))
  )

;reading data from cust.txt file and storing in maps
(getLineFromFile cust_file)

(doseq [i file_lines]

  (def row (clojure.string/split i #"\|"))

  (def cust_ID_Name_Map (assoc cust_ID_Name_Map (get row 0) (get row 1)))
  (def custInfo (str ":[" (get row 1) "," (get row 2) "," (get row 3) "]"))
  (def cust_ID_Info_Map (assoc cust_ID_Info_Map (get row 0) custInfo))
    
  )

;reading data from prod.txt file and storing in maps
(getLineFromFile prod_file)

(doseq [i file_lines]

  (def row (clojure.string/split i #"\|"))

  (def prod_ID_Item_Map (assoc prod_ID_Item_Map (get row 0) (get row 1)))
  (def prod_ID_Cost_Map (assoc prod_ID_Cost_Map (get row 0) (read-string (get row 2))))

  )

;reading data from sales.txt file and storing in maps
(getLineFromFile sales_file)

(doseq [i file_lines]

  (def row (clojure.string/split i #"\|"))

  (def sales_ID_custID_Map (assoc sales_ID_custID_Map (get row 0) (get row 1)))
  (def sales_ID_prodID_Map (assoc sales_ID_prodID_Map (get row 0) (get row 2)))
  (def sales_ID_ItemCount_Map (assoc sales_ID_ItemCount_Map (get row 0) (read-string (get row 3))))
  )

(def sort_sales_ID_custID_Map (into (sorted-map) sales_ID_custID_Map))


(doseq [[k v] (map vector (keys sort_sales_ID_custID_Map) (vals sort_sales_ID_custID_Map))]

  (def produtId (get sales_ID_prodID_Map k))
  (def productName (get prod_ID_Item_Map produtId))
  (def custName (get cust_ID_Name_Map v))
  (def producPrice (get prod_ID_Cost_Map produtId))
  (def productCount (get sales_ID_ItemCount_Map k))
  (def purchase (* producPrice productCount))
  (def totalPurchases (+ purchase (get total_Sales_cust_Map custName 0)))
  (def total_Sales_cust_Map (assoc total_Sales_cust_Map custName totalPurchases))

  (def total_product_count (+ productCount (get total_product_count_Map productName 0)))
  (def total_product_count_Map (assoc total_product_count_Map productName total_product_count))

  )


(doseq [[k v] (map vector (keys cust_ID_Name_Map) (vals cust_ID_Name_Map))]
        (def final_total_Sales_cust_Map (assoc final_total_Sales_cust_Map v (get total_Sales_cust_Map v 0)))
)

(doseq [[k v] (map vector (keys prod_ID_Item_Map) (vals prod_ID_Item_Map))]
        (def final_total_product_count_Map (assoc final_total_product_count_Map v (get total_product_count_Map v 0)))
)

;for option 1
(defn displayCustomerTable[]
    (def sort_cust_ID_Name_Map (into (sorted-map) cust_ID_Name_Map))
    (doseq [[k v] (map vector (keys sort_cust_ID_Name_Map) (vals sort_cust_ID_Name_Map))]
        (println (str k (get cust_ID_Info_Map k)))
    )
)

;for option 2
(defn displayProductTable[]
    (def sort_prod_ID_Item_Map (into (sorted-map) prod_ID_Item_Map))
    (doseq [[k v] (map vector (keys sort_prod_ID_Item_Map) (vals sort_prod_ID_Item_Map))]
        (println (str k ":[" v "," (get prod_ID_Cost_Map k) "]"))
    )
)

;for option 3
(defn displaySalesTable[]
    
    (doseq [[k v] (map vector (keys sort_sales_ID_custID_Map) (vals sort_sales_ID_custID_Map))]

        (def produtId (get sales_ID_prodID_Map k))
        (def productName (get prod_ID_Item_Map produtId))
        (def dispSalesTab (str k ":[" (get cust_ID_Name_Map v) "," productName "," (get sales_ID_ItemCount_Map k) "]"))
        (println dispSalesTab)
    )
)

;for option 4
(defn totalSalesforCustomer[]
    
    (println "Please enter customer name.")
    (def custName (read-line))
    
    (if (contains? final_total_Sales_cust_Map custName)
        (do (println (str custName ": $"(get final_total_Sales_cust_Map custName)))
            true)
        (do (println "You enter wrong customer name.")
            (totalSalesforCustomer))
    )
)

;for option 5
(defn totalCountforProduct[]
    
    (println "Please enter product name.")
    (def productName (read-line))
    
    (if (contains? final_total_product_count_Map productName)
        (do (println (str productName ": "(get final_total_product_count_Map productName)))
            true)
        (do (println "You enter wrong product name.")
            (totalCountforProduct))
    )
)

;for option 6
(defn exitProgram[]
    (println "Good Bye..!!")
    (System/exit 0)
)


(defn displayMenu []

  (def salesMenu (str "*** Sales Menu ***\n------------------\n\n1. Display Customer Table\n2. Display Product Table\n3. Display Sales Table\n4. Total Sales for Customer\n5. Total Count for Product\n6. Exit\n\nEnter an option?"))
  (println salesMenu)
    
    (let [userOption (read-line)]
  (case userOption
    "1" (displayCustomerTable)
    "2" (displayProductTable)
    "3" (displaySalesTable)
    "4" (totalSalesforCustomer)
    "5" (totalCountforProduct)
    "6" (exitProgram)
    (println "You enter wrong option..! Please select correct option.")))
    
    (displayMenu)
)


(displayMenu)


