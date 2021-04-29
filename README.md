# Trackmania COTD App

DISCLAIMER! This project is not related to Nadeo! Its a fan made project to generate and display a leaderboard for the Trackmania Cup of the Day.

## Used technologies

* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a> (my own API). See documentation <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">here</a>.
* <a href="https://trackmania.io/#/totd">trackmania.io</a> API from <a href="https://github.com/codecat">@CodeCat</a>
* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> collecting data from trackmanio.io and imports it into my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>
* <a href="https://github.com/PhilJay/MPAndroidChart">MPAndroidChart</a> to display the pie chart inside the player detail fragment
* <a href="https://github.com/SoWieMarkus/DefaultAppComponents">DefaultAppComponents</a>

## How does it work

### Get results from Cup of the Day

My <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> is running continuously on a Raspberry Pi. Every 10 minutes it gets the results of the most recent Cup of the Day from <a href="https://trackmania.io/#/cotd">trackmania.io</a> and sends the data to my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>. 

Check out the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot#readme">trackmania.io havesting bot documentation</a> and the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">API documentation</a>

### Get information about the Track of the Day





## Credits
