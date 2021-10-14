<img src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/cotd_no_bg.png">

# Trackmania COTD App

**DISCLAIMER! This project is not related to Nadeo! Its a fan made project to generate and display a leaderboard for the Trackmania Cup of the Day.**

This project is based on the work of <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a>! Unfortunately he stopped working on this project.

<a href="https://play.google.com/store/apps/details?id=markus.wieland.unofficalcupoftheleaderboard">
        <img src="https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg" alt="Build status" width="50%">
 </a>

## Special thanks

* <a href="https://github.com/codecat">@CodeCat</a> for letting me use the trackmania.io API
* <a href="https://www.twitch.tv/granadyy">GranaDy</a> for letting me use his "granaBonk" emote inside my app. Make sure to check out his Twitch channel for Cup of the Day WINNER POV and some juicy WHEEL action.
* <a href="https://github.com/Bmandk">@Bmandk</a> and axelalex2 for helping me debug the Trackmania style parser ^^
* <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a>
* \_\_mo\_\_ for the header (check out his <a href="https://docs.google.com/spreadsheets/d/1I79FPd5cly52nMUY38zEWzhDK0bwW0EXbTsPZrF4JxE/edit#gid=217730040">leaderboard</a>)

## Screenshots

<img width="20%" src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/english/Screenshot_20210429-133205_Cup%20of%20the%20Day%20Leaderboard.jpg"><img width="20%" src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/english/Screenshot_20210429-133220_Cup%20of%20the%20Day%20Leaderboard.jpg"><img width="20%" src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/english/Screenshot_20210429-133230_Cup%20of%20the%20Day%20Leaderboard.jpg"><img width="20%" src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/english/Screenshot_20210429-133249_Cup%20of%20the%20Day%20Leaderboard.jpg"><img width="20%" src="https://github.com/SoWieMarkus/TrackmaniaCOTDApp/blob/main/screenshots/english/Screenshot_20210429-133327_Cup%20of%20the%20Day%20Leaderboard.jpg">

## Questions?

Feel free to contact me!

* Twitter: <a href="https://twitter.com/SoWieMarkus">@SoWieMarkus</a>
* Discord: Markus#2348

## Used technologies

* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a> (my own API). See documentation <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">here</a>.
* <a href="https://trackmania.io/#/totd">trackmania.io</a> API from <a href="https://github.com/codecat">@CodeCat</a>
* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> collecting data from trackmania.io and imports it into my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>
* <a href="https://github.com/PhilJay/MPAndroidChart">MPAndroidChart</a> to display the pie chart inside the player detail fragment
* <a href="https://github.com/SoWieMarkus/DefaultAppComponents">DefaultAppComponents</a>

## How does it work

I am using the results of the Cup of the Day from trackmania.io. There are some days missing or incomplete. Those dates are:

* 10.12.2020, reason unknown
* 04.01.2021, reason unknown. GranaDy said on this day you were able to vote.
* 27.-28.01.2021 Server issues
* 29.-30.01.2021 reason unknown
* 08.09.2021 Server issues

I did not correct any of these results! They are still as you can find them on trackmania.io. There are other leaderboards who corrected those days by hand. This can lead to different results.

I am using the point system used by <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a> for creating the leaderboard.

### Get results from Cup of the Day

My <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> is running continuously on a Raspberry Pi. Every 10 minutes it gets the results of the most recent Cup of the Day from <a href="https://trackmania.io/#/cotd">trackmania.io</a> and sends the data to my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>. There the new results will be processed and the leaderboard will be updated.

Check out the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot#readme">trackmania.io havesting bot documentation</a> and the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">API documentation</a>.

### Get information about the Track of the Day

The app is using the API of <a href="trackmania.io">trackmania.io</a> by <a href="https://github.com/codecat">@CodeCat</a>. If you want to use this API for your own projects, <a href="https://github.com/codecat">@CodeCat</a> allowes the usage of the API under the following conditions:

* The API is unsupported. If CodeCat removes/changes something, CodeCat will (likely) not give notice of it, and you'll have to deal with it yourself.
* The API is undocumented. You're on your own to figure out how the API works and what you need from it.
* Use a proper user agent header so that CodeCat can see how you're using the API.
* There are no hard limitations in place, so please be respectful of CodeCat's server resources as well as Nadeo's. Cache stuff! Please let CodeCat know if you're planning to do any kind of "bulk" requests!
* Let CodeCat know what you're working on! CodeCat is always curious and it lets CodeCat understand how people are using the API and who to contact about certain things if any contact is needed.
* If you're still unsure about your use of the API, feel free to DM Miss#8888 with any further questions. 
* To stay up to date you can also join the <a href="https://discord.gg/a4JBRM2Q">trackmania-io Discord Server</a>. Sometimes CodeCat will post updates there!

As I said earlier, the API can change. It is therefore very likely that this documentation is not up to date. I still try to keep it up to date!

#### Get all tracks of a the current month

GET `https://trackmania.io/api/totd/0`
Example: <a href="https://trackmania.io/api/totd/0">TOTD of current month</a>

```javascript
{
        campaignid: 11545,
        map: {
                author: "d46fb45d-d422-47c9-9785-67270a311e25",
                name: "Perfecto Bonko Mapo",
                mapType: "",
                authorScore: 41216,
                goldScore: 44000,
                silverScore: 50000,
                bronzeScore: 62000,
                collectionName: "Stadium",
                environment: "Stadium",
                filename: "Perfecto Bonko Mapo.Map.Gbx",
                isPlayable: true,
                mapId: "a6dffa8a-4b5a-4c7b-bf23-0826eb5e081d",
                mapUid: "ogZoUILPyiBb7xknMQJVnSaKBa",
                submitter: "d46fb45d-d422-47c9-9785-67270a311e25",
                timestamp: "2021-06-21T20:36:26+00:00",
                fileUrl: "https://prod.trackmania.core.nadeo.online/storageObjects/b561acab-838d-4cba-9434-ffa91f21f378",
                thumbnailUrl: "https://prod.trackmania.core.nadeo.online/storageObjects/3236113e-599b-4b4a-a083-992836368dc1.jpg",
                authorplayer: {
                        name: "eLconn21",
                        tag: "$900L$A00O$C00U$D00D",
                        id: "d46fb45d-d422-47c9-9785-67270a311e25",
                        zone: {
                                name: "Středočeský kraj",
                                flag: "Středočeský kraj",
                                parent: {
                                        name: "Czechia",
                                        flag: "CZE",
                                        parent: {
                                                name: "Europe",
                                                flag: "europe",
                                                parent: {
                                                        name: "World",
                                                        flag: "WOR"
                                                }
                                        }
                                }
                        }
                },
                submitterplayer: {
                        name: "eLconn21",
                        tag: "$900L$A00O$C00U$D00D",
                        id: "d46fb45d-d422-47c9-9785-67270a311e25",
                        zone: {
                                name: "Středočeský kraj",
                                flag: "Středočeský kraj",
                                parent: {
                                        name: "Czechia",
                                        flag: "CZE",
                                        parent: {
                                                name: "Europe",
                                                flag: "europe",
                                                parent: {
                                                        name: "World",
                                                        flag: "WOR"
                                                }
                                         }
                                }
                        }
                },
                exchangeid: 31272
        },
        weekday: 3,
        monthday: 1,
        leaderboarduid: "37d0910f-2a9a-451b-9758-f69b739ed4a9"
},,
  ...
  ]
}

```

#### Get all tracks of a specific month

GET `https://trackmania.io/api/totd/<index>`
Example: <a href="https://trackmania.io/api/totd/0">TOTD of current month</a>
Example: <a href="https://trackmania.io/api/totd/1">TOTD of last month</a>

0 is always the index of the current month. If you want to get months from the past you have to increment the index. Index 1 will be the month before the current month and so on.

#### Get time leaderboard of a specific map

With the `mapUid` and the `leaderboarduid` you can get the best times of this map.

GET `https://trackmania.io/api/leaderboard/<leaderboarduid>/<mapUid>`
Example: <a href="https://trackmania.io/api/leaderboard/37d0910f-2a9a-451b-9758-f69b739ed4a9/ogZoUILPyiBb7xknMQJVnSaKBa">Leaderboard of the example TOTD/a>

```javascript
tops: [
        {
                player: {
                        name: "JakeRay1995",
                        tag: "$N $Z$S$FFFD$F00K$N $Z",
                        id: "dcf4dd03-7801-4fab-af0c-63854970b3de",
                        zone: {
                                name: "Denmark",
                                flag: "DEN",
                                parent: {
                                        name: "Europe",
                                        flag: "europe",
                                        parent: {
                                                name: "World",
                                                flag: "WOR"
                                        }
                                }
                        },
                        meta: {
                                twitch: "jakeray",
                                youtube: "UCiwpOv6yWbWlcx0CczebZfw"
                        }
                },
                position: 1,
                time: 42670,
                points: 0,
                filename: "Replays\Downloaded\02f57965-6864-4aae-add5-db185ffa4008_dcf4dd03-7801-4fab-af0c-63854970b3de_(0'42''67).replay.gbx",
                timestamp: "2021-07-04T18:10:56+00:00",
                url: "/api/download/ghost/35961b84-9f31-4c19-80a6-e4324dd509e7",
                removed: false
        },
  ...
}
```



