## Haw to start
```$xslt
mvn clean install
java -jar target/cinema-1.0-SNAPSHOT.jar 
```

## Scenario:
Hello, let's create new user:
```$xslt
create-user --firstName Bob --lastName Smith --email Bob@mail.com --birthday 1998-02-10
```
Get all users:
```$xslt
users --all
users
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


