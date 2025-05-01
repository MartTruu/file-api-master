# Summary

## Comments

The assignment was very clear. The best part for me was the completeness of the project: data storing, data manipulation and an API for a user interface potential.

## Which part of the assignment took the most time and why?

Unfortunately the learning curve was steep on every step of the project for me: Docker, Maven, Spring Boot, Kotlin, tests.

This lead to time deficit. It was clear that I need more time to get some projects under my belt before realizing the accumulated knowledge in the next test assignment. So I was at a descision-making point:
1. do I cut the losses early and just admit this to the employer and avoid the potential embarrassment
2. do I still make the most of it with the task given to me

I chose the second route and used different prompts with ChatGPT to generate the API part for the project.

How is practically "vibe coding" supporting this idea?
This allowed me to get more hands on with the functional part of the API and get familiar with Spring Boot and Kotlin with something at least partly working.

So I got to compare the practical part with the code structure.

## What You learned

Oh, so much! There were 10 active days which amounts to about 20 hours.

Here is the repository with my contribution: https://github.com/MartTruu/file-api-master

The first half of the time went by very fast with on Docker documentation, tutorials and tinkering in CLI.

This all amounted to a new technical crush (DevOps seems to be something close to my heart) and seemingly modest branch merge on 2025-04-29: https://github.com/MartTruu/file-api-master/commit/1c69bd4171a6f5d82ded53604fa79c20b3f6d810

Then I watched through a couple of "get started" videos about Spring Boot and Java. Because the amount of learning materials about Spring Boot and Java is understandably huge.

The idea was to then turn the restored or gained knowledge into Kotlin context, but as the saying goes: "use it or lose it",  so I basically ended up trying to learn two languages simultaneously... Not too clever, if the goal is not to write "Hello, world" in 50 languages.

The inevitable skill-issue snowball rolled too big. This lead to the decision of getting the API part running, so I could focus on learning by practically reading the code with explanations.

The generated code was merged on 2025-05-01: https://github.com/MartTruu/file-api-master/commit/bd347f96a5179df1861daf05e439451094dc9959

## TODOs

1. MongoDB connection and database runtime validation. Data manipulation is not working in the current state of the app. 
2. Volume sharing to keep the data after container shut-downs. The given filedb-data volume configuration needs attention for this.
3. Writing tests. I currently have no experience with it and sadly didn't manage to gain any with this project as well, although the opportunity was there.

As I still have the assessment with all the components, I think I will continue to tinker with it. It can serve as a study platform for me. 

## Starting the app
Just run `docker-compose up` and use endpoint `http://localhost:6011/` for API calls, for which the spec is available at `http://localhost:6011/docs`.