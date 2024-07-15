# Clojure AWS Lambda Demo

Adapted from: https://github.com/juxt/plain-clojure-lambda/tree/reitit-function-url

## Setup

To install the javascript dependencies:

```bash
$ npm install
```

## Build

To build an uberjar:

```bash
$ clojure -T:build uber
```

Then upload the `target/lambda-*.jar` to your [lambda function](https://docs.aws.amazon.com/lambda/latest/dg/java-package.html#java-package-console)
and off you go!

To use, create a [function URL](https://docs.aws.amazon.com/lambda/latest/dg/urls-configuration.html#create-url-console)
then visit the given url.

## Dev

To develop this locally, start shadow-cljs:

```bash
$ npx shadow-cljs watch app
```

Then either start your repl in the ordinary way (using the `:dev` alias) or connect to the repl that shadow-cljs opens.

You can then execute `(user/go!)` to start or restart a local webserver on http://localhost:8000
