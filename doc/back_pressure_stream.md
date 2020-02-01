# [Reactive Streams](https://www.reactive-streams.org/)

Reactive Streams is an initiative to provide a standard for asynchronous stream processing with non-blocking back pressure.

> package: org.reactivestreams

- `Publisher<Data>`
    > A provider of a potentially unbounded number of sequenced elements, publishing them according to
    > the demand received from its `Subscriber(s)`.
    >
    > A `Publisher` can serve multiple `Subscribers` subscribed `#subscribe(Subscriber)` dynamically
    > at various points in time.

- `Subscription`
    > A `Subscription` represents a **one-to-one lifecycle** of a `Subscriber` subscribing to a `Publisher`.
    >
    > It can only be used **once** by a single `Subscriber`.
    >
    > It is used to both signal desire for data and cancel demand (and allow resource cleanup).
    >
    - `request(long n)`
        > @param n `n >= 1 and n <= Long.MAX_VALUE`, it may be treated by the `Publisher` as "effectively unbounded"
        >
        > No events will be sent by a `Publisher` until demand is signaled via this method.
        >
        > A `Publisher` can send **less than** is requested if the stream ends but
        > then must emit either `Subscriber#onError(Throwable)` or `Subscriber#onComplete()`.
        >
        > - `n < Long.MAX_VALUE` is back-pressure stream
        > - `n = Long.MAX_VALUE` is normal stream

    - `cancel()`
        > Request the `Publisher` to stop sending data and clean up resources.
        >
        > **NOTICE:** Data may still be sent to meet previously signalled demand **after** calling cancel.

- `Subscriber<Data>`
    > Will receive call to `#onSubscribe(Subscription)`
    > once after passing an instance of `Subscriber` to `Publisher#subscribe(Subscriber)`.
    >
    > No further notifications will be received until `Subscription#request(long)` is called.
    >
    > After signaling demand:
    >
    > - **One or more** invocations of `#onNext(Object)` up to the maximum number defined
    > by `Subscription#request(long)`
    > - **Single** invocation of `#onError(Throwable)` or `#onComplete()` which signals a terminal state
    > after which no further events will be sent.
    >
    > Demand can be signaled via `Subscription#request(long)`
    > whenever the `Subscriber` instance is capable of handling more:
    >
    > request(1) -> onNext(data) -> request(1) -> onNext(data) -> ...

- `Processor<Data>` extends `Subscriber<Data>` and `Publisher<Data>`
    > Represents a processing stage—which is both a `Subscriber`
    > and a `Publisher` and obeys the contracts of both.

## AbstractListenerReadPublisher > Publisher<Data> = Data Producer

> package: org.springframework.http.server.reactive

> Abstract base class for `Publisher` implementations that bridge between
> event-listener read APIs and Reactive Streams.

```
    UNSUBSCRIBED
         |
         v
    SUBSCRIBING
         |
         v
+---- NO_DEMAND ---------------> DEMAND ---+
|        ^                         ^       |
|        |                         |       |
|        +------- READING <--------+       |
|                    |                     |
|                    v                     |
+--------------> COMPLETED <---------------+
```

## AbstractListenerWriteProcessor > Processor<Data> > Subscriber<Data> & Publisher<Data> = Data Transport

> package: org.springframework.http.server.reactive

> Abstract base class for `Processor` implementations that bridge between
> event-listener write APIs and Reactive Streams.

```
     UNSUBSCRIBED
          |
          v
+--- REQUESTED -------------> RECEIVED ---+
|        ^                       ^        |
|        |                       |        |
|        + ------ WRITING <------+        |
|                    |                    |
|                    v                    |
+--------------> COMPLETED <--------------+
```

## AbstractListenerWriteFlushProcessor > Processor > Publisher & Subscriber

> package: org.springframework.http.server.reactive

> An alternative to `AbstractListenerWriteProcessor` but instead writing
> a `Publisher<Publisher<T>>` with flush boundaries enforces after
> the completion of each nested `Publisher`.

```
UNSUBSCRIBED
     |
     v
 REQUESTED <---> RECEIVED ------+
     |              |           |
     |              v           |
     |           FLUSHING       |
     |              |           |
     |              v           |
     +--------> COMPLETED <-----+
```

## Back-pressure Stream: A -> B

1. prepare
    1. (A) Publisher#subscribe(Subscriber)
        1. `subscription = createSubscription(publisher, subscriber)`
        2. `changeState(SUBSCRIBING)` and `subscriber.onSubscribe(Subscription)`
        3. `changeState(NO_DEMAND)` to waiting for demands
    2. (B) Subscriber#onSubscribe(Subscription)
        1. `changeState(REQUESTED)` and `subscription.request(n)` to signal publisher for `n` demands

2. working
    1. (A) Publisher.Subscription#request(n)
        1. `changeState(DEMAND)` and try to produce data or request data from upstream
        2. `subscriber.onNext(data)` if data is available
        3. `changeState(READING)` if handled demand number < `n`,
           or else pause data producing and `changeState(NO_DEMAND)`
    2. (B) Subscriber#onNext(data)
        1. consume data
        2. `changeState(REQUESTED)` and `subscription.request(n)` for more data if needed,
           or else `changeState(COMPLETED)` and `subscription.cancel()` to end subscription

3. finally
    1. (A) Publisher.Subscription#cancel()
        1. `changeState(COMPLETED)` and release resources
