#Android Annotation Processing
This is a playground project to evaluate annotation processing in Android.

It is based on [this tutorial](http://brianattwell.com/android-annotation-processing-pojo-string-generator/) by Brian Attwell.

##Project setup
* **api:** This module contains the `Annotations` the `Processor` is going to process. It's separated from the `compiler` module in order to have a lightweight dependency in the dependent `app` module.
* **compiler:** This module contains the custom `Annotation Processor`.  Having a dedicated module for the `Processor` features to have a *compile time only* dependency (the `Processor` is not bundled with the resulting app).
* **app:** This module showcases how to use the `api` along with the `compiler`. It uses the convenient [android-apt](https://bitbucket.org/hvisser/android-apt) Gradle plugin in order to simplify the configuration of a custom annotation processor in Android Studio.

*Note: The `Processor` is not invoked when using android-apt 1.7. Downgrading to version 1.4 solves the problem.*
