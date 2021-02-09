# SmartApiCaller

>A smart &amp; easy way to call RestApi's through Retrofit, Now you don't have to write extra classes and response interfaces to call api's

## Gradle Dependency

 ``` gradle
dependencies {
    implementation 'com.github.zeeshansardar61:SmartApiCaller:1.0.0'
    //for retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    //for serializing json pojo objects
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
 }
 ```
Also add following in your ```build.gradle``` at Project level 

```gradle 
repositories {
maven { url 'https://jitpack.io' }
}
  ```
## Usage

In your application class, add the ``BASE_URL`` where you want to make call. Also create an Interface where you will define 
all your RestCalls. Then initialize the Retrofit Client with your ApiInterface.


```java
public static ApiInterface smartCaller;

@Override
    public void onCreate() {
        super.onCreate();
        SmartApiCaller.BASE_URL = "https://api.github.com/";
        smartCaller = SmartApiCaller.getRestClient(ApiInterface.class);
    }
```

## ApiInterface 

This interface will contains all the methods you want to call. For example 

```java
public interface ApiInterface {
    @GET("search/repositories")
    Call<GitResponse> getRepos(@Query("q") String trending, @Query("sort") String stars);
}
```

## RestCalling

Implement the ```ResponseHandler``` in your class or fragment. Then call RestApi as mentioned below.

```java
new SmartRestCaller(context, this, AppApplication.smartCaller.getRepos("trending", "stars"), TRENDING_REQUEST_CODE);
```
Note: No need to worry about internet connectivity check. It will take care of it automatically. :)
No matter how many api's you want to call on fragment or activity, it will take just one line code to call.

## Response Handling

Now if there are multiple api's call on activity or fragment, you will manage the response of the api's on the basis of ```REQUEST_CODE```
of the api.

```java
@Override
    public void onSuccess(Call call, Response response, int reqCode) {
      if(reqCode==TRENDING_REQUEST_CODE){
         GitResponse gitResponse = (GitResponse) response.body();
         //this is how you will manage your response according to the specific request.
      }
    }   
@Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
         if(reqCode==TRENDING_REQUEST_CODE){
           Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();
         }
    }    
@Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
      if(reqCode==TRENDING_REQUEST_CODE){
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
       }
    }
```
    
Hope you all will like this. Happy Coding. Enjoy :)


