# It-Support
I have implemented below features in Api:

1:As a support agent I want to see a list of all tickets.
2:As a support agent I want to see all the information about a ticket including all comments in a
detail view
3:As a support agent I want to add comments to an existing ticket, so that I can ask the customer
about missing information or give them feedback
4:As a support agent I want to update the status of a ticket and add more information to it in a
comment at the same time, so that the customer can see the status and understands it
• In queue
• In progress
• Resolved

_________________________________________________________________________

Output is Docker image and below is steps to configure it

First I created a mysql container then linked it to springboot application.
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=ticket -e MYSQL_USER=sa -e MYSQL_PASSWORD=root123 -d mysql:latest

build the project in project folder and it creates an image.
docker build . -t itsupportapi

then run the application linking mysql application.
docker run -p 8085:8085 --name itsupportapicontainer --link mysql-standalone -d itsupportapi

Run the application and open the Below URL:

http://localhost:8085/ticket/swagger-ui/index.html

