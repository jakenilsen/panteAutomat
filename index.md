# Oslo Technical Interview Assignment

Congratulations! You have reached the technical part of interviewing for a position at Sonat Consulting Oslo.

Below, you'll find a description of a system we'd like you to design and implement before a technical review. Please
submit the project, e.g. in a git bundle, at least two (2) days before the interview. That way the interviewers will have
some time to familiarize themselves with the solution in advance.

Good luck!

## Task Details

We'd like you to implement can/bottle deposit machine, like those at many norwegian grocery stores. This is a machine
where you turn in bottles/cans for recycling, while receiving a voucher for the returned value for you to use at the store.

You are free to choose how you want present/implement the UI.

### Requirements

The product owner has put together the following list of user stories for this application.

> #### RS-001
> **As** a drinks' manufacturer,  
> **I'd like** the system to accept empty bottles  
> **So that** we can reacquire used bottles for recycling.

> #### RS-002
> **As** a drinks' manufacturer,  
> **I'd like** the system to accept empty cans,  
> **So that** we can reacquire used cans for recycling.

> #### RS-003
> **As** a customer,  
> **I'd like** the system to give me a voucher as a reward for turning in bottles,  
> **So that** I can buy stuff

> #### RS-004
> **As** a drinks' manufacturer,  
> **I'd like** a backend system to log whenever a bottle/can is turned in or a voucher is printed,  
> **So that** we can report the efficiency of each recycling station.

#### Specifications and Clarifications 

* *Cans* are valued at 2 NOK each.
* *Bottles* are valued at 3 NOK each.
* Inputting a bottle/can should be represented through a UI element, e.g. a button or a console prompt.
* For the purposes of this application, it is enough to present the printed voucher on screen next to the bottle inputs.
* You can assume that all containers have a valid sticker on it for valid identification.
* The hardware running on the recycling machine is able to process a can at a rate of 0,5 per second and for the plastic bottles it's 1 per second.

#### Technology and Architecture

You choose the architecture, design, programming language and technology you would like to use to solve this assignment.
However, keep in mind that it might be beneficial that these are somewhat related to the technologies you'd like us to
view as your strengths.

## Review Considerations

If you make any assumptions while building your solution, please present these in a file `README.md` located at the root level of your project.

Reviewers will evaluate your program both from a functional and technical standpoint. Depending on your background,
some parts will be weighted as more important than others.  Also, be prepared that we might ask you to refactor,
fix bugs and or implement additional functionality during the interview. If we do, however, we will help you as much as we can.
