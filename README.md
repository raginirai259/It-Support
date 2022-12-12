# It-Support

We would like you to implement the backend API of an IT-support ticket system which is being used
by the IT-support agents. The main object of the system is the support ticket which contains
information about the case. Each support ticket can have multiple comments added by the support
agent. The tickets have a lifecycle that contains the following states:
• In queue: new tickets that were submitted by a customer but not picked up by a support
agent yet
• In progress: When a support agent has started working on a ticket, they move it to this state
• Resolved: When a ticket is resolved/done
The output should be a containerized service with REST API that is production ready. We don't
expect you to implement any security on the API, neither do you need to provide a CI/CD pipeline
setup or deployment configuration.
Expected technical set-up:
• Implemented in Java using spring boot
• Well designed SQL Database
• The output should be a docker image
Requirements:
As a support agent I want to see a list of all tickets of any customer/company
As a support agent I want to see all the information about a ticket including all comments in a
detail view
As a support agent I want to add comments to an existing ticket, so that I can ask the customer
about missing information or give them feedback
As a support agent I want to update the status of a ticket and add more information to it in a
comment at the same time, so that the customer can see the status and understands it
• In queue
• In progress
• Resolved

