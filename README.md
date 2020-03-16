# News feed

Before proceeding with the refactoring of the initial task the code was analysed and a pool of tasks
was written.

## Backlog
### Maintenance
ONF-101. Update libraries
ONF-102. Improve building process (gradle + ext)
ONF-103. Improve naming and typos
ONF-104. Remove allow backups

###UI
ONF-201. Move hardcoded values to resources (news_item)
ONF-202. Add databinding support
ONF-203. Support configuration change
ONF-204. Move news_item to constraint layout
ONF-205. Make the app bar hiding on scroll.

###Logic
ONF-301. Unlink the adapter.
ONF-302. Unlink the parser.
ONF-303. Introduce MVP.
ONF-304. Introduce DI.
ONF-305. Refactor news provider.
ONF-306. Make file parsing in background
ONF-307. Make model class null-safe (news - nullable) 
ONF-308. Optimize a number of parsing calls in onResume
ONF-309. Add exception handling for reading and parsing the file
ONF-310. Exclude news that are not valid

###Testing
ONF-401. Introduce tests.
ONF-402. Test on a big file.
ONF-403. Test on a file with missing fields.
ONF-404. Test on a file with empty fields.
ONF-405. Test on an empty file.

## Developer notes

The libraries were updated to the actual versions.
For better version management a file ext.gradle was introduced. This may allow to change the versions
in one place.
"My" prefixes of the classes were removed.
Allow Backups property was removed from the manifest in terms of security.

The styles, dimens, strings were introduced and hardcoded values have been removed.
Databinding support has been added.
The app now supports configuration changing events.
The layout of one item was converted to a constraint layout instead of nested linear layouts.
The appbar now hides/shows up when a user scrolls the list.

The classes of the adapter and parser were separated and placed in different files.
MVP pattern was introduced and appropriate package structure has been created.
Dagger2 as a Dependency Injector has been added. Modules were created for the App, Activity,
Adapter and Presenter.
The news provider has been refactored. RxJava has been added to make the process in background.
For this task it is an overkill of course.
The models were created to represent only valid results. NewsItem is used in UI and has non-null
properties. News is used on the network layer and all it's fields are nullable (because anything can
happen in the file or network side).
The parser now handles missing and empty fields. The UI part filters them out.
The amount of calls to the parser was reduced. If the app has the items in the presenter, the file is
not opened again.

Tests were added and cover the presenter and the provider.
Some sample files were added to provide invalid json (missing/empty fields), or huge file to be able
to test the logic and be sure that it handles all the necessary cases.
As a future improvement instead of filtering invalid items the UI can show placeholders where the field
is missing or blank.
