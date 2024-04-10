This Test app is a kotlin multiplatform project targeting Android, Desktop (jvm) and Ios.

Known issues:
- 1 Navigation is all handled by a view model and could be improved by using a navigation library.
- 2 Regarding the view models. 
* on Android they all live in the main activity
* on Desktop they live for the all lifecycle of the app
* on Ios they also live for the all lifecycle of the app (which is likely not the best approach)