# panteAutomat
A machine for recycling cans and bottles. The user can print a money voucher based on how much has been recycled.

## System
**Java version:** 17 \
**Spring Boot version:** 3.0.4 \
**Builder:** Maven \
**Dependencies:** 
- Spring-boot-starter
- Spring-boot-starter-web
- Bucket4j
- Spring-boot-starter-test

## Running the program
To run the program first run
`mvn clean install`.

Then you just need to run the PanteautomatApplication class in the IDEA or run `mvn spring-boot:run`, open up index.html and start recycling!

## Testing
There are several unit tests in place to cover that the code does as it should, 
one testing class is for the Recycling machine controller, and one is for the RecyclingMachineService class.

## Additions
After conducting a small user survey and by doing some research online, 
I have taken the liberty of adding some extra functionality I thought might come in handy.
These functionalities can easily be removed without it affecting the original user stories if the product owner does not like it.

Added functionalities include:
- Voucher history: Can get a list of all vouchers that have been printed
- Possibility to add non-valued recycable objects: If you f.ex have cans or bottles that does not have a recycle value in your country. (Only 1 per 2 seconds)
- Lottery ticket: The user can choose to donate to charity and get a lottery ticket. If the user wins, they get prize money.