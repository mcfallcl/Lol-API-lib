# Lol-API-lib
A library for making and handling API requests to the League of Legends API server.

A quick guide on the basic use of this library:

First, you want to set your API key and ResponseHandler. ResponseHandler is an interface used to handle the requests as they pass through the throttle.

```Java
LolAPI.set("your-api-key-here", new ResponseHandler() {
    @Override
    public void operate(Response response) {
        System.out.println(response);
    }
});
```

The above method sets your api key and sets its rate limits to the default dev rates(10/10sec, 500/10min), creates a DefaultThrottle for your requests to go through, to ensure you do not go over your api key's rate limit.

You can set your api with specific rate limits or your own throttle. The DefaultThrottle uses a simple queue. The RequestArbiter interface is there to create your own throttle if more functionality is required, like a priority queue.

With your api key set, there are two ways to make requests to the API server. The primary way is to use:

```Java
LolAPI.makeRequest(Request)
```

Which sends the request through your throttle and response handler. You can also use:

```Java
Request.send()
```

but you will bypass your throttle and ResponseHandler, which risks violating your rate limit and requires you to handle the recieved response manually.
