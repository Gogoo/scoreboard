# Football World Cup Scoreboard

## Overview
Live Football World Cup Scoreboard library shows all the ongoing matches and their scores.

## Table of Contents
- [About the Project](#about-the-project)
- [Technical requirements](#technical-requirements)
- [Source code](#source-code)

## About the Project

The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
This should capture following parameters:
a. Home team
b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
same total score will be returned ordered by the most recently started match in the
scoreboard.

For example, if following matches are started in the specified order and their scores
respectively updated:
a. Mexico 0 - Canada 5
b. Spain 10 - Brazil 2
c. Germany 2 - France 2
d. Uruguay 6 - Italy 6
e. Argentina 3 - Australia 1

The summary should be as follows:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

## Technical requirements
To build the project you will need Java 11 and Maven build tool.

## Source code
1. Clone the repo:
   ```bash
   git clone https://github.com/username/project.git
