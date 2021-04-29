# Trackmania COTD App

DISCLAIMER! This project is not related to Nadeo! Its a fan made project to generate and display a leaderboard for the Trackmania Cup of the Day.

This project is based on the work of <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a>! Unfortunately he stopped working on this project.

## Special thanks

* <a href="https://github.com/codecat">@CodeCat</a> for letting me use the trackmania.io API
* <a href="https://www.twitch.tv/granadyy">GranaDy</a> for letting me use his "granaBonk" emote inside my app. Make sure to check out his Twitch channel for Cup of the Day WINNER POV and some juicy WHEEL action.
* <a href="https://github.com/Bmandk">@Bmandk</a> and axelalex2 for helping me debug the Trackmania style parser ^^
* <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a>


## Questions?

Feel free to contact me!

* Twitter: <a href="https://twitter.com/SoWieMarkus">@SoWieMarkus</a>
* Discord: Markus#2348

## Used technologies

* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a> (my own API). See documentation <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">here</a>.
* <a href="https://trackmania.io/#/totd">trackmania.io</a> API from <a href="https://github.com/codecat">@CodeCat</a>
* <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> collecting data from trackmanio.io and imports it into my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>
* <a href="https://github.com/PhilJay/MPAndroidChart">MPAndroidChart</a> to display the pie chart inside the player detail fragment
* <a href="https://github.com/SoWieMarkus/DefaultAppComponents">DefaultAppComponents</a>

## How does it work

I am using the results of the Cup of the Day from trackmania.io. There are some days missing or incompleted. Those dates are:

* 10.12.2020, reason unknown
* 04.01.2021, reason unknown. GranaDy said on this day you were able to vote.
* 27.-28.01.2021 Server issues
* 29.-30.01.2021 reason unknown

I did not correct any of this results! They are still as you can find them on trackmania.io. There are other leaderboards who corrected those days by hand. This can lead to different results.

For creating the leaderboard I am using the point system used by <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#">pointerzio</a>.

### Get results from Cup of the Day

My <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot">trackmania.io havesting bot</a> is running continuously on a Raspberry Pi. Every 10 minutes it gets the results of the most recent Cup of the Day from <a href="https://trackmania.io/#/cotd">trackmania.io</a> and sends the data to my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI">Trackmania COTD Rest API</a>. There the new results will be processed and the leaderboard will be updated.

Check out the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDBot#readme">trackmania.io havesting bot documentation</a> and the <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDRestAPI#readme">API documentation</a>.

### Get information about the Track of the Day

The app uses the API <a href="trackmania.io">trackmania.io</a> by <a href="https://github.com/codecat">@CodeCat</a>. If you want to use this API for your own projects, <a href="https://github.com/codecat">@CodeCat</a> allowes the usage of the API under the following conditions:

* The API is unsupported. If CodeCat removes/changes something, CodeCat will (likely) not give notice of it, and you'll have to deal with it yourself.
* The API is undocumented. You're on your own to figure out how the API works and what you need from it.
* Use a proper user agent header so that CodeCat can see how you're using the API.
* There are no hard limitations in place, so please be respectful of CodeCat's server resources as well as Nadeo's. Cache stuff! Please let CodeCat know if you're planning to do any kind of "bulk" requests!
* Let CodeCat know what you're working on! CodeCat is always curious and it lets CodeCat understand how people are using the API and who to contact about certain things if any contact is needed.
* If you're still unsure about your use of the API, feel free to DM Miss#8888 with any further questions.

#### Get all tracks of a the current month

GET `https://trackmania.io/api/totd/0`

```
{
  year: 2021,
  month: 4,
  lastday: 30,
  days: [
    {
      campaignid: 8383,
      map: {
        author: "62c59cd2-4981-43cc-a6d2-7feaf96ceeb1",
        name: "$F00[$F10L$F20O$F30L$F50]$F60P$F70l$F80i$F90n$FA0k$FC0o$FD0 $FE0v$FF01",
        authorScore: 41529,
        goldScore: 45000,
        silverScore: 50000,
        bronzeScore: 63000,
        collectionName: "Stadium",
        environment: "Stadium",
        filename: "[LOL]Plinko v1.Map.Gbx",
        isPlayable: true,
        mapId: "0308f5f1-380e-4394-a737-dc031e1b6d2d",
        mapUid: "17B5XtQBJ_nukdrylfT7Pj1q1C1",
        submitter: "62c59cd2-4981-43cc-a6d2-7feaf96ceeb1",
        timestamp: "2021-03-01T02:26:22+00:00",
        fileUrl: "https://prod.trackmania.core.nadeo.online/storageObjects/792ea4f6-1a13-45e3-b860-6c40c62a33f3",
        thumbnailUrl: "https://prod.trackmania.core.nadeo.online/storageObjects/5063b9b8-da6c-4cf3-a0d3-f17cf0cb6d16.jpg",
        authordisplayname: "prince10bee",
        submitterdisplayname: "prince10bee",
        exchangeid: 23641
      },
      weekday: 3,
      monthday: 1,
      leaderboarduid: "bf597bc1-a8c9-4dfa-9ae9-40a3a5e4a0bf"
    },
  ...
  ]
}

```

#### Get all tracks of a specific month

GET `https://trackmania.io/api/totd/<index>`

0 is always the index of the current month. If you want to get months from the past you have to increment the index. Index 1 will be the month before the current month and so on.

#### Get time leaderboard of a specific map

With the `mapUid` and the `leaderboarduid` you can get the best times of this map.

GET `https://trackmania.io/api/leaderboard/<leaderboarduid>/<mapUid>`

```
{
  tops: [
    {
      accountid: "0da0a251-20e8-4219-86eb-7d9c52847779",
      displayname: "Cemkoo",
      zone: {
        name: "Turkey",
        flag: "TUR",
        parent: {
          name: "Europe",
          flag: "europe",
          parent: {
            name: "World",
            flag: "WOR"
          }
        }
      },
      position: 1,
      time: 33045,
      points: 0,
      filename: "Replays\Downloaded\ad5a3da2-7231-4456-bfc0-8212a0d48c5b_0da0a251-20e8-4219-86eb-7d9c52847779_(0'33''4).replay.gbx",
      timestamp: "2021-04-28T21:00:47+00:00",
      url: "/api/download/ghost/ef0a6aef-cb39-4f38-b2c4-4dd41030ec37"
  },
  ...
}
```



