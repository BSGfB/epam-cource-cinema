## Haw to start
```$xslt
mvn clean install
java -jar target/cinema-1.0-SNAPSHOT.jar 
```

## Database (Part 3)
Database schema: https://image.ibb.co/fykp1R/cinemadb.png

![alt Database schema](https://image.ibb.co/fykp1R/cinemadb.png)


## AOP Scenario (Part 2)
Create new user
```
create-user --firstName Bob --lastName Smith --email Bob@mail.com --birthday 1998-02-10
```

Lets sign in
```$xslt
sign-in --email Bob@mail.com
```
Then book some tickets:
```$xslt
book-ticket --eventId 1 --time 2017-10-01T18:30 --seats 5
book-ticket --eventId 1 --time 2017-10-01T18:30 --seats 10
book-ticket --eventId 1 --time 2017-10-01T18:30 --seats 15
```
Then we can check user's messages for win free tickets:
```
users
```
Or we can check discounts, that we have got early
```
stat-disk
```
Then we can request event by name or get event's price by name:
```
events --name Kingsman\ 2
event-price --name Kingsman\ 2
```
See result:
```
stat-count
```

sign-in --email Siarhei_Blashuk@epam.com
book-ticket --eventId 0 --time 2017-10-01T18:30 --seats 10

events --name Kingsman\ 2
event-price --name Kingsman\ 2


## Scenario (1 part):

Hello, let's create new user:
```$xslt
create-user --firstName Bob --lastName Smith --email Bob@mail.com --birthday 1998-02-10
```
Get all users:
```$xslt
users --all
users
```

We need admin privileges to create some staff:
```
sign-in --email Siarhei_Blashuk@epam.com
```

Then we can add new Auditorium:
```$xslt
create-auditorium --name Vip --seats 10 --vip 1,2,3,4,5
```
Get all auditoriums:
```$xslt
auditoriums --all
auditoriums
```

After let's go create Event:
```$xslt
create-event --name Star_Wars_9 --rating HIGH --basePrice 5
```
Then we need to add auditorium to event:
```$xslt
add-event-auditorium --eventId 1 --auditoriumId 2 --time 2017-02-11T18:30:00
```
Now, we can see our event with auditory:
```$xslt
events --all
events
```

After all we want buy some tickets
First, you need sign in
```$xslt
sign-in --email Bob@mail.com
```
Second, let's buy tickets
```$xslt
book-ticket --eventId 1 --time 2017-02-11T18:30:00 --seats 10
```
Check our tickets:
```$xslt
who-am-i
```
And log out
```$xslt
log-out
```


jenkins 1