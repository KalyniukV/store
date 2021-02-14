[![CircleCI](https://circleci.com/gh/KalyniukV/store.svg?style=svg)](https://circleci.com/gh/KalyniukV/store)

# store
Test store application

# Getting Started

To start application follow the next steps:
1. Run the script "msql-init.sql" for the mysql database. Which is in the catalog: store\src\main\scripts
1. Use Maven to build the program (mvn clean package).
1. Run the file "store.jar"  from the command line (java -jar store.jar). That was created after build. Which is in the catalog: store\target

The program contains tests that are performed during the building of the program. Tests are in the catalog store\src\test\java\com\example\store

# Application should provide such operations:

| Description | Command |
| ----------- | ------- |
| Create Product | products create |
| Create Order with a list of the products specified by id | orders create |
| Update Order Entries quantities | orders update_quantity |
| Show all products | products show_all |
| List all products, which have been ordered at least once, with total ordered quantity sorted descending by the quantity | products show_in_order |
| Show orders with price, products, total products quantity | orders show_orders_entry |
| List all orders | orders show_orders_info |
| Remove product by ID / Remove all products | products remove |
   
`In program for price and quantity use numbers without floating-point (only int)`