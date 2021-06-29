<h3 align="center">Mandh Admob Helper</h3>

<div align="center">

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/kylelobo/The-Documentation-Compendium.svg)](https://github.com/kylelobo/The-Documentation-Compendium/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/kylelobo/The-Documentation-Compendium.svg)](https://github.com/kylelobo/The-Documentation-Compendium/pulls)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> This project created for helping management of Admob in Mandh Android applications.
    <br> 
</p>

## 📝 Table of Contents

- [About](#about)
- [Usage](#usage)
- [TODO](#todo)
- [Authors](#authors)

## 🧐 About <a name = "about"></a>

Admob manager is making easy for Admob integrate to our applications. We expect to prevent confusing of updates and policies of Admob.

## 🎈 Usage <a name="usage"></a>

Add Jitpack to proect build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

Add implementation to app/build.gradle
```
dependencies {
    ...
    implementation 'com.github.hunkar:mandh-admob-manager:1.0.0'
    ...
}
```

We should init keys to Admanager
```
AdManager.setAdManager(getApplicationContext(),
                        ADMOB_APP_ID,
                        ADMOB_BANNER_ID,
                        ADMOB_INTERSTITIAL_ID);
```

We need to initialize of interstitial ads
```
InterstitialAdManager.initInterstitialAd();
```

We need to set activity in onCreate method of each activity
```
AdManager.setActivity(MainActivity.this);
```

This method shows interstitial ad immediately after initialize and ad is ready.
```
InterstitialAdManager.showOnReady();
```

This method shows interstitial ad.
```
InterstitialAdManager.showAd();
```

Banner view
```
    <com.mandh.googleadmanager.BannerView
        android:id="@+id/mandh_ad"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

To show banner view
```
BannerView bannerView = findViewById(R.id.mandh_ad);
bannerView.show();
```

We can close apps with this line
```
AdManager.setAdsClosed(true);
```

We can set range of showing interstital ads as millisecond. 
```
AdManager.setInterstitialAdTimeout(5000);
```


## 🚀 TODO <a name = "todo"></a>

- Rewarded ads unit will added.
- Native ads unit will be added.

## ✍️ Authors <a name = "authors"></a>

- [Mandh Solutions](http://www.mandhsolutions.com/) - Idea & Initial work
