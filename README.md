# Java_Parking_Garage
Side-Project Java command line app that models a parking garage management system 

Java Program to Model Parking Garage Management Parking lot Java project This command line program models a parking garage.

Parking Garage description 4 floors 40 spots per floor Prices are $5 an hour up to $25 a day Available spaces are displayed Prints ticket when entering Prints recipe when exiting Logs all sales

Data base tables: SPACE Space_id (PK) serial floor_id Occupied Vehicle (FK) VEHICLE License_plate (PK) String Color String Type String Space_id (FK) int Arrival_time Timestamp SALES Sale_id (PK) serial Space_id (FK) Amount int Time_hours Int License_plate String Logic

Program loads and Displays Menu & available spaces by floor Option1—Arriving car—user is prompted to enter vehicle license plate, color, type, arrival time and space, if a space isn’t specified it defaults to the first unoccupied space. When the information is submitted, the program returns a ticket with license_plate, type, spot and arrival time and price guide. Push all data to respective database table (User can type exit at any time and return to main menu)

Option2—Exiting Car— user is prompted to enter the license plate and exit time of vehicle. Upon submit, vehicle is removed from database. Receipt is printed with Space_id, vehicle, time, amount. Data pushed to database. Option3—List of current cars— displays a list of all vehicles in the garage. Option4—Sales— displays total sales, total sale amount and a list of all sales Option5—Exit— program ends
