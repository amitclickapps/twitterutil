![](https://jitpack.io/v/amitclickapps/twitterutil.svg?style=flat-square)
**How to Use Library**


`TwitterConnect` is used to call every Method.
Currently this library support only following service.
- Login
- Logout
- Email
- Profile

----
`Model` used in above service



##### Login

  `Result` - TwitterSession

  `Error` - TwitterException

##### Logout

`Result` - N/A

`Error` - N/A

##### Profile

`Result` - User

`Error` - TwitterException


----

`Sample` Example

To set `Configuration` regarding Twitter Key , Twitter Secret

```
new TwitterConfiguration.Builder(this)
                .isDebug(true)
                .keys(TWITTER_KEY, TWITTER_SECRET)
                .config();
```

To check for active `session`

```
TwitterConnect.get().session()
```

`Login Sample`

```
TwitterConnect.with(this, Param.TWAction.LOGIN)
                    .callback(new OnTwitterCallback<TwitterSession, TwitterException>() {
                        @Override
                        public void onSuccess(TwitterSession twitterSessionResult) {
                            TwitterAuthToken authToken = twitterSessionResult.getAuthToken();
                            String token = authToken.token;
                            String secret = authToken.secret;
                            Log.i(getLocalClassName(), "UserName = " + twitterSessionResult.getUserName());
                            Log.i(getLocalClassName(), "Id = " + twitterSessionResult.getUserId());
                            Log.i(getLocalClassName(), "token = " + token);
                            Log.i(getLocalClassName(), "secret = " + secret);
                            email();
                        }

                        @Override
                        public void onError(TwitterException e) {
                            e.printStackTrace();
                        }
                    }).build();
```

`Email Permission Sample`

```
TwitterConnect.with(this, Param.TWAction.EMAIL)
                .callback(new OnTwitterCallback<String, TwitterException>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.i(getLocalClassName(), "onSuccess = " + s);
                    }

                    @Override
                    public void onError(TwitterException e) {
                        e.printStackTrace();
                    }
                }).build();
```

`Profile Sample`

```
TwitterConnect.with(this, Param.TWAction.PROFILE)
                .callback(new OnTwitterCallback<User, TwitterException>() {
                    @Override
                    public void onSuccess(User s) {
                        Log.i(getLocalClassName(), "Email = " + s.email);
                        Log.i(getLocalClassName(), "Name = " + s.name);
                        Log.i(getLocalClassName(), "profileImageUrl = " + s.profileImageUrl);
                    }

                    @Override
                    public void onError(TwitterException e) {
                        e.printStackTrace();
                    }
                }).build();
```

`OnAcitivyResult`

```
if(requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE){
     TwitterConnect.get().onActivityResult(requestCode, resultCode, data);
 }
```

Download
--------
Add the JitPack repository to your root build.gradle:

![](https://jitpack.io/v/amitclickapps/twitterutil.svg?style=flat-square)


```groovy
	allprojects {
		repositories {
			maven { url "https://jitpack.io" }
		}
	}
```
Add the Gradle dependency:
```groovy
	dependencies {
		compile 'com.github.amitclickapps:twitterutil:latest'
	}
```